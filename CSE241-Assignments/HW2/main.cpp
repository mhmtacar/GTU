#include<iostream>
#include "helper.h"

using namespace std;


int main() {

    cell_states board[SIZE][SIZE];
    int destinated_board[SIZE][SIZE];
    int board_size;
    int game_type;
    int input_truth=0;
    int user_control=1;
    int board_full=0;
    int winner=0;
  

  while(input_truth==0){
  
        cout<<"Please enter board size from 6 to 12:";
        cin>>board_size;

        if(cin.fail()){
          cerr<<"Wrong choice"<<endl;
          cin.clear();
          cin.ignore(1000,'\n');
        }

        else{
           if(board_size>=6 && board_size<=12)
              input_truth=1;
        }
        
  	}
      

   input_truth=0;


	 while(input_truth==0) {

         cout<<"Please if this a two player game,enter 0 or user versus computer game,enter 1 :";
         cin>>game_type;
          
         if(cin.fail()){
          cerr<<"Wrong choice"<<endl;
          cin.clear();
          cin.ignore(1000,'\n');
         }

        else{
           if(game_type==0 || game_type==1)
              input_truth=1;
        }
	}


	set_board(board,board_size);
	set_destinated_board(destinated_board,board_size);
	print_board(board,destinated_board,board_size);

	while(board_full!=1 && winner==0) {
		if(game_type==0) {
		winner=two_user(board,destinated_board,board_size,game_type,user_control);
		board_full=all_board_fill(board,board_size);
	}

        else {
		winner=user_computer(board,destinated_board,board_size,game_type);
		board_full=all_board_fill(board,board_size);
	}
   }

    if(winner==1)
       cout<<"User1 won"<<endl;
       
    else if(winner==2){
    	if(game_type==0)
    	   cout<<"User2 won"<<endl;
    	else
    	   cout<<"Computer won"<<endl;
	}
	
	else
    cout<<"Game end"<<endl;

    return 0;
}


