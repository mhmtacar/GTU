#include <netdb.h> 
#include <string.h> 
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <fcntl.h>
#include <stdio.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <stdlib.h>
#include <pthread.h>
#include <errno.h>
#include <dirent.h>
#include <signal.h>
#include <libgen.h>
#include <sys/time.h>
#include <sys/inotify.h>
#include <sys/ioctl.h>
#include <errno.h>

#define BUFFER_SIZE 1024

#define MAX_FILE_CONTENT 1024
#define MAX_CONTENT_LENGTH 1000
#define MAX_PATH_LENGTH 256

pthread_mutex_t output_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t output_mutex2 = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t output_mutex3 = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t output_mutex4 = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t output_mutex5 = PTHREAD_MUTEX_INITIALIZER;

typedef struct {
    char name[MAX_PATH_LENGTH];
    char content[MAX_CONTENT_LENGTH];
} File;

typedef struct {
    char name[MAX_PATH_LENGTH];
    File files[MAX_PATH_LENGTH];
    int fileCount;
    struct Node* subdirectories[MAX_PATH_LENGTH];
    int subdirCount;
} Directory;

typedef struct {
    int client_socket;
    struct sockaddr_in client_addr;
} ClientInfo;

typedef struct{
    char name[512];
    mode_t mode; // Permissions
    char content[4096];
    long size;
    time_t modifiedTime;
    int isDeleted;
}file;

typedef struct {
    char name[MAX_PATH_LENGTH];
    char content[MAX_FILE_CONTENT];
} FileEntry;

typedef struct {
    char name[MAX_PATH_LENGTH];
    FileEntry files[MAX_PATH_LENGTH];
    int fileCount;
    struct DirEntry* subdirs[MAX_PATH_LENGTH];
    int subdirCount;
} DirEntry;

typedef struct {
    char name[1024];
    //char modifiedTime[512];
    int isDeleted;
    //file files[100]
    char type[10];
    char content[4096];
    char time[100];
} Info;

char pathName[1024];
int threadPoolSize;
pthread_t *threadPool;
int server_socket;
volatile int done = 0;
Info clientInfo[10000];
Info serverInfo[10000];
Info inf[100];
Info inf2[100];
int count = 0;
int count2 = 0;
int counter = 0;

file f[100];
    file f2[100];

#define MAX_CLIENTS 100
#define BUFFER_SZ 2048

static _Atomic unsigned int cli_count = 0;
static int uid = 1;

/* Client structure */
typedef struct{
	struct sockaddr_in address;
	int sockfd;
	int uid;
	char name[32];
} client_t;

client_t *clients[MAX_CLIENTS];

pthread_mutex_t clients_mutex = PTHREAD_MUTEX_INITIALIZER;

void readFile2(const char *fileName) {

	 pthread_mutex_lock(&output_mutex2);

    int fd = open(fileName, O_RDONLY);

    ssize_t bytes_read, bytes_written;
    
    // Copy contents from source file to destination file
    while ((bytes_read = read(fd, serverInfo[count].content, sizeof(serverInfo[count].content))) > 0) {
        //serverInfo[count].size = bytes_read;
    }
    
    // Close the file descriptors
    close(fd);
    
/*
    FILE *fptr;

    // Open a file in read mode
    fptr = fopen(fileName, "r");

    // Read the content and store it inside myString
    fgets(serverInfo[count].content, 4096, fptr);

    // Close the file
    fclose(fptr); 
*/
    pthread_mutex_unlock(&output_mutex2);
    
}

void copyFile(Info info){
    pthread_mutex_lock(&output_mutex3);
    char file_path[2048];
    sprintf(file_path, "%s/%s", pathName, info.name);
    
    //remove(file_path);
    int fd = open(file_path,O_WRONLY | O_CREAT | O_TRUNC,0644);
    //int i = 0;
    //while(info.content[i] != 0){
    write(fd,info.content,strlen(info.content));
    //i++;
    //}
    close(fd);
    
/*
    FILE *fp;
    fp = fopen(file_path,"w+");

   // If file opened successfully, then write the string to file
   if ( fp )
   {
       fputs(info.content,fp);
   }

   fclose(fp);
*/
    pthread_mutex_unlock(&output_mutex3);

}

