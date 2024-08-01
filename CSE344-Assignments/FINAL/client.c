#include <stdio.h>
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

#define BUFFER_SIZE 1024

#define MAX_PATH_LENGTH 256
#define MAX_FILE_CONTENT 1024
#define MAX_CONTENT_LENGTH 1000

pthread_mutex_t output_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t output_mutex2 = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t output_mutex3 = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t output_mutex4 = PTHREAD_MUTEX_INITIALIZER;

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
    //file files[100];
    char type[10];
    char content[4096];
    char time[100];
} Info;

Info clientInfo[10000];
Info serverInfo[10000];
Info inf[100];
Info inf2[100];
int count = 0;
int count2 = 0;
char pathName[1024];
char filePath[2048];

volatile int done = 0;

file f[100];
    file f2[100];

#define LENGTH 2048

// Global variables
volatile sig_atomic_t flag = 0;
int sockfd = 0;
char name[32];

void writeLogTime(FILE *logfp){
    time_t t = time(NULL);
    struct tm tm = *localtime(&t);
    fprintf(logfp,"%02d-%02d-%02d  %02d:%02d:%02d \t", tm.tm_year + 1900, tm.tm_mon + 1, tm.tm_mday, tm.tm_hour, tm.tm_min, tm.tm_sec);
}


void writeLog(char *logMessage,char *logPath){

    FILE *logfp = fopen(logPath,"a");
    writeLogTime(logfp);
    fprintf(logfp, "%s\n",logMessage);
    fclose(logfp);

}

void readFile2(const char *fileName) {

    pthread_mutex_lock(&output_mutex);
    
    int fd = open(fileName, O_RDONLY);

    ssize_t bytes_read, bytes_written;
    
    // Copy contents from source file to destination file
    while ((bytes_read = read(fd, clientInfo[count].content, sizeof(clientInfo[count].content))) > 0) {
        //clientInfo[count].size = bytes_read;
    }
    
    // Close the file descriptors
    close(fd);
    
    
/*
    FILE *fptr;

    // Open a file in read mode
    fptr = fopen(fileName, "r");

    // Read the content and store it inside myString
    fgets(clientInfo[count].content, 4096, fptr);

    // Close the file
    fclose(fptr); 
    */
    pthread_mutex_unlock(&output_mutex);
    
}

void copyFile(Info info){
    pthread_mutex_lock(&output_mutex2);
    char file_path[2048];
    sprintf(file_path, "%s/%s", pathName, info.name);
    
    int fd = open(file_path,O_WRONLY | O_CREAT | O_TRUNC,0644);
    //int i = 0;
    //struct flock lock;
		//memset(&lock,0,sizeof(lock));
		//lock.l_type = F_WRLCK;
		//fcntl(fd,F_SETLKW,&lock);
    //while(info.content[i] != 0){
        write(fd,info.content,strlen(info.content));
        //i++;
    //}
    //lock.l_type = F_UNLCK;
    //fcntl(fd,F_SETLKW,&lock);
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
   pthread_mutex_unlock(&output_mutex2);

}

