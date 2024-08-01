#include<iostream>
#include "helper.h"
using namespace std;


int main() {

    int board[SIZE][SIZE];
    int destinated_board[SIZE][SIZE];
    int board_size;
    int option;
    int board_full=0;
    int winner=0;


	cout<<"Please enter board size from 6 to 12:";
	cin>>board_size;

	while(board_size<6 || board_size>12) {
          cout<<"Please enter board size from 6 to 12:";
          cin>>board_size;
	}

	cout<<"if this a two player game,enter 0 or user versus computer game,enter 1 :";
        cin>>option;

	while(option<0 || option>1) {
          cerr<<"Wrong choice.Please if this a two player game,enter 0 or user versus computer game,enter 1 :";
          cin>>option;
	}


	set_board(board,board_size);
	set_board(destinated_board,board_size);
	print_board(board,destinated_board,board_size);

    if(option==0) {
	while(board_full!=1 && winner==0) {
		winner=two_user(board,destinated_board,board_size);
		board_full=all_board_fill(board,board_size);
	}
}

   else {
   	while(board_full!=1 && winner==0) {
		winner=user_computer(board,destinated_board,board_size);
		board_full=all_board_fill(board,board_size);
	}
   }

    if(winner==1)
       cout<<"User1 won"<<endl;
       
    else if(winner==2){
    	if(option==0)
    	   cout<<"User2 won"<<endl;
    	else
    	   cout<<"Computer won"<<endl;
	}
	
	else
    cout<<"Game end"<<endl;

    return 0;
}