void updateServer(){

    pthread_mutex_lock(&output_mutex4);
    //printf("Count:%d\n",count);

    int i,j;
    int check = 0;

    for(i = 0;i < count; i++){
        serverInfo[i].isDeleted = 1;
        //for(j = 0;j < 100)
    }

    i = 0;

    if(clientInfo[0].name[0] != 0){

    while(strlen(clientInfo[i].name) != 0){

        check = 0;

        for(j=0;j<count;j++){
            if(strcmp(serverInfo[j].name,clientInfo[i].name) == 0){
                check = 1;
                serverInfo[j].isDeleted = 0;
                //printf("girdi\n");
                if(strcmp(serverInfo[j].time,clientInfo[i].time) != 0){
                    strcpy(serverInfo[j].time,clientInfo[i].time);
                    copyFile(clientInfo[i]);
                }
                break;
            }
        }

        if(check == 0){
            /*
            if(strcmp(clientInfo[i].type,"DIR") == 0){
                j = 0;
                strcpy(serverInfo[count].type,"DIR");
                strcpy(serverInfo[count].name,clientInfo[i].name);
                while(strcmp(clientInfo[i].files[j].name,"") != 0){
                    strcpy(serverInfo[count].files[j].name,clientInfo[i].files[j].name);
                    j++;
                }
            }
            */
            if(strcmp(clientInfo[i].type,"REG") == 0){
                strcpy(serverInfo[count].type,"REG");
                strcpy(serverInfo[count].name,clientInfo[i].name);
                strcpy(serverInfo[count].time,clientInfo[i].time);
                copyFile(clientInfo[i]);
            }

            //strcpy(client[count].name,dest_file);
            serverInfo[count].isDeleted = 0;
            //printf("%s\n",entry->d_name);
            count++;
            //copyDirectory(src_file,dest_dir);
        }

        i++;
    }

}
/*
    for(int i = 0; i < count; i++){
        if(serverInfo[i].isDeleted == 1){
            char dir_name[1024];
            sprintf(dir_name, "%s/%s", pathName,serverInfo[i].name);
            if(strcmp(serverInfo[i].type,"DIR") == 0){
                //printf("mehmet\n");
                rmdir(dir_name);
            }
            else if(strcmp(serverInfo[i].type,"REG") == 0){
                unlink(dir_name);
            }
        }
    }
*/
    pthread_mutex_unlock(&output_mutex4);
}

void traverseServer() {
    DIR *dir;
    DIR *dir2;
    struct dirent *entry;
    struct dirent *entry2;
    count = 0;
    int i,j;

    pthread_mutex_lock(&output_mutex5);

    for(i=0;i<10000;i++){
        strcpy(serverInfo[i].name,"");
        /*
        for(j=0;j<100;j++){
            strcpy(serverInfo[i].files[j].name,"");
        }
        */
    }  
    
/*
    for(int i = 0;i < count; i++){
        serverInfo[i].isDeleted = 1;
    }
    */
    // Open the source directory
    dir = opendir(pathName);
    if (dir == NULL) {
        fprintf(stderr, "Error opening source directory: %s\n", pathName);
        return;
    }

    
    // Traverse through the directory entries
    while ((entry = readdir(dir)) != NULL) {

        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0)
            continue;
/*
        FileInfo file_info;
        */
        //char file_name[1024];
        char dir_name[2048];
        int check = 0;
        // Create the source and destination paths
        //strcpy(file_name, entry->d_name);
        sprintf(dir_name, "%s/%s", pathName, entry->d_name);
        //printf("%s\n",dest_file);
        /*
        for(int i = 0;i<count ; i++){
            printf("%s\n",serverInfo[i].name);
        }
        */
        //int ret;
        //ret = strstr(entry->d_name, ".goutputstream");
        //if (!ret){
            //printf("%s\n",entry->d_name);
            /*
        for(int i = 0 ; i < count ; i++){
            if(strcmp(file_name,serverInfo[i].name) == 0 ){
                check = 1;
                serverInfo[i].isDeleted = 0;
                printf("girdi\n");
                break;
            }
        }
*/
        //if(check == 0){
            /*
            if(entry->d_type == DT_DIR){
                //printf("yunus\n");
                strcpy(serverInfo[count].type,"DIR");
                strcpy(serverInfo[count].name,entry->d_name);
                serverInfo[count].isDeleted = 0;
                dir2 = opendir(dir_name);
                if (dir2 == NULL) {
                    fprintf(stderr, "Error opening source directory: %s\n", serverPath);
                    return;
                }
                count2 = 0;
                while ((entry2 = readdir(dir2)) != NULL) {

                    if (strcmp(entry2->d_name, ".") == 0 || strcmp(entry2->d_name, "..") == 0)
                        continue;

                    int ret;
                    ret = strstr(entry2->d_name, ".goutputstream");
                    if (!ret){
                        if(entry2->d_type == DT_REG){
                        char file_name[1024];
                        sprintf(file_name, "%s/%s", dir_name, entry2->d_name);
                        readFile(file_name);
                        strcpy(serverInfo[count].files[count2].name,entry2->d_name);
                        serverInfo[count].files[count2].isDeleted = 0;
                        count2++;
                        }
                    }

                }
                closedir(dir2);
            }
            */
             if(entry->d_type == DT_REG){
                struct stat attr;
                char path[2048];
                sprintf(path,"%s/%s",pathName,entry->d_name);
                stat(path, &attr);
                readFile2(dir_name);
                strcpy(serverInfo[count].type,"REG");
                strcpy(serverInfo[count].name,entry->d_name);
                strcpy(serverInfo[count].time,ctime(&attr.st_mtime));
                count++;
                //serverInfo[count].isDeleted = 0;
            }

            //strcpy(serverInfo[count].name,entry);
            //serverInfo[count].isDeleted = 0;
            //printf("%s\n",entry->d_name);
    
            //copyDirectory(src_file,dest_dir);
        //}

        //}

        /*
        // Check if the entry is a directory
        if (entry->d_type == DT_DIR) {
            add_buffer(file_info.src_file, file_info.dest_file);
        }
        // Check if the entry is a regular file or a FIFO
        else if (entry->d_type == DT_REG || entry->d_type == DT_FIFO) {

            if(entry->d_type == DT_FIFO){
                file_info.src_fd = -1;
                file_info.dest_fd = -1;
            }

            else{
                // Open the source file for reading
                file_info.src_fd = open(file_info.src_file, O_RDONLY);
                
                // Open the destination file for writing
                file_info.dest_fd = open(file_info.dest_file, O_WRONLY | O_CREAT | O_TRUNC, 0644);

                if (file_info.src_fd == -1 || file_info.dest_fd == -1) {
                    if (file_info.src_fd == -1) {
                        pthread_mutex_lock(&output_mutex);
                        fprintf(stderr, "Error opening source file: %s\n", file_info.src_file);
                        pthread_mutex_unlock(&output_mutex);
                        close(file_info.src_fd);
                        close(file_info.dest_fd);
                        continue;
                    }
                    else{
                        pthread_mutex_lock(&output_mutex);
                        fprintf(stderr, "Error opening destination file: %s\n", file_info.dest_file);
                        pthread_mutex_unlock(&output_mutex);
                        close(file_info.src_fd);
                        close(file_info.dest_fd);
                        continue;
                    }
                }
            }
            copyFile(file_info);
            }
            */
    }
    
    // Close the directory
    closedir(dir);
    /*
    printf("%d\n",count);
    for(int i = 0;i<count ; i++){
        printf("IsDeleted:%d\n",serverInfo[i].isDeleted);
    }
    for(int i = 0; i < count; i++){
        if(serverInfo[i].isDeleted == 1){
            if(strcmp(serverInfo[i].type,"DIR") == 0){
                //printf("mehmet\n");
                rmdir(serverInfo[i].name);
            }
            else if(strcmp(serverInfo[i].type,"REG") == 0){
                unlink(serverInfo[i].name);
            }
        }
    }
    */
    pthread_mutex_unlock(&output_mutex5);

}

