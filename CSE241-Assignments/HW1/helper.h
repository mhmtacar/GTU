#ifndef HELPER_H_
#define HELPER_H_

const int SIZE=12;

void set_board(int [SIZE][SIZE], int);
void print_board(int [SIZE][SIZE], int [SIZE][SIZE], int);
int all_board_fill(int [SIZE][SIZE], int);
int in_board_area(int, int, int);
int control_empty_cell(int [SIZE][SIZE], int, int);
int control_empty_neighbors(int [SIZE][SIZE], int, int, int);
int control_same_cell1(int [SIZE][SIZE], int [SIZE][SIZE], int, int, int);
int control_same_cell2(int [SIZE][SIZE], int [SIZE][SIZE], int, int, int);
int game_end1(int [SIZE][SIZE], int [SIZE][SIZE], int, int, int);
int game_end2(int [SIZE][SIZE], int [SIZE][SIZE], int, int, int);
int computer_move(int [SIZE][SIZE], int [SIZE][SIZE], int, int, int);
int two_user(int [SIZE][SIZE], int [SIZE][SIZE], int);
int user_computer(int [SIZE][SIZE], int [SIZE][SIZE], int);

#endif
