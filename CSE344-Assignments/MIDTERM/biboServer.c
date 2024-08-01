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
int client_count = 0;

char server_connect_fifo[MAX_BUF_SIZE];
char server_command_fifo[MAX_BUF_SIZE];
char client_connect_fifo[MAX_BUF_SIZE];
char client_command_fifo[MAX_BUF_SIZE];
FILE *fp;
pid_t pids[10000];


char server_command_arr[1000][1000];


void handle_signal(int signal){
    if (signal == SIGINT) {
        int i;
        
        unlink(server_connect_fifo);
        
        for(i=0;i<client_count;i++){
            unlink(server_command_arr[i]);
        }
        
        fclose(fp);

        for(i=0;i<client_count;i++){
            kill(pids[i],SIGTERM);
        }
    }
    exit(0);
}

void client_handle(char request[10000],struct response *resp){

    int i = 0;
    char firstCommandWord[100];

    while(1){
        if(request[i] == ' '){
            firstCommandWord[i] = '\0';
            break;
        }
        firstCommandWord[i] = request[i];
        i++;
    }


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

    else if(strcmp(firstCommandWord,"readF") == 0) {
        

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

    else if(strcmp(firstCommandWord,"writeT") == 0){

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

    else if(strcmp(firstCommandWord,"upload") == 0){
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

    else if(strcmp(firstCommandWord,"download") == 0){
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


int main(int argc, char* argv[]) {
    if (argc < 3) {
        printf("Usage: %s <directory_name> <max_clients>\n", argv[0]);
        return 1;
    }

    char* directory_name = argv[1];
    int max_clients = atoi(argv[2]);
    pid_t pids[max_clients];
    int i;


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

    
    snprintf(server_connect_fifo, sizeof(server_connect_fifo), "/tmp/server_connect_fifo.%ld", (long)getpid());
    if (mkfifo(server_connect_fifo, 0666) == -1) {
        if (errno != EEXIST) {
            perror("mkfifo");
            return 1;
        }
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
    sigaction(SIGINT, &act, NULL);

    key_t key = ftok(".", 34);
	int *arr;
	
	int shmid = shmget(key,sizeof(int)*1,0666|IPC_CREAT);

	arr = (int *)shmat(shmid, NULL, 0);
    arr[0] = 0;

    int key2 = ftok(".", 34);
	int *arr2;
	
	int shmid2 = shmget(key2,sizeof(int)*1,0666|IPC_EXCL);

	arr2 = shmat(shmid2, NULL, 0);

    key_t key3 = ftok(".", 65);
	int *arr3;
	
	int shmid3 = shmget(key3,sizeof(int)*1,0666|IPC_CREAT);

	arr3 = (int *)shmat(shmid3, NULL, 0);
    arr3[0] = 0;

    int key4 = ftok(".", 65);
	int *arr4;
	
	int shmid4 = shmget(key4,sizeof(int)*1,0666|IPC_EXCL);

	arr4 = shmat(shmid4, NULL, 0);

    key_t key5 = ftok(".", 96);
	int *arr5;
	
	int shmid5 = shmget(key5,sizeof(int)*1,0666|IPC_CREAT);

	arr5 = (int *)shmat(shmid5, NULL, 0);
    arr5[0] = 0;

    int key6 = ftok(".", 96);
	int *arr6;
	
	int shmid6 = shmget(key6,sizeof(int)*1,0666|IPC_EXCL);

	arr6 = shmat(shmid6, NULL, 0);

    
    
    int count = 0;
    sem_t *sem;
    sem = sem_open ("pSem", O_CREAT | O_EXCL, 0644, 1); 
    sem_unlink("pSem");


    struct request req;
    struct request req2;
    struct response resp;
    struct response resp2;
    int client_fd2;
    int clientNum;
    
    printf(">> waiting for clients\n");


    while (1) {
        
        
        int server_fd = open(server_connect_fifo, O_RDONLY);
        if (server_fd == -1) {
            perror("open");
            return 1;
        }
        
        
        read(server_fd,&req,sizeof(struct request));
        close(server_fd);
        client_count++;

                pids[client_count-1] = fork();
            

                if (pids[client_count-1] == -1) {
                    perror("fork");
                    continue;
                } 
                else if (pids[client_count-1] == 0) {
                
                    count = 0;
                    
                    
                    while(arr2[0] == max_clients){
                        if(count == 0){
                            printf("Connection request PID %d Que FULL\n",req.pid);
                            count = 1;
                        }
                        if(strcmp(req.message,"tryconnect")==0){
                            snprintf(client_connect_fifo, sizeof(client_connect_fifo),"/tmp/client_connect_fifo.%ld", (long)req.pid);
                            int client_fd = open(client_connect_fifo, O_WRONLY);
                            resp.pid = req.pid;
                            strcpy(resp.message,"Exit");
                            write(client_fd,&resp,sizeof(struct response));
                            close(client_fd);
                            exit(0);
                        }
                        sem_wait(sem);
                    
                        while(arr4[0] == 0){

                        }
                    
                        if(arr4[0] == 1){
                            arr3[0] = 0;
                        }
                    
                        sem_post(sem);
                    }
                
                
                    arr5[0]++;
                    clientNum = arr6[0];
                    arr[0]++;
                    printf(">> Client PID %d connected as client%d\n",req.pid,arr6[0]);
                    snprintf(client_connect_fifo, sizeof(client_connect_fifo),"/tmp/client_connect_fifo.%ld", (long)req.pid);
                    int client_fd = open(client_connect_fifo, O_WRONLY);
                    resp.pid = req.pid;
                    strcpy(resp.message,"Success");
                    write(client_fd,&resp,sizeof(struct response));
                    close(client_fd);
                    
                    snprintf(server_command_fifo, sizeof(server_command_fifo), "/tmp/server_command_fifo.%ld", (long)req.pid);
                    if (mkfifo(server_command_fifo, 0666) == -1) {
                        if (errno != EEXIST) {
                            perror("mkfifo");
                            return 1;
                        }
                    }
                    strcpy(server_command_arr[clientNum-1],server_command_fifo);
                    
                    snprintf(client_command_fifo, sizeof(client_command_fifo), "/tmp/client_command_fifo.%ld", (long)req.pid);


                while (1) {
                    
                    int server_fd2 = open(server_command_fifo, O_RDONLY);
                    if (server_fd2 == -1) {
                        perror("open");
                        return 1;
                    }
                    if (read(server_fd2, &req2, sizeof(struct request)) == -1) {
                        perror("read");
                        break;
                    }
                    close(server_fd2);

                    resp2.pid = req2.pid;
                    client_handle(req2.message,&resp2);
                    log_client_process((long)req2.pid,req2.message,resp2.message);
                    client_fd2 = open(client_command_fifo, O_WRONLY);
                    if (client_fd2 == -1) {
                        perror("open");
                        continue;
                    }
                    write(client_fd2,&resp2,sizeof(struct response));
                    close(client_fd2);
                    
                    if(strcmp(req2.message,"quit")==0){
                       break;
                    }

                    else if(strcmp(req2.message,"Ctrl C")==0){
                       printf("Client %d disconnected\n",clientNum);
                       kill(getpid(),SIGTERM);
                    }

                    else if(strcmp(req2.message,"killServer")==0){
                       
                       printf("kill signal from client %d.. terminating...\nbye\n",arr6[0]);
                       unlink(server_connect_fifo);
                       
                       for(i=0;i<arr6[0];i++){
                           unlink(server_command_arr[i]);
                       }
                       
                       shmdt((void *) arr);
                       shmctl(shmid, IPC_RMID, NULL);
                       shmdt((void *) arr2);
                       shmctl(shmid2, IPC_RMID, NULL);
                       shmdt((void *) arr3);
                       shmctl(shmid3, IPC_RMID, NULL);
                       shmdt((void *) arr4);
                       shmctl(shmid4, IPC_RMID, NULL);
                       shmdt((void *) arr5);
                       shmctl(shmid5, IPC_RMID, NULL);
                       shmdt((void *) arr6);
                       shmctl(shmid6, IPC_RMID, NULL);
                       sem_destroy(sem);
                       
                       for(i=0;i<client_count;i++){
                           kill(pids[i],SIGTERM);
                       }
                       exit(0);
                    }


                }
                
                arr3[0] = 1;
                printf(">> Client %d disconnected\n",clientNum);
                arr[0]--;
                exit(0);
            } 

            }
            
    

    for(i=0;i<client_count;i++){
        wait(NULL);
    }

    return 0;
}