void print_client_addr(struct sockaddr_in addr){
    printf("%d.%d.%d.%d",
        addr.sin_addr.s_addr & 0xff,
        (addr.sin_addr.s_addr & 0xff00) >> 8,
        (addr.sin_addr.s_addr & 0xff0000) >> 16,
        (addr.sin_addr.s_addr & 0xff000000) >> 24);
}

/* Add clients to queue */
void queue_add(client_t *cl){
	pthread_mutex_lock(&clients_mutex);

	for(int i=0; i < threadPoolSize; ++i){
		if(!clients[i]){
			clients[i] = cl;
			break;
		}
	}

	pthread_mutex_unlock(&clients_mutex);
}

/* Remove clients to queue */
void queue_remove(int uid){
	pthread_mutex_lock(&clients_mutex);

	for(int i=0; i < threadPoolSize; ++i){
		if(clients[i]){
			if(clients[i]->uid == uid){
				clients[i] = NULL;
				break;
			}
		}
	}

	pthread_mutex_unlock(&clients_mutex);
}

/* Send message to all clients except sender */
void send_message(char *s, int uid){
	pthread_mutex_lock(&clients_mutex);

	for(int i=0; i<threadPoolSize; ++i){
		if(clients[i]){
				if(write(clients[i]->sockfd, s, strlen(s)) < 0){
					perror("ERROR: write to descriptor failed");
					break;
				}
			
		}
	}

	pthread_mutex_unlock(&clients_mutex);
}

void send_message2(int uid){
	pthread_mutex_lock(&clients_mutex);

	for(int i=0; i<threadPoolSize; ++i){
		if(clients[i]){
			
				if(write(clients[i]->sockfd, &serverInfo, sizeof(serverInfo)) < 0){
					perror("ERROR: write to descriptor failed");
					break;
				}
			
		}
	}

	pthread_mutex_unlock(&clients_mutex);
}

