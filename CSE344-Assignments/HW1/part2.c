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
        printf("Err message for mydup(oldfd) function: %s\n\n",strerror(errno));
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
        printf("Err message for mydup2(oldfd, newfd) function: %s\n\n",strerror(errno));
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
    
    int fd1, fd2, fd3, fd4, fd5;

    fd1 = open("part2.txt", O_CREAT | O_WRONLY, 0666);

    if(fd1 == -1) {
       printf( "Error opening file: errno: %d - %s\n", errno, strerror( errno ) );
       return -1;
    }
    
    printf("fd%d is an original file descriptor for part2.txt\n\n", fd1);


    // mydup(oldfd) part
    fd2 = mydup(fd1);

    if(fd2 == -1)
        return -1;

    printf("fd%d is a copied file descriptor of fd%d after mydup(oldfd) operation\n",fd2,fd1);

    char str1[200] = "This string is written to the part2.txt file with copied file descriptor after mydup(oldfd) operation\n";
    size_t written = write(fd2, str1, strlen(str1));

    if(written == -1){
       printf( "Error writing text: errno: %d - %s\n", errno, strerror( errno ) );
       return -1;
    }

    printf("fd%d write its string to the part2.txt successfully\n\n",fd2);
    

    // mydup2(oldfd,newfd) part
    fd3 = mydup2(fd1 , fd1+2);

    if(fd3 == -1)
        return -1;

    printf("fd%d is a copied file descriptor of fd%d after mydup2(oldfd,newfd) operation\n",fd3,fd1);

    char str2[200] = "This string is written to the part2.txt file with copied file descriptor after mydup2(oldfd,newfd) operation\n";
    size_t written2 = write(fd3, str2, strlen(str2));

    if(written2 == -1){
       printf( "Error writing text: errno: %d - %s\n", errno, strerror( errno ) );
       return -1;
    }
    
    printf("fd%d write its string to the part2.txt successfully\n\n",fd3);
    

    printf("In order to test EBADF error in mydup(oldfd) function, I send an unused file descriptor number to function\n");
    fd4 = mydup(10); // In order to test error case for mydup(oldfd) function

    printf("In order to test EBADF error in mydup2(oldfd,newfd) function, I send an unused file descriptor numbers to function\n");
    fd5 = mydup2(15,15); // In order to test error case for mydup2(oldfd, newfd) function


    close(fd1);
    close(fd2);
    close(fd3);
    close(fd4);
    close(fd5);

    return 0;
    
}
