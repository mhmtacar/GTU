#ifndef HELPER_H_
#define HELPER_H_

#include<iostream>
#include<fstream>
#include<string>


enum cell_states{empty,user1,user2};


class Hex{

    public:

	class Cell{
			
			public:
	
				void set_pos(char);
				void set_row(int);
				void set_user_type(cell_states);
				void set_destinated_cell();	
				void set_cell_order(int);
				void change_destinated_cell();
				char get_pos() const;
				int get_row() const;
				cell_states get_user_type() const;
				int get_destinated_cell() const;
                int get_cell_order() const;
                  
			private:
				char pos;
				int row;
				cell_states user_type;
				int destinated_cell;
				int cell_order;
		};

		 explicit Hex();
		 explicit Hex(int);
		 explicit Hex(int,int);
		 Hex(const Hex&);
		 Hex& operator =(const Hex&);
		 void set_board();
		 void set_destinated_board();
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
		 void set_undo_num(int);
		 int get_undo_num() const;
         static int all_games_markedcells(int);
         int one_game_markedcells() const;
         bool operator ==(const Hex&) const;
         
		 friend std::ostream& operator <<(std::ostream&, const Hex&);
         friend std::ofstream& operator <<(std::ofstream&, Hex&);
         friend std::ifstream& operator >>(std::ifstream&, Hex&);
         void operator --();
         void operator --(int);

         ~Hex();
	     
		 
	private:
		
		
		 void playGame();
		 void playGame(int);
		 void playGame(int,int);
		 int in_board_area(int,int) const;
         int control_empty_cell(int,int) const;
         int all_board_fill()const;
		 void play(char,int);
		 Cell play();
		 int control_same_cell1(int,int) const;
		 int control_same_cell2(int,int) const;
		 int game_end1(int,int);
		 int game_end2(int,int);
		 int two_user();
		 int control_empty_neighbors(int,int) const;
		 int user_computer();
		 int get_command(int&,int&);
		 void finish_control();
		
		
		Cell **hexCells;
		int board_size;
		int game_type;
		int user_select;
		int user1_pos;
		int user1_row;
		int undo_num;
		static int marked_cells;
		static int user1_score;
		static int user2_score;
		static int temp1_score;
		static int temp2_score;
};


		#endif
