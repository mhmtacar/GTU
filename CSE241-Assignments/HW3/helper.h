#ifndef HELPER_H_
#define HELPER_H_

#include<string>
#include<vector>

enum cell_states{empty,user1,user2};


class Hex{
	public:
		 Hex();
		 Hex(int);
		 Hex(int,int);
		 void set_board();
		 void print_board();
		 void set_destinated_board();
		 void set_board_size(int val);
		 int get_board_size() const;
		 int get_width() const;
		 int get_height() const;
		 void set_game_type(int game_type_val);
		 int get_game_type() const;
		 void set_user_select(int user_val);
		 int get_user_select() const;
		 void set_user1_pos(char pos_val);
		 char get_user1_pos() const;
         void set_user1_row(int row_val);
		 int get_user1_row() const;
         int resize_game();
         static int all_games_markedcells(int);
         int one_game_markedcells();
         bool compare_marked_cells(Hex &);

	 
		 
	private:
		
		class Cell{
			
			public:
	
				void set_pos(char pos_val);
				void set_row(int row_val);
				void set_user_type(cell_states user_type_val);
				void set_destinated_cell();	
				void change_destinated_cell();
				char get_pos() const;
				int get_row() const;
				cell_states get_user_type() const;
				int get_destinated_cell() const;
                  
                  
			private:
				char pos;
				int row;
				cell_states user_type;
				int destinated_cell;
		};

		 void playGame();
		 void playGame(int);
		 void playGame(int,int);
		 int in_board_area(int,int);
         int control_empty_cell(int,int);
         int all_board_fill();
		 void play(char,int);
		 int play();
		 int control_same_cell1(int,int);
		 int control_same_cell2(int,int);
		 int game_end1(int,int);
		 int game_end2(int,int);
		 int two_user(int &);
		 int control_empty_neighbors(int,int);
		 int user_computer();
		 void save_file(std::string filename);
		 int load_file(std::string filename);
		 int get_command(int &,int &);
		 void finish_control();
		
		
		std::vector<std::vector<Cell> > hexCells;
		int board_size;
		int game_type;
		int user_select;
		int user1_pos;
		int user1_row;
		static int marked_cells;
};


		#endif
