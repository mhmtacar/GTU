#ifndef HELPER_H_
#define HELPER_H_

#include<string>

const int SIZE=12;

enum cell_states{empty,user1,user2};

void set_board(cell_states [SIZE][SIZE], const int &);
void set_destinated_board(int [SIZE][SIZE], const int &);
void print_board(cell_states [SIZE][SIZE], int [SIZE][SIZE], const int &);
int all_board_fill(cell_states [SIZE][SIZE], const int &);
int in_board_area(int, int, int);
int control_empty_cell(cell_states [SIZE][SIZE], const int &, const int &);
int control_empty_neighbors(cell_states [SIZE][SIZE], int, int, int);
int control_same_cell1(cell_states [SIZE][SIZE], int [SIZE][SIZE], int, int, int);
int control_same_cell2(cell_states [SIZE][SIZE], int [SIZE][SIZE], int, int, int);
int game_end1(cell_states [SIZE][SIZE], int [SIZE][SIZE], int, int, int);
int game_end2(cell_states [SIZE][SIZE], int [SIZE][SIZE], int, int, int);
int load_file(std::string ,cell_states [SIZE][SIZE], int &, int &);
void save_file(std::string ,cell_states [SIZE][SIZE], int, int);
int computer_move(cell_states [SIZE][SIZE], int [SIZE][SIZE], int, int, int);
int get_command(cell_states [SIZE][SIZE], int [SIZE][SIZE], int & , char &, int &, int &);
int two_user(cell_states [SIZE][SIZE], int [SIZE][SIZE], int &, int &, int &);
int user_computer(cell_states [SIZE][SIZE], int [SIZE][SIZE], int &, int &);

#endif
