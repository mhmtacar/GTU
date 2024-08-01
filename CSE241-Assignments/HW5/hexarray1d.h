#ifndef HEXARRAY1D_H_
#define HEXARRAY1D_H_


#include<iostream>
#include<fstream>
#include<random>
#include<string>
#include<vector>

#include "abstracthex.h"

namespace Mehmet {
	
	class HexArray1D: public AbstractHex{

	public:

	HexArray1D();
	HexArray1D(int);
	HexArray1D(int,int);
	HexArray1D(const HexArray1D&);
	HexArray1D& operator =(const HexArray1D&);
	int one_dim(int,int) const;
    virtual void set_board();
	virtual void set_destinated_board();
	virtual int in_board_area(int,int) const;
	virtual int control_empty_cell(int,int) const;
	virtual int all_board_fill() const;
    virtual int control_same_cell1(int,int) const;
	virtual int control_same_cell2(int,int) const;
	virtual int two_user();
	virtual int control_empty_neighbors(int,int) const;
	virtual int user_computer();
	virtual int get_command(int&,int&);
	virtual void finish_control();

    virtual void print() const;
    virtual int readFromFile(std::string);
    virtual void writeToFile(std::string) const;
    virtual void reset();
    virtual void setSize();
    virtual void play(char,int);
    virtual int play();
    virtual bool isEnd(int,int,int);
    virtual Cell operator () (int,int) throw(Exc1);
    bool operator ==(HexArray1D&);
    virtual Cell last_move() throw(Exc2);
    virtual int numberOfMoves();
    virtual bool valid_moves() const;
    virtual ~HexArray1D();

	private:

	  Cell *hexCells;

	};
	
}


    #endif
