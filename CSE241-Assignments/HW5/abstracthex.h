#ifndef ABSTRACTHEX_H_
#define ABSTRACTHEX_H_

#include<iostream>
#include<fstream>
#include<random>
#include<string>
#include<vector>
#include<deque>

enum cell_states{empty,user1,user2,invalid};

class Exc1{};
class Exc2{};

namespace Mehmet{

class AbstractHex{

    public:

	class Cell{
			
			public:
	
				void set_pos(char);
				void set_row(int);
				void set_user_type(cell_states);
				void set_destinated_cell();	
				void set2_destinated_cell(int);
				void change_destinated_cell();
				char get_pos() const;
				int get_row() const;
				cell_states get_user_type() const;
				int get_destinated_cell() const;
				void set_invalid_char(char);
				char get_invalid_char() const;
                  
			private:
				char pos;
				int row;
				cell_states user_type;
				int destinated_cell;
				char invalid_char;

		};

		 explicit AbstractHex();
		 explicit AbstractHex(int);
		 explicit AbstractHex(int,int);
		 AbstractHex(const AbstractHex&);
		 AbstractHex& operator =(const AbstractHex&);
		 void set_board_size(int);
		 int get_board_size() const;
		 int get_width() const;
		 int get_height() const;
		 void set_game_type(int);
		 int get_game_type() const;
		 void set_user_select(int);
		 int get_user_select() const;
		 void set_user1_pos(char);
		 char get_user1_pos() const;
         void set_user1_row(int);
		 int get_user1_row() const;
		 void set_last_move_pos(int);
		 int get_last_move_pos() const;
		 void set_last_move_row(int);
		 int get_last_move_row() const;
		 void set_winner_user(int);
		 int get_winner_user() const;


         void playGame();
		 void playGame(int);
		 void playGame(int,int);
		 virtual void set_board()=0;
		 virtual void set_destinated_board()=0;
		 virtual int in_board_area(int,int) const=0;
		 virtual int control_empty_cell(int,int) const=0;
		 virtual int all_board_fill() const=0;
		 virtual int control_same_cell1(int,int) const=0;
		 virtual int control_same_cell2(int,int) const=0;
		 virtual int two_user()=0;
		 virtual int control_empty_neighbors(int,int) const=0;
		 virtual int user_computer()=0;
		 virtual int get_command(int&,int&)=0;
		 virtual void finish_control()=0;

         virtual void print() const=0;
         virtual int readFromFile(std::string)=0;
         virtual void writeToFile(std::string) const=0;
         virtual void reset()=0;
         virtual void setSize()=0;
         virtual void play(char,int)=0;
         virtual int play()=0;
         virtual bool isEnd(int,int,int)=0;
         virtual Cell operator () (int,int) throw(Exc1)=0;
         bool operator ==(const AbstractHex&) const;
         virtual Cell last_move() throw(Exc2)=0;
         virtual int numberOfMoves()=0;
         virtual bool valid_moves() const=0;
         virtual ~AbstractHex();
             
	private:
			
		int board_size;
		int game_type;
		int user_select;
		int user1_pos;
		int user1_row;
		int last_move_pos=-1;
		int last_move_row=-1;
		int winner_user=0;

};

        bool valid_check(AbstractHex *);

}

		#endif
