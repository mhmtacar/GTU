
output: main.o helper.o
	g++ main.o helper.o -o output

main.o: main.cpp
	g++ -std=gnu++11 -c main.cpp

helper.o: helper.cpp helper.h
	g++ -std=gnu++11 -c helper.cpp

clean:
	rm *.o output
