#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <signal.h>
#include <errno.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/ipc.h> 
#include <sys/shm.h> 
#include <semaphore.h>
#include <dirent.h>

struct request { /* Request (client --> server) */
 pid_t pid; /* PID of client */
 char message[10000]; 
};

struct response { /* Request (client --> server) */
 pid_t pid; /* PID of client */
 char message[10000]; 
};


#define MAX_BUF_SIZE 1024

char server_connect_fifo[MAX_BUF_SIZE];
char server_command_fifo[MAX_BUF_SIZE];
char client_connect_fifo[MAX_BUF_SIZE];
char client_command_fifo[MAX_BUF_SIZE];
int server_fd;
int client_fd;
int server_fd2;
int client_fd2;
char clientFilename[MAX_BUF_SIZE];
FILE *fp;


void handle_signal(int sig) {

        if(sig == SIGINT)
            write(STDOUT_FILENO,"\nReceived SIGINT signal\n",strlen("\nReceived SIGINT signal\n"));
        else if(sig == SIGTSTP)
            write(STDOUT_FILENO,"\nReceived SIGTSTP signal\n",strlen("\nReceived SIGTSTP signal\n"));
        else if(sig == SIGQUIT)
            write(STDOUT_FILENO,"\nReceived SIGQUIT signal\n",strlen("\nReceived SIGQUIT signal\n"));
    
        struct request req2;
        strcpy(req2.message,"Signal is received");
        req2.pid = getpid();
        server_fd2 = open(server_command_fifo, O_WRONLY);
        write(server_fd2, &req2, sizeof(struct request));
        struct response resp2;
        client_fd2 = open(client_command_fifo, O_RDONLY);
        read(client_fd2, &resp2, sizeof(struct response));

            
        close(server_fd);
        close(client_fd);
        close(server_fd2);
        close(client_fd2);
        unlink(client_connect_fifo);
        unlink(client_command_fifo);
        fclose(fp);
        exit(0);
    
}

void create_fifo(char* fifo_name) {
    int ret = mkfifo(fifo_name, 0666);
    if (ret == -1) {
        perror("mkfifo error");
        exit(1);
    }
}

void log_client_process(long pid, char *request, char *response) {
    fprintf(fp, "PID: %ld ", pid);
    fprintf(fp, "Request: %s ", request);
    fprintf(fp, "Response: %s\n", response);
}


