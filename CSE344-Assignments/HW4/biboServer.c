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
#include <pthread.h>


struct request { /* Request (client --> server) */
 pid_t pid; /* PID of client */
 char message[10000]; 
};

struct response { /* Request (client --> server) */
 pid_t pid; /* PID of client */
 char message[10000]; 
};

#define MAX_BUF_SIZE 1024
int client_count = 0;

char server_connect_fifo[MAX_BUF_SIZE];
char client_connect_fifo[MAX_BUF_SIZE];
FILE *fp;
int client_pid;
int check = 0;

char server_command_arr[1000][1000];

int shmid5,shmid6;
int *arr5,*arr6;
int clientNum;

int clientCount = 0;

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex2 = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;
pthread_cond_t cond2 = PTHREAD_COND_INITIALIZER;


void handle_signal(int sig){
    
    int i;

    if(sig == SIGINT)
        write(STDOUT_FILENO,"\nReceived SIGINT signal\n",strlen("\nReceived SIGINT signal\n"));
    else if(sig == SIGTSTP)
        write(STDOUT_FILENO,"\nReceived SIGTSTP signal\n",strlen("\nReceived SIGTSTP signal\n"));
    else if(sig == SIGQUIT)
        write(STDOUT_FILENO,"\nReceived SIGQUIT signal\n",strlen("\nReceived SIGQUIT signal\n"));

        
    unlink(server_connect_fifo);
        
    for(i=0;i<client_count;i++){
        unlink(server_command_arr[i]);
    }
        
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

void client_handle(char request[10000],struct response *resp){


    if(strcmp(request,"help") == 0){
        strcpy(resp->message,"Available comments are :\nhelp, list, readF, writeT, upload, download, quit, killServer\n");
    }

    else if(strcmp(request,"help list") == 0){
        strcpy(resp->message,"list\nrequest to display the list of files in Servers directory\n");
    }

    else if(strcmp(request,"help readF") == 0){
        strcpy(resp->message,"readF <file> <line #>\ndisplay the #th line of the <file>, returns with an\nerror if <file> does not exists\n");
    }

    else if(strcmp(request,"help writeT") == 0){
        strcpy(resp->message,"writeT <file> <line #> <string>\nwrite the content of “string” to the #th line the <file> \n if the line # is not given writes to the end of file.\n");
    }

    else if(strcmp(request,"help upload") == 0){
        strcpy(resp->message,"upload <file>\nuploads the file from the current working directory of client to the Servers directory\n");
    }

    else if(strcmp(request,"help download") == 0){
        strcpy(resp->message,"download <file>\nrequest to receive <file> from Servers directory to client side\n");
    }

    else if(strcmp(request,"help quit") == 0){
        strcpy(resp->message,"quit\nSend write request to Server side log file and quits\n");
    }

    else if(strcmp(request,"help killServer") == 0){
        strcpy(resp->message,"killServer\nSends a kill request to the Server\n");
    }

    else if(strcmp(request,"list") == 0){
        DIR *dir;
        struct dirent *ent;
        char files[100000] = "";  // initialize empty string

        dir = opendir(".");
        if (dir == NULL) {
            perror("opendir");
            return;
        }

        while ((ent = readdir(dir)) != NULL) {
            if (ent->d_type == DT_REG) {
                strcat(files, ent->d_name);
                strcat(files, "\n");  // add newline between file names
            }
        }

        closedir(dir);

        strcpy(resp->message,files);
    }

    else if(strcmp(request,"quit") == 0){
        strcpy(resp->message,"quit");
    }

    else if(strcmp(request,"Ctrl C") == 0){
        strcpy(resp->message,"Ctrl C\n");
    }

    else if(strcmp(request,"killServer") == 0){
        strcpy(resp->message,"killServer");
    }

    else if(strstr(request, "readF")) {
        

        char *filename;
        int line_number = 0;
        int fd;
        char buffer[MAX_BUF_SIZE];
        int line_count = 0; // default to printing entire file if no line number provided
        int bytes_read;
        int i;
        char commandWords[1000][100];
        int count = 0;
        int index = 0;
        struct flock lock;
        

        key_t key = ftok("shmfile",65);
  
    // shmget returns an identifier in shmid
        int shmid = shmget(key,1000000000,0666|IPC_CREAT);
  
    // shmat to attach to shared memory
        char *str = (char*) shmat(shmid,(void*)0,0);
  

        char *token = strtok(request, " ");
        while (token != NULL) {
            count++;
            strcpy(commandWords[count-1],token);
            token = strtok(NULL, " ");
        }

        // check command line arguments
        if (count != 2 && count != 3) {
            strcpy(resp->message,"Usage: readF filename [line_number]\n");
            return;
        }

        // get filename
        filename = commandWords[1];

        // get line number (if provided)
        if (count == 3) {
            line_number = atoi(commandWords[2]);
        }


        fd = open(filename, O_RDONLY);
        if (fd == -1) {
            strcpy(resp->message,"File can not be opened\n");
            return;
        }


        memset(&lock,0,sizeof(lock));
        lock.l_type = F_RDLCK;
        fcntl(fd,F_SETLKW,&lock);

        while ((bytes_read = read(fd, buffer, MAX_BUF_SIZE)) > 0) {
            for (i = 0; i < bytes_read; i++) {
                if (line_number == 0 || line_count == line_number - 1) {
                    str[index] = buffer[i];
                    index++;
                }
                if (buffer[i] == '\n') {
                    line_count++;
                    if (line_number != 0 && line_count == line_number)
                        break;
                }
            }
            if (line_number != 0 && line_count == line_number)
                break;
        }

        strcpy(resp->message,"readF command return the wanted line of file successfully\n");
        lock.l_type = F_UNLCK;
        fcntl(fd,F_SETLKW,&lock);

        close(fd);

    }

    else if(strstr(request, "writeT")){

            char commandWords[100][1000];
            int count = 0;
            struct flock lock;

            
            char *token = strtok(request, " ");
            while (token != NULL) {
                strcpy(commandWords[count],token);
                token = strtok(NULL, " ");
                count++;
            }

            if (count != 3 && count != 4) {
                strcpy(resp->message,"Usage: writeT <file> [<line #>] <string>\n");
                return;
            }

            char *filename = commandWords[1];
            int fd;
            // Check if the file already exists
            if (access(filename, F_OK) != 0) {
                // File does not exist, create it
                fd = open(filename, O_CREAT | O_RDWR, 0644);
                if (fd == -1) {
                    strcpy(resp->message,"File can not be created\n");
                    return;
                }
            } else {
                // File already exists, open it
                fd = open(filename, O_RDWR);
                if (fd == -1) {
                    strcpy(resp->message,"Filename can not be opened\n");
                    return;
                }
            }

            memset(&lock,0,sizeof(lock));
            lock.l_type = F_WRLCK;
            fcntl(fd,F_SETLKW,&lock);

            int line_number = -1;
            if (count == 4) {
                line_number = atoi(commandWords[2]);
            }

            char *string = commandWords[count-1];
            char c;
            int n_lines = 0;
            
            // Count the number of lines in the file
            while (read(fd, &c, 1) > 0) {
                if (c == '\n') {
                    n_lines++;
                }
            }

            // If a line number is given, check if it's valid
            if (line_number > 0 && line_number <= n_lines) {
                // Move the file pointer to the start of the specified line
                lseek(fd, 0, SEEK_SET);
                for (int i = 1; i < line_number; i++) {
                    while (read(fd, &c, 1) > 0) {
                        if (c == '\n') {
                            break;
                        }
                    }
                }
            } else {
                // Move the file pointer to the end of the file
                lseek(fd, 0, SEEK_END);
                
            }

            // Write the string to the file
            write(fd, string, strlen(string));
            strcpy(resp->message,"Given string is writtten to the given file successfully\n");
            lock.l_type = F_UNLCK;
            fcntl(fd,F_SETLKW,&lock);
            close(fd);

    }

    else if(strstr(request, "upload")){
            char commandWords[100][1000];
            int count = 0;
            char filename[100];
            int fd;
            struct flock lock;
            key_t key = ftok("shmfile",65);
  
        // shmget returns an identifier in shmid
            int shmid = shmget(key,1000000000,0666|IPC_CREAT);
      
        // shmat to attach to shared memory
            char *str = (char*) shmat(shmid,(void*)0,0);

            
            char *token = strtok(request, " ");
            while (token != NULL) {
                strcpy(commandWords[count],token);
                token = strtok(NULL, " ");
                count++;
            }

            if (count != 2) {
                strcpy(resp->message,"Usage: upload <file>\n");
                shmdt((void *) str);
                shmctl(shmid, IPC_RMID, NULL);
                return;
            }

            strcpy(filename,commandWords[1]);

            fd = open(filename, O_WRONLY | O_CREAT | O_EXCL, 0644);

            if(fd == -1){
                strcpy(resp->message,"Filename is already exists in server directory\n");
                shmdt((void *) str);
                shmctl(shmid, IPC_RMID, NULL);
                return;
            }

            memset(&lock,0,sizeof(lock));
            lock.l_type = F_WRLCK;
            fcntl(fd,F_SETLKW,&lock);

            count = 0;

            while(str[count] != '\0'){
                write(fd,&str[count],1);
                count++;
            }
            

            shmdt((void *) str);
            shmctl(shmid, IPC_RMID, NULL);

            strcpy(resp->message,"File is uploaded successfully\n");
            lock.l_type = F_UNLCK;
            fcntl(fd,F_SETLKW,&lock);

            close(fd);


    }

    else if(strstr(request, "download")){
            char commandWords[100][1000];
            int count = 0;
            char filename[100];
            int fd;
            int index = 0;
            struct flock lock;
            key_t key = ftok("shmfile",65);
  
        // shmget returns an identifier in shmid
            int shmid = shmget(key,1000000000,0666|IPC_CREAT);
      
        // shmat to attach to shared memory
            char *str = (char*) shmat(shmid,(void*)0,0);
            
            char *token = strtok(request, " ");
            while (token != NULL) {
                strcpy(commandWords[count],token);
                token = strtok(NULL, " ");
                count++;
            }

            if (count != 2) {
                strcpy(resp->message,"Usage: download <file>\n");
                shmdt((void *) str);
                shmctl(shmid, IPC_RMID, NULL);
                return;
            }

            strcpy(filename,commandWords[1]);

            fd = open(filename, O_RDONLY);

            if(fd == -1){
                strcpy(resp->message,"Given file can not be opened in servers directory\n");
                shmdt((void *) str);
                shmctl(shmid, IPC_RMID, NULL);
                return;
            }

            memset(&lock,0,sizeof(lock));
            lock.l_type = F_RDLCK;
            fcntl(fd,F_SETLKW,&lock);

            while(read(fd,&str[index],1)){
                index++;
            }
            
            str[index] = '\0';

            strcpy(resp->message,"download file is successfully completed\n");
            lock.l_type = F_UNLCK;
            fcntl(fd,F_SETLKW,&lock);

            close(fd);


    }


}

void log_client_process(long pid, char *request, char *response) {
    fprintf(fp, "PID: %ld ", pid);
    fprintf(fp, "Request: %s ", request);
    fprintf(fp, "Response: %s\n", response);
}


void* startThread(void* args) {
        
        while(1){

        pthread_mutex_lock(&mutex2);
        while (check == 0) {
            pthread_cond_wait(&cond2, &mutex2);
        }
        check = 0;
        char server_command_fifo[MAX_BUF_SIZE];
        char client_command_fifo[MAX_BUF_SIZE];
        snprintf(server_command_fifo, sizeof(server_command_fifo), "/tmp/server_command_fifo.%ld", (long)client_pid);
        create_fifo(server_command_fifo);
        strcpy(server_command_arr[client_count-1],server_command_fifo);
        snprintf(client_command_fifo, sizeof(client_command_fifo), "/tmp/client_command_fifo.%ld", (long)client_pid);
        int clientnum = clientNum;
        
        pthread_mutex_unlock(&mutex2);

        
        while (1) {
                    struct request req;
                    struct response resp;
                    int request_fd = open(server_command_fifo, O_RDONLY);
                    if (request_fd == -1) {
                        perror("open");
                        exit(0);
                    }
                    read(request_fd, &req, sizeof(struct request));
                    close(request_fd);
                    resp.pid = req.pid;
                    client_handle(req.message,&resp);
                    log_client_process(req.pid,req.message,resp.message);
                    // Send response to the client
                    int response_fd = open(client_command_fifo, O_WRONLY);
                    if (response_fd == -1) {
                        perror("open");
                        exit(0);;
                    }
                    write(response_fd, &resp, sizeof(struct response));
                    close(response_fd);
                
                    if(strcmp(req.message,"quit")==0){
                       break;
                    }

                    else if(strcmp(req.message,"Signal is received")==0){
                        break;
                    }

                    else if(strcmp(req.message,"killServer")==0){
                       
                       printf("kill signal from client %d.. terminating...\nbye\n",clientnum);
                       unlink(server_connect_fifo);
                       int i;
                       for(i=0;i<client_count;i++){
                            unlink(server_command_arr[i]);
                        }
                       
                       shmdt((void *) arr5);
                       shmctl(shmid5, IPC_RMID, NULL);
                       shmdt((void *) arr6);
                       shmctl(shmid6, IPC_RMID, NULL);
                       exit(0);
                       
                    }


                }
                
                printf(">> Client %d disconnected\n",clientnum);
                pthread_mutex_lock(&mutex);
                --client_count;
                pthread_cond_signal(&cond);
                pthread_mutex_unlock(&mutex);
            }

                
    }



int main(int argc, char* argv[]) {
    if (argc != 4) {
        printf("Usage: %s <directory_name> <max_clients> <pool_size>\n", argv[0]);
        return 1;
    }

    char* directory_name = argv[1];
    int max_clients = atoi(argv[2]);
    int pool_size = atoi(argv[3]);
    pthread_t th[pool_size];
    int i;

    if(max_clients > pool_size){
        printf("Max client number should be equal or less than thread pool size\n");
        return -1;
    }

    struct stat st;
    char cwd[256];
    if (stat(directory_name, &st) == -1) {
        // directory does not exist, so create it
        if (mkdir(directory_name, 0700) == -1) {
            perror("mkdir");
            return 1;
        }
    }

    if (chdir(directory_name) == -1) {
        perror("chdir");
        return 1;
    }
    else{
    if (getcwd(cwd, sizeof(cwd)) == NULL)
        perror("getcwd() error");
    }

    

    fp = fopen("server_log.txt", "w");

    if (fp == NULL) {
        perror("fopen");
        return 1;
    }

    
    int server_pid = getpid();
    printf(">>Server Started PID: %d\n", getpid());

    struct sigaction act;
    act.sa_handler = handle_signal;
    sigemptyset(&act.sa_mask);
    act.sa_flags = 0;
    sigaction(SIGINT, &act, NULL);
    sigaction(SIGTSTP, &act, NULL);
    sigaction(SIGQUIT, &act, NULL);


    for (i = 0; i < pool_size; i++) {
        if (pthread_create(&th[i], NULL, &startThread, NULL) != 0) {
            perror("Failed to create the thread");
        }
    }


    snprintf(server_connect_fifo, sizeof(server_connect_fifo), "/tmp/server_connect_fifo.%ld", (long)getpid());
    
    
    create_fifo(server_connect_fifo);


    key_t key5 = ftok(".", 96);
    
    shmid5 = shmget(key5,sizeof(int)*1,0666|IPC_CREAT);

    arr5 = (int *)shmat(shmid5, NULL, 0);
    arr5[0] = 0;

    key_t key6 = ftok(".", 96);
    int *arr6;
    
    shmid6 = shmget(key6,sizeof(int)*1,0666|IPC_EXCL);

    arr6 = shmat(shmid6, NULL, 0);


    struct request req;
    struct response resp;
    int count = 0;
    int flag = 0;
    
    printf(">> waiting for clients\n");

    while (1) {
        
        flag = 0;
        int server_fd = open(server_connect_fifo, O_RDONLY);
        if (server_fd == -1) {
            perror("open");
            return -1;
        }
        
        
        read(server_fd,&req,sizeof(struct request));
        close(server_fd);
        count = 0;
        pthread_mutex_lock(&mutex);
        while (client_count >= max_clients) {
            flag = 1;
            if(count == 0){
                printf("Connection request PID %d Que FULL\n",req.pid);
                count = 1;
            }
            if(strcmp(req.message,"tryconnect")==0){
                snprintf(client_connect_fifo, sizeof(client_connect_fifo),"/tmp/client_connect_fifo.%ld", (long)req.pid);
                int client_fd = open(client_connect_fifo, O_WRONLY);
                if (client_fd == -1) {
                    perror("open");
                    return 1;
                }
                resp.pid = req.pid;
                strcpy(resp.message,"Exit");
                write(client_fd,&resp,sizeof(struct response));
                close(client_fd);
                break;
            }
            pthread_cond_wait(&cond, &mutex);
        }
        if(strcmp(req.message,"tryconnect")==0 && client_count >= max_clients){}

        else
            ++client_count;
        pthread_mutex_unlock(&mutex);

        
        if(strcmp(req.message,"tryconnect")==0 && flag == 1)
            continue;
        
       
        arr5[0]++;
        clientNum = arr6[0];
                    
        printf(">> Client PID %d connected as client%d\n",req.pid,arr6[0]);
        snprintf(client_connect_fifo, sizeof(client_connect_fifo),"/tmp/client_connect_fifo.%ld", (long)req.pid);
        int client_fd = open(client_connect_fifo, O_WRONLY);
        if (client_fd == -1) {
            perror("open");
            return 1;
        }
        resp.pid = req.pid;
        strcpy(resp.message,"Success");
        write(client_fd,&resp,sizeof(struct response));
        close(client_fd);

        client_pid = req.pid;

        pthread_mutex_lock(&mutex2);
        check = 1;
        pthread_cond_signal(&cond2);
        pthread_mutex_unlock(&mutex2);
                
                    
    }
    
        for (i = 0; i < pool_size; i++) {
            if (pthread_join(th[i], NULL) != 0) {
                perror("Failed to join the thread");
            }
        }
    

        pthread_mutex_destroy(&mutex);
        pthread_cond_destroy(&cond);
        pthread_mutex_destroy(&mutex2);
        pthread_cond_destroy(&cond2);

        return 0;

}


