#ifndef HEXVECTOR_H_
#define HEXVECTOR_H_


#include<iostream>
#include<fstream>
#include<random>
#include<string>
#include<vector>

#include "abstracthex.h"


namespace Mehmet {

	class HexVector: public AbstractHex{

	public:

	HexVector();
	HexVector(int);
	HexVector(int,int);
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
    bool operator ==(HexVector&);
    virtual Cell last_move() throw(Exc2);
    virtual int numberOfMoves();
    virtual bool valid_moves() const;
  
	private:

	 std::vector<std::vector<Cell> > hexCells;

	};

}


    #endif
