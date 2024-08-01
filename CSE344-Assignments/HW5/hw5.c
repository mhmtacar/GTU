#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <dirent.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <sys/wait.h>

#define MAX_PATH_LENGTH 1024
// Structure to hold the information about each file
typedef struct {
    int src_fd;
    int dest_fd;
    char src_file[MAX_PATH_LENGTH];
    char dest_file[MAX_PATH_LENGTH];
} FileInfo;

// Global variables
FileInfo *buffer;
int buffer_size;
int buffer_count = 0;
int buffer_in = 0;
int buffer_out = 0;
int done = 0;
long total_bytes_copied = 0;
int num_files_copied = 0;
int num_directories_copied = 0;
int num_fifos_copied = 0;

pthread_mutex_t buffer_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t output_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t buffer_not_empty = PTHREAD_COND_INITIALIZER;
pthread_cond_t buffer_not_full = PTHREAD_COND_INITIALIZER;

pthread_t producer_thread;
pthread_t *consumer_threads;
int num_consumers;

void handle_signal(int sig){

    done = 1;
    pthread_cond_broadcast(&buffer_not_empty);

    
    pthread_join(producer_thread, NULL);
    for (int i = 0; i < num_consumers; i++)
        pthread_join(consumer_threads[i], NULL);
        
    free(buffer);
    free(consumer_threads);
    if(sig == SIGINT)
        write(STDOUT_FILENO,"\nReceived SIGINT signal\n",strlen("\nReceived SIGINT signal\n"));
    else if(sig == SIGTSTP)
        write(STDOUT_FILENO,"\nReceived SIGTSTP signal\n",strlen("\nReceived SIGTSTP signal\n"));
    else if(sig == SIGQUIT)
        write(STDOUT_FILENO,"\nReceived SIGQUIT signal\n",strlen("\nReceived SIGQUIT signal\n"));

    pthread_mutex_destroy(&buffer_mutex);
    pthread_mutex_destroy(&output_mutex);
    pthread_cond_destroy(&buffer_not_empty);
    pthread_cond_destroy(&buffer_not_full);

    exit(0);

}

// Function to copy file from source to destination
void copy_file(FileInfo *info) {

    if(info->src_fd == -1 && info->dest_fd == -1){
        mkfifo(info->dest_file,0666);
        num_fifos_copied++;
    }

    else{
    char buffer[1024];
    ssize_t bytes_read, bytes_written;
    
    // Copy contents from source file to destination file
    while ((bytes_read = read(info->src_fd, buffer, sizeof(buffer))) > 0) {
        bytes_written = write(info->dest_fd, buffer, bytes_read);
        if (bytes_written != bytes_read) {
            pthread_mutex_lock(&output_mutex);
            fprintf(stderr, "Failed to copy file '%s'\n", info->src_file);
            pthread_mutex_unlock(&output_mutex);
            break;
        }
        total_bytes_copied += bytes_written;
    }
    
    // Close the file descriptors
    close(info->src_fd);
    close(info->dest_fd);
    num_files_copied++;
    }
    
    // Print completion status
    pthread_mutex_lock(&output_mutex);
    printf("Copied file: %s\n", info->src_file);
    pthread_mutex_unlock(&output_mutex);
}

// Recursive function to copy files and directories
void add_buffer(const char *src_dir, const char *dest_dir) {
    DIR *dir;
    struct dirent *entry;
    
    // Open the source directory
    dir = opendir(src_dir);
    if (dir == NULL) {
        pthread_mutex_lock(&output_mutex);
        fprintf(stderr, "Error opening source directory: %s\n", src_dir);
        pthread_mutex_unlock(&output_mutex);
        return;
    }
    
    struct stat st = {0};
    // Create the destination directory if it doesn't exist
    if(stat(dest_dir,&st) == -1)
        mkdir(dest_dir, 0777);
    
    // Traverse through the directory entries
    while ((entry = readdir(dir)) != NULL) {
        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0)
            continue;

        FileInfo file_info;
        
        // Create the source and destination paths
        sprintf(file_info.src_file, "%s/%s", src_dir, entry->d_name);
        sprintf(file_info.dest_file, "%s/%s", dest_dir, entry->d_name);
        
        // Check if the entry is a directory
        if (entry->d_type == DT_DIR) {
            num_directories_copied++;
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
            
            // Acquire the buffer mutex lock
            pthread_mutex_lock(&buffer_mutex);
            
            // Wait until buffer is not full
            while (buffer_count == buffer_size && !done)
                pthread_cond_wait(&buffer_not_full, &buffer_mutex);
            
            // Check if the producer is done
            if (done) {
                pthread_mutex_unlock(&buffer_mutex);
                break;
            }
            
            // Add file information to the buffer
            buffer[buffer_in] = file_info;
            buffer_in = (buffer_in + 1) % buffer_size;
            buffer_count++;
            
            // Signal that buffer is not empty
            pthread_cond_signal(&buffer_not_empty);
            
            // Release the buffer mutex lock
            pthread_mutex_unlock(&buffer_mutex);
        }
    }
    
    // Close the directory
    closedir(dir);
}