/* Handle all communication with the client */
void *handle_client(void *arg){
	char buff_out[BUFFER_SZ];
	char name[1024];
	int leave_flag = 0;

	cli_count++;
	client_t *cli = (client_t *)arg;

	// Name
	
	if(recv(cli->sockfd, name, 1024, 0) <= 0){
		printf("Didn't enter the name.\n");
		leave_flag = 1;
	} else{
		strcpy(cli->name, name);
		sprintf(buff_out, "\n%s has joined\n", cli->name);
		printf("%s", buff_out);
		//send_message(buff_out, cli->uid);
	}

	bzero(buff_out, BUFFER_SZ);
	



	while(1){
		if (leave_flag) {
			break;
		}

		//pthread_mutex_lock(&output_mutex);

		int receive = recv(cli->sockfd, clientInfo, sizeof(clientInfo), 0);
		if (receive > 0){
				traverseServer();
				updateServer();
				send_message2(cli->uid);

				//str_trim_lf(buff_out, strlen(buff_out));
				//printf("%s -> %s\n", buff_out, cli->name);
			
		} else if (receive == 0){
			sprintf(buff_out, "%s has left\n", cli->name);
			printf("%s", buff_out);
			//send_message(buff_out, cli->uid);
			leave_flag = 1;
		} else {
			printf("ERROR: -1\n");
			leave_flag = 1;
		}

		bzero(buff_out, BUFFER_SZ);
		//sleep(1);
		//pthread_mutex_unlock(&output_mutex);
	}

  /* Delete client from queue and yield thread */
	close(cli->sockfd);
  queue_remove(cli->uid);
  free(cli);
  cli_count--;
  pthread_detach(pthread_self());

	return NULL;
}

int main(int argc, char **argv){

	struct sockaddr_in server_addr, client_addr;
    socklen_t addr_len;
    DIR *dir;
    int portNumber;
    //int on = 1;
    //int listen_sd;

    if (argc != 4 || ((threadPoolSize = atoi (argv[2]))) <= 0 || ((portNumber = atoi (argv[3]))) <= 0) {
        fprintf(stderr, "Usage: %s [directory] [threadPoolSize] [portnumber]\n", argv[0]);
        return 1;
    }

    struct stat st = {0};

		if (stat(argv[1], &st) == -1) {
		    mkdir(argv[1], 0700);
		}


    if((dir = opendir(argv[1])) == NULL){
        fprintf(stderr, "%s: invalid path\n",argv[1]);
        return 1;
    }
    closedir(dir);

    strcpy(pathName,argv[1]);

	//int port = atoi(argv[1]);
	int option = 1;
	int listenfd = 0, connfd = 0;
  struct sockaddr_in serv_addr;
  struct sockaddr_in cli_addr;
  pthread_t tid;

  /* Socket settings */
  listenfd = socket(AF_INET, SOCK_STREAM, 0);
  serv_addr.sin_family = AF_INET;
  serv_addr.sin_addr.s_addr = INADDR_ANY;
  serv_addr.sin_port = htons(portNumber);

  /* Ignore pipe signals */
	signal(SIGPIPE, SIG_IGN);

	if(setsockopt(listenfd, SOL_SOCKET,(SO_REUSEPORT | SO_REUSEADDR),(char*)&option,sizeof(option)) < 0){
		perror("ERROR: setsockopt failed");
    return EXIT_FAILURE;
	}

	/* Bind */
  if(bind(listenfd, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) < 0) {
    perror("ERROR: Socket binding failed");
    return EXIT_FAILURE;
  }

  /* Listen */
  if (listen(listenfd, threadPoolSize) < 0) {
    perror("ERROR: Socket listening failed");
    return EXIT_FAILURE;
	}


	while(1){
		socklen_t clilen = sizeof(cli_addr);
		connfd = accept(listenfd, (struct sockaddr*)&cli_addr, &clilen);

		/* Check if max clients is reached */
		if((cli_count) == threadPoolSize){
			printf("Max clients reached. Rejected: ");
			print_client_addr(cli_addr);
			printf(":%d\n", cli_addr.sin_port);
			close(connfd);
			continue;
		}

		printf("New client connected: ");
		print_client_addr(cli_addr);
		//printf(":%d\n", cli_addr.sin_port);

		/* Client settings */
		client_t *cli = (client_t *)malloc(sizeof(client_t));
		cli->address = cli_addr;
		cli->sockfd = connfd;
		cli->uid = uid++;
		//sprintf(cli->name, "Client %d\n", cli->uid);

		/* Add client to the queue and fork thread */
		queue_add(cli);
		pthread_create(&tid, NULL, &handle_client, (void*)cli);

		/* Reduce CPU usage */
		sleep(1);
	}

	pthread_mutex_destroy(&output_mutex);
	pthread_mutex_destroy(&output_mutex2);
	pthread_mutex_destroy(&output_mutex3);
	pthread_mutex_destroy(&output_mutex4);
	pthread_mutex_destroy(&output_mutex5);

	return EXIT_SUCCESS;
}