int main(int argc, char** argv) {

    if (argc < 3 || argc > 3) {
        printf("Usage: %s <Connect/tryConnect> <Server PID>\n", argv[0]);
        exit(1);
    }

    struct sigaction act;
    act.sa_handler = handle_signal;
    sigemptyset(&act.sa_mask);
    act.sa_flags = 0;
    sigaction(SIGINT, &act, NULL);
    sigaction(SIGTSTP, &act, NULL);
    sigaction(SIGQUIT, &act, NULL);

    snprintf(clientFilename,sizeof(clientFilename), "client%ld_log.txt", (long)getpid());
    fp = fopen(clientFilename, "w");

    if (fp == NULL) {
        perror("fopen");
        return 1;
    }

    char* request_type = argv[1];
    pid_t server_pid = atoi(argv[2]);
    
    // Create client FIFO
    snprintf(client_connect_fifo,sizeof(client_connect_fifo), "/tmp/client_connect_fifo.%ld", (long)getpid());
    create_fifo(client_connect_fifo);

    // Create client FIFO
    snprintf(client_command_fifo, sizeof(client_command_fifo),"/tmp/client_command_fifo.%ld", (long)getpid());
    create_fifo(client_command_fifo);

    // Send request to server
    struct request req;
    req.pid = getpid();
    strcpy(req.message,request_type);
    // Connect to server FIFO
    snprintf(server_connect_fifo,sizeof(server_connect_fifo), "/tmp/server_connect_fifo.%ld", (long)server_pid);
    snprintf(server_command_fifo,sizeof(server_command_fifo), "/tmp/server_command_fifo.%ld", (long)getpid());
    server_fd = open(server_connect_fifo, O_WRONLY);
    if (server_fd == -1) {
        perror("open server FIFO error");
        exit(1);
    }
    write(server_fd, &req, sizeof(struct request));
    close(server_fd);

    // Wait for server response
    client_fd = open(client_connect_fifo, O_RDONLY);
    if (client_fd == -1) {
        perror("open client FIFO error");
        exit(1);
    }

    struct response resp;
    read(client_fd, &resp, sizeof(struct response));
    close(client_fd);

    if(strcmp(resp.message,"Exit")==0){
         printf("Que FULL.Client leaves without waiting\n");
         unlink(client_connect_fifo);
         unlink(client_command_fifo);
         fclose(fp);
         exit(0);
    }


    // Handle requests
    while (1) {

        printf(">>Enter command:");
        char input[MAX_BUF_SIZE];
        fgets(input, MAX_BUF_SIZE, stdin);

        // Remove newline character from input
        int len = strlen(input);
        if (input[len - 1] == '\n') {
            input[len - 1] = '\0';
        }
        
        // Send request to server
        struct request req2;
        strcpy(req2.message,input);
        req2.pid = getpid();
        char temp[1000];
        int count = 0;
        char firstCommandWord[100];
        char secondCommandWord[100];
        strcpy(temp,input);
        char *token = strtok(input, " ");
        while (token != NULL) {
            count++;
            if(count == 1)
                strcpy(firstCommandWord,token);
            else if(count == 2)
                strcpy(secondCommandWord,token);
            token = strtok(NULL, " ");
        }

        if(count == 2 && strcmp(firstCommandWord,"upload") == 0){

            key_t key = ftok("shmfile",65);
  
        // shmget returns an identifier in shmid
            int shmid = shmget(key,1000000000,0666|IPC_CREAT);
      
        // shmat to attach to shared memory
            char *str = (char*) shmat(shmid,(void*)0,0);
            
            char filename[100];
            strcpy(filename,secondCommandWord);
            int index = 0;

            int fd;
            
            fd = open(filename,O_RDONLY);
            if(fd == -1){
                printf("Given file can not be opened in client directory\n");
                continue;
            }
            
            while(read(fd,&str[index],1)){
                index++;
            }
            
            str[index] = '\0';
            
        }


        server_fd2 = open(server_command_fifo, O_WRONLY);

        if (server_fd2 == -1) {
            perror("open server FIFO error");
            exit(1);
        }
        write(server_fd2, &req2, sizeof(struct request));
        close(server_fd2);

        // Wait for server response
        struct response resp2;
        client_fd2 = open(client_command_fifo, O_RDONLY);
        read(client_fd2, &resp2, sizeof(struct response));
        close(client_fd2);
        
        log_client_process(getpid(),req2.message,resp2.message);

        if(strcmp(resp2.message,"readF command return the wanted line of file successfully\n") == 0){
            key_t key = ftok("shmfile",65);
  
        // shmget returns an identifier in shmid
            int shmid = shmget(key,1000000000,0666|IPC_CREAT);
      
        // shmat to attach to shared memory
            char *str = (char*) shmat(shmid,(void*)0,0);
            
            printf("%s\n",str);
            shmdt((void *) str);
            shmctl(shmid, IPC_RMID, NULL);
        }

        else if(count == 2 && strcmp(firstCommandWord,"download") == 0 && strcmp(resp2.message,"download file is successfully completed\n") == 0){
            
            key_t key = ftok("shmfile",65);
  
        // shmget returns an identifier in shmid
            int shmid = shmget(key,1000000000,0666|IPC_CREAT);
      
        // shmat to attach to shared memory
            char *str = (char*) shmat(shmid,(void*)0,0);
            char filename[100];
            strcpy(filename,secondCommandWord);
            int index = 0;
            int count = 0;

            int fd;
            
            fd = open(filename,O_WRONLY | O_CREAT | O_EXCL, 0644);
            if(fd == -1){
                printf("Filename is already exists in client directory\n");
                shmdt((void *) str);
                shmctl(shmid, IPC_RMID, NULL);
                continue;
            }
            
            while(str[count] != '\0'){
                write(fd,&str[count],1);
                count++;
            }

            shmdt((void *) str);
            shmctl(shmid, IPC_RMID, NULL);

            printf("%s", resp2.message);
            
        }


        // Check if client wants to quit
        else if (strcmp(resp2.message, "quit") == 0 || strcmp(resp2.message,"killServer") == 0) {
            if(strcmp(resp2.message,"quit") == 0){
            printf("Sending write request to server log file\nwaiting for logfile ...\nlogfile write request granted\nbye.\n");
            }
            close(server_fd);
            close(client_fd);
            close(server_fd2);
            close(client_fd2);
            unlink(client_connect_fifo);
            unlink(client_command_fifo);
            fclose(fp);
            exit(0);
        }

        else{
            printf("%s", resp2.message);
        }

        
    }

    return 0;
    
}


