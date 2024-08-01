#include <assert.h>
#include <fcntl.h>
#include <string.h>
#include <stdio.h>
#include <unistd.h>
#include <errno.h>


int mydup(int oldfd)
{
    
    if (fcntl(oldfd, F_GETFL) < 0) {
        errno = EBADF;
        printf("Err message for mydup(oldfd) function: %s\n",strerror(errno));
        return -1;
    }

    int newfd = fcntl(oldfd, F_DUPFD, 0);

    if (newfd == -1) {
        return -1;
    }

    return newfd;

}


int mydup2(int oldfd, int newfd)
{
    
    if (fcntl(oldfd, F_GETFL) < 0) {
        errno = EBADF;
        printf("Err message for mydup2(oldfd, newfd) function: %s\n",strerror(errno));
        return -1;
    }


    if (oldfd == newfd) {
        return newfd; 
    }


    close(newfd); 
    
    int res = fcntl(oldfd, F_DUPFD, newfd);

    if (res == -1) {
        return -1;
    }

    return res;

}


int main(int argc, char *argv[]) {

    int fd1, fd2, fd3;
    off_t cur_pos1, cur_pos2, cur_pos3, cur_pos4;

    fd1 = open("part3.txt", O_WRONLY | O_CREAT, 0666);

    if(fd1 == -1) {
       printf( "Error opening file: errno: %d - %s\n", errno, strerror( errno ) );
       return -1;
    }

    printf("fd%d is an original file descriptor for part3.txt\n\n", fd1);


    // mydup(oldfd) part
    fd2 = mydup(fd1);

    if(fd2 == -1)
        return -1;

    printf("fd%d is created with mydup(oldfd) operation and it is copied file descriptor of fd%d\n",fd2,fd1);

    char str1[200] = "This string is written to the part3.txt file with copied file descriptor after mydup(oldfd) operation\n";
    size_t written = write(fd2, str1, strlen(str1));

    if(written == -1){
       printf( "Error writing text: errno: %d - %s\n", errno, strerror( errno ) );
       return -1;
    }
    
    printf("fd%d wrote its string to the part3.txt successfully\n\n",fd2);

    cur_pos1 = lseek(fd1,0,SEEK_CUR);
    cur_pos2 = lseek(fd2,0,SEEK_CUR);
    printf("fd%d (original file descriptor) offset value after writing string to file: %ld\n", fd1, cur_pos1);
    printf("fd%d (copied file descriptor of fd%d) offset value after writing string to file: %ld\n\n", fd2, fd1, cur_pos2);
    

    // mydup2(oldfd,newfd) part
    fd3 = mydup2(fd1,fd1+2);

    if(fd3 == -1)
        return -1;

    printf("fd%d is created with mydup2(oldfd,newfd) operation and it is copied file descriptor of fd%d\n",fd3,fd1);

    char str2[200] = "This string is written to the part3.txt file with copied file descriptor after mydup2(oldfd,newfd) operation\n";
    size_t written2 = write(fd3, str2, strlen(str2));

    if(written2 == -1){
       printf( "Error writing text: errno: %d - %s\n", errno, strerror( errno ) );
       return -1;
    }

    printf("fd%d wrote its string to the part3.txt successfully\n\n",fd3);

    cur_pos3 = lseek(fd1,0,SEEK_CUR);
    cur_pos4 = lseek(fd3,0,SEEK_CUR);
    printf("fd%d (original file descriptor) offset value after writing string to file: %ld\n", fd1, cur_pos3);
    printf("fd%d (copied file descriptor of fd%d) offset value after writing string to file: %ld\n", fd3, fd1, cur_pos4);
    
    
    close(fd1);
    close(fd2);
    close(fd3);
    
    return 0;

}


