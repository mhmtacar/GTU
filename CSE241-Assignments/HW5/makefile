
output:	main.o abstracthex.o hexvector.o hexarray1d.o hexadapter.o
	g++ -std=c++11 main.o abstracthex.o hexvector.o hexarray1d.o hexadapter.o -o output

main.o: main.cpp
	g++ -std=c++11 -c main.cpp

abstracthex.o: abstracthex.cpp abstracthex.h
	g++ -std=c++11 -c abstracthex.cpp

hexvector.o:   hexvector.cpp hexvector.h
	g++ -std=c++11 -c hexvector.cpp

hexarray1d.o:  hexarray1d.cpp hexarray1d.h
	g++ -std=c++11 -c hexarray1d.cpp

hexadapter.o: hexadapter.cpp hexadapter.h
	g++ -std=c++11 -c hexadapter.cpp

clean:
	rm *.o output