void updateClient(){

    int i,j;
    int check = 0;

    pthread_mutex_lock(&output_mutex3);
    for(i = 0;i < count; i++){
        clientInfo[i].isDeleted = 1;
        //for(j = 0;j < 100)
    }

    i = 0;

    if(serverInfo[0].name[0] != 0){

    while(strlen(serverInfo[i].name) != 0){

        check = 0;

        for(j=0;j<count;j++){
            if(strcmp(clientInfo[j].name,serverInfo[i].name) == 0){
                check = 1;
                clientInfo[j].isDeleted = 0;
                //printf("girdi\n");
                if(strcmp(clientInfo[j].time,serverInfo[i].time) != 0){
                    strcpy(clientInfo[j].time,serverInfo[i].time);
                    copyFile(serverInfo[i]);
                }
                break;
            }
        }

        if(check == 0){
            /*
            if(strcmp(serverInfo[i].type,"DIR") == 0){
                j = 0;
                strcpy(clientInfo[count].type,"DIR");
                strcpy(clientInfo[count].name,serverInfo[i].name);
                while(strcmp(serverInfo[i].files[j].name,"") != 0){
                    strcpy(clientInfo[count].files[j].name,serverInfo[i].files[j].name);
                    j++;
                }
            }
            */
             if(strcmp(serverInfo[i].type,"REG") == 0){
                strcpy(clientInfo[count].type,"REG");
                strcpy(clientInfo[count].name,serverInfo[i].name);
                strcpy(clientInfo[count].time,serverInfo[i].time);
                copyFile(serverInfo[i]);
                char message[2048];
                sprintf(message,"%s %s",serverInfo[i].name,"file is created");
                sprintf(filePath,"%s/%s",pathName,basename(pathName));
                writeLog(message,filePath);
            }

            //strcpy(client[count].name,dest_file);
            clientInfo[count].isDeleted = 0;
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
            sprintf(dir_name,"%s/%s",pathName,clientInfo[i].name);
            if(strcmp(clientInfo[i].type,"DIR") == 0){
                //printf("mehmet\n");
                rmdir(clientInfo[i].name);
            }
            else if(strcmp(clientInfo[i].type,"REG") == 0){
                unlink(clientInfo[i].name);
            }
        }
    }
*/
    pthread_mutex_unlock(&output_mutex3);
}

void traverseClient() {
    DIR *dir;
    DIR *dir2;
    struct dirent *entry;
    struct dirent *entry2;
    count = 0;
    int i,j;
    pthread_mutex_lock(&output_mutex4);
    for(i=0;i<10000;i++){
        strcpy(clientInfo[i].name,"");
        /*
        for(j=0;j<100;j++){
            strcpy(clientInfo[i].files[j].name,"");
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
                strcpy(clientInfo[count].type,"DIR");
                strcpy(clientInfo[count].name,entry->d_name);
                clientInfo[count].isDeleted = 0;
                dir2 = opendir(dir_name);
                if (dir2 == NULL) {
                    fprintf(stderr, "Error opening source directory: %s\n", clientPath);
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
                        strcpy(clientInfo[count].files[count2].name,entry2->d_name);
                        clientInfo[count].files[count2].isDeleted = 0;
                        count2++;
                        }
                    }

                }
                closedir(dir2);
            }
            */
             if(entry->d_type == DT_REG){
                if(strcmp(entry->d_name,basename(filePath)) != 0){
                struct stat attr;
                char path[2048];
                sprintf(path,"%s/%s",pathName,entry->d_name);
                stat(path, &attr);
                
                //printf("ali\n");
                strcpy(clientInfo[count].type,"REG");
                //clientInfo[count].name[0] = '\0';
                strcpy(clientInfo[count].name,entry->d_name);
                strcpy(clientInfo[count].time,ctime(&attr.st_mtime));
                readFile2(dir_name);
                count++;
                }
                //clientInfo[count].isDeleted = 0;
            //}

            //strcpy(serverInfo[count].name,entry);
            //serverInfo[count].isDeleted = 0;
            //printf("%s\n",entry->d_name);
            
            //copyDirectory(src_file,dest_dir);
        //}

        }

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
    //printf("ayse\n");
    /*
    printf("%d\n",count);
    for(int i = 0;i<count ; i++){
        printf("IsDeleted:%d\n",serverInfo[i].isDeleted);
    }
    */
    
    pthread_mutex_unlock(&output_mutex4);

}


void catch_ctrl_c_and_exit(int sig) {
    flag = 1;
}

void send_msg_handler() {

  while(1) {
  	//str_overwrite_stdout();
    //fgets(message, LENGTH, stdin);
    //str_trim_lf(message, LENGTH);

    //pthread_mutex_lock(&output_mutex);

  	traverseClient(pathName);
    send(sockfd, clientInfo, sizeof(clientInfo), 0);

    
    
		//bzero(clientInfo, sizeof(clientInfo));

        //pthread_mutex_unlock(&output_mutex);
   
  }
  catch_ctrl_c_and_exit(2);
}

void recv_msg_handler() {
	
  while (1) {
		int receive = recv(sockfd, serverInfo, sizeof(serverInfo), 0);
    if (receive > 0) {
      updateClient();
    } else if (receive == 0) {
			break;
    } else {
			// -1
		}
		//memset(serverInfo, 0, sizeof(serverInfo));
  }
}

int main(int argc, char **argv){

		int client_socket;
	  struct sockaddr_in server_addr;
	  int server_port;
	  char server_ip[1024];
	  DIR *dir;

	  if (argc < 3 || argc > 4 || argv[2] <= 0) {
        fprintf(stderr, "Usage: %s [dirName] [portnumber] [Optional IP Address]\n", argv[0]);
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

	signal(SIGINT, catch_ctrl_c_and_exit);

	strcpy(pathName,argv[1]);
  	server_port = atoi (argv[2]);

    if(argc == 3){
        strcpy(server_ip,"127.0.0.1");
    }

    else if(argc == 4){
        strcpy(server_ip,argv[3]);
    }

	//printf("Please enter your name: ");
  //fgets(name, 32, stdin);
  //str_trim_lf(name, strlen(name));

/*
	if (strlen(name) > 32 || strlen(name) < 2){
		printf("Name must be less than 30 and more than 2 characters.\n");
		return EXIT_FAILURE;
	}
	*/


	/* Socket settings */
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
  server_addr.sin_family = AF_INET;
  server_addr.sin_addr.s_addr = inet_addr(server_ip);
  server_addr.sin_port = htons(server_port);


  // Connect to Server
  int err = connect(sockfd, (struct sockaddr *)&server_addr, sizeof(server_addr));
  if (err == -1) {
		printf("ERROR: connect\n");
		return EXIT_FAILURE;
	}

	// Send name
	send(sockfd, pathName, 1024, 0);

    printf("Connected to server\n");
    sprintf(filePath,"%s/%s",pathName,basename(pathName));
    writeLog("Connected to server",filePath);

	pthread_t send_msg_thread;
  if(pthread_create(&send_msg_thread, NULL, (void *) send_msg_handler, NULL) != 0){
		printf("ERROR: pthread\n");
    return EXIT_FAILURE;
	}

	pthread_t recv_msg_thread;
  if(pthread_create(&recv_msg_thread, NULL, (void *) recv_msg_handler, NULL) != 0){
		printf("ERROR: pthread\n");
		return EXIT_FAILURE;
	}

	while (1){
		if(flag){
			printf("\nBye\n");
			break;
    }
	}

    pthread_mutex_destroy(&output_mutex);
    pthread_mutex_destroy(&output_mutex2);
    pthread_mutex_destroy(&output_mutex3);
    pthread_mutex_destroy(&output_mutex4);
	close(sockfd);

	return EXIT_SUCCESS;
}