#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <time.h>

#define MAX_ARGS 20 
#define MAX_COMMANDS 20 
#define MAX_INPUT 1000

volatile int control_flag=0;
pid_t pids[MAX_COMMANDS];
int command_count = 0;


void handle_signal(int signum) {

    for (int i = 0; i < command_count; i++) {
        kill(pids[i], SIGKILL);
    }

    switch (signum) {
        case SIGINT:
            write(STDOUT_FILENO,"\nReceived SIGINT signal\n",strlen("\nReceived SIGINT signal\n"));
            control_flag = 1;
            break;

        case SIGTERM:
            write(STDOUT_FILENO,"\nReceived SIGTERM signal\n",strlen("\nReceived SIGTERM signal\n"));
            control_flag = 1;
            break;

        case SIGKILL:
            write(STDOUT_FILENO,"\nReceived SIGKILL signal\n",strlen("\nReceived SIGKILL signal\n"));
            break;

        case SIGTSTP:
            write(STDOUT_FILENO,"\nReceived SIGTSTP signal\n",strlen("\nReceived SIGTSTP signal\n"));
            control_flag = 1;
            break;

        case SIGQUIT:
            write(STDOUT_FILENO,"\nReceived SIGQUIT signal\n",strlen("\nReceived SIGQUIT signal\n"));
            control_flag = 1;
            break;
            
        default:
            write(STDOUT_FILENO,"\nReceived signal\n",strlen("\nReceived signal\n"));
    }
    
}


void log_child_process(int pid, char* command) {
    time_t t = time(NULL);
    struct tm tm = *localtime(&t);
    char filename[100];
    sprintf(filename, "%d-%02d-%02d-%02d-%02d-%02d.log", tm.tm_year + 1900, tm.tm_mon + 1, tm.tm_mday, tm.tm_hour, tm.tm_min, tm.tm_sec);
    FILE* fp = fopen(filename, "a");
    fprintf(fp, "PID: %d\nCommand: %s\n", pid, command);
    fclose(fp);
}


int main(int argc, char* argv[]) {

    if(argc > 1){
        perror("Usage like this: ./hw2\n");
        exit(0);
    }
    
    struct sigaction sa;
    memset (&sa, 0, sizeof (sa));
    sa.sa_handler = handle_signal;
    sigaction(SIGINT, &sa, NULL);
    sigaction(SIGTERM, &sa, NULL);
    sigaction(SIGKILL, &sa, NULL);
    sigaction(SIGQUIT, &sa, NULL);
    sigaction(SIGTSTP, &sa, NULL);


    char input[MAX_INPUT];
    char *args[MAX_ARGS];
    char *commands[MAX_COMMANDS];
    int command_index = 0;
    char *input_file = NULL;
    char *output_file = NULL;
    int i,j;

    
    while(1){

        command_index = 0;
        printf("$ ");
        fgets(input, MAX_INPUT, stdin);
        input[strlen(input) - 1] = '\0'; 

        if(strcmp(input,":q")==0){
            break;
        }

        commands[command_index] = strtok(input, "|");
        while (commands[command_index] != NULL) {
            command_index++;
            commands[command_index] = strtok(NULL, "|");
        }

        int command_num = command_index;
        command_count = command_index;
        int pipes[(command_num-1)*2];
        
        for(i = 0 ; i < command_num-1 ; i++){
            if (pipe(pipes + (i*2)) < 0) {
                perror("pipe error");
                exit(1);
            }
        }
        

        if(control_flag == 1){
            
            for (int i = 0; i < (command_num-1)* 2; i++) {
                close(pipes[i]);
            }
            
            command_index = 0;
            input_file =NULL;
            output_file = NULL;
            control_flag = 0;
            continue;
            
        }

        
        for (i = 0; i < command_num; i++) {
            int arg_index = 0;
            args[arg_index] = strtok(commands[i], " ");
            while (args[arg_index] != NULL) {
                arg_index++;
                args[arg_index] = strtok(NULL, " ");
            }


            int arg_num = arg_index;
            input_file = NULL;
            output_file = NULL;
            for (j = 0; j < arg_num; j++) {
                if (strcmp(args[j], "<") == 0) {
                    args[j] = NULL;
                    input_file = args[j+1];
                } 
                else if (strcmp(args[j], ">") == 0) {
                    args[j] = NULL;
                    output_file = args[j+1];
                }
            }


            pids[i] = fork();
            if (pids[i] < 0) {
                perror("fork error");
                exit(1);
            } 

            else if (pids[i] == 0) {

                if (input_file != NULL) {
                    int fd_input = open(input_file, O_RDONLY);
                    if (fd_input < 0) {
                        perror("open error");
                        exit(1);
                    }
                    dup2(fd_input, STDIN_FILENO);
                    close(fd_input);
                } 

                else if (i > 0) {
                    dup2(pipes[(i*2)-2], STDIN_FILENO);
                    close(pipes[(i*2)-2]);
                    close(pipes[(i*2)-1]);
                }
                
                if (output_file != NULL) {
                    int fd_output = open(output_file, O_WRONLY | O_CREAT | O_TRUNC, 0666);
                    if (fd_output < 0) {
                        perror("open error");
                        exit(1);
                    }
                    dup2(fd_output, STDOUT_FILENO);
                    close(fd_output);
                } 
                else if (i < command_num - 1) {
                    dup2(pipes[(i*2)+1], STDOUT_FILENO);
                    close(pipes[(i*2)+1]);
                    close(pipes[(i*2)]);
                }

                log_child_process(getpid(), args[0]);

                if (execvp(args[0], args) < 0) {
                    perror("exec error");
                    exit(1);
                }
            } 
            
            else {

                if (i > 0) {
                    
                    close(pipes[(i*2)-2]);
                    close(pipes[(i*2)-1]);
                }

           }

        }


            for (int i = 0; i < (command_num-1)* 2; i++) {
                close(pipes[i]);
            }

            for (i = 0; i < command_num; i++) {
                
                int status;
                wait(&status);
                if (WIFEXITED(status)) {
                    int exit_status = WEXITSTATUS(status);
                    if (exit_status != 0) {
                        perror("Please enter true command\n");
                    } 
                } 
                else if (WIFSIGNALED(status)) {
                    int sig = WTERMSIG(status);
                    printf("Command terminated by signal: %d\n", sig);
                    
                } 
                else {
                    perror("Command did not terminate normally\n");
                }
                
            }


            if(control_flag == 1){
                for (int i = 0; i < (command_num-1)* 2; i++) {
                     close(pipes[i]);
                }
                
                command_index = 0;
                input_file =NULL;
                output_file = NULL;
                control_flag = 0;
                continue;
                
            }
            
        }

            return 0;

        }