// Producer thread function
void *producer(void *arg) {
    char *src_dir = ((char **)arg)[0];
    char *dest_dir = ((char **)arg)[1];
    
    // Recursively copy files and directories
    add_buffer(src_dir, dest_dir);
    
    // Set the done flag and signal that buffer is not empty
    pthread_mutex_lock(&buffer_mutex);
    done = 1;
    pthread_cond_broadcast(&buffer_not_empty);
    pthread_mutex_unlock(&buffer_mutex);
    
    pthread_exit(NULL);
}

// Consumer thread function
void *consumer(void *arg) {
    while (1) {
        // Acquire the buffer mutex lock
        pthread_mutex_lock(&buffer_mutex);
        
        // Wait until buffer is not empty
        while (buffer_count == 0 && !done)
            pthread_cond_wait(&buffer_not_empty, &buffer_mutex);
        
        // Check if the consumer should exit
        if (done) {
            pthread_mutex_unlock(&buffer_mutex);
            break;
        }
        
        // Get file information from the buffer
        FileInfo *info = &buffer[buffer_out];
        buffer_out = (buffer_out + 1) % buffer_size;
        buffer_count--;

        // Copy the file
        copy_file(info);
        
        // Signal that buffer is not full
        pthread_cond_signal(&buffer_not_full);
        
        // Release the buffer mutex lock
        pthread_mutex_unlock(&buffer_mutex);
        
        // Print completion status
        pthread_mutex_lock(&output_mutex);
        printf("Completed: %s\n", info->dest_file);
        pthread_mutex_unlock(&output_mutex);
    
    }
    
    pthread_exit(NULL);
}

int main(int argc, char *argv[]) {
    // Check command-line arguments
    if (argc < 5) {
        fprintf(stderr, "Usage: %s <buffer_size> <num_consumers> <src_dir> <dest_dir>\n", argv[0]);
        return 1;
    }
    
    buffer_size = atoi(argv[1]);
    num_consumers = atoi(argv[2]);
    char *src_dir = argv[3];
    char *dest_dir = argv[4];
    
    // Initialize buffer size
    if (buffer_size <= 0) {
        fprintf(stderr, "Buffer size must be a positive integer\n");
        return 1;
    }
    
    // Initialize number of consumers
    if (num_consumers <= 0) {
        fprintf(stderr, "Number of consumers must be a positive integer\n");
        return 1;
    }

    struct sigaction act;
    act.sa_handler = handle_signal;
    sigemptyset(&act.sa_mask);
    act.sa_flags = 0;
    sigaction(SIGINT, &act, NULL);
    sigaction(SIGTSTP, &act, NULL);
    sigaction(SIGQUIT, &act, NULL);

    buffer = (FileInfo *)malloc(sizeof(FileInfo)*buffer_size);
    
    // Create producer thread
    //pthread_t producer_thread;
    char *args[2] = {src_dir, dest_dir};

    // Get start time
    struct timeval start_time;
    gettimeofday(&start_time, NULL);

    pthread_create(&producer_thread, NULL, producer, args);
    
    // Create consumer threads
    //pthread_t consumer_threads[num_consumers];
    consumer_threads = (pthread_t *)malloc(sizeof(pthread_t)*num_consumers);
    
    for (int i = 0; i < num_consumers; i++)
        pthread_create(&consumer_threads[i], NULL, consumer, NULL);
    
    
    // Wait for producer and consumer threads to complete
    pthread_join(producer_thread, NULL);
    for (int i = 0; i < num_consumers; i++)
        pthread_join(consumer_threads[i], NULL);
    
    // Get end time
    struct timeval end_time;
    gettimeofday(&end_time, NULL);
    
    // Calculate total time
    double total_time = (end_time.tv_sec - start_time.tv_sec) +
                        (end_time.tv_usec - start_time.tv_usec) / 1000000.0;
    
    // Display total time
    printf("\nTotal time taken: %.2lf seconds\n", total_time);
    printf("Number of files copied: %d\n", num_files_copied);
    printf("Number of directories copied: %d\n", num_directories_copied);
    printf("Number of fifos copied: %d\n", num_fifos_copied);
    printf("Total bytes copied: %ld\n", total_bytes_copied);
    
    // Cleanup mutex and condition variables
    pthread_mutex_destroy(&buffer_mutex);
    pthread_mutex_destroy(&output_mutex);
    pthread_cond_destroy(&buffer_not_empty);
    pthread_cond_destroy(&buffer_not_full);
    free(buffer);
    free(consumer_threads);
    
    return 0;
}

