#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <errno.h>

int main(int argc, char *argv[])
{
	int fd;
	int flags;
	char *filename;
	long num_bytes;
	int i;

	if (argc < 3 || argc > 4) {
		printf("Command line argument number should be 3 or 4\n");
		printf("Command line arguments should be like this and [x] is optional : %s <filename> <num-bytes> [x]\n", argv[0]);
		return -1;
	}

    filename = argv[1];

	if (argc == 3) {
		flags = (O_WRONLY | O_CREAT | O_APPEND);
	}
	else{
        flags = (O_WRONLY | O_CREAT);
	}

	fd = open(filename, flags, S_IRUSR | S_IWUSR);

	if (fd == -1) {
		printf("Could not open specified filename\n");
		return -1;
	}

	num_bytes = atol(argv[2]);

	for (i = 0; i < num_bytes; i++) {

		if (argc == 3) {
			write(fd, "a", 1);
		} 
		else {
			lseek(fd, 0, SEEK_END);
			write(fd, "b", 1);
		}

	}

	close(fd);
	return 0;

}



