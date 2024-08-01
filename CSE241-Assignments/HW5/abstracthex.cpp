#include<iostream>
#include<fstream>
#include<random>
#include<string>
#include<vector>
#include<deque>
#include "abstracthex.h"

using namespace std;


namespace Mehmet{

		AbstractHex::AbstractHex(){
			playGame();
		}
		AbstractHex::AbstractHex(int board_size_val){
		    playGame(board_size_val);
		}

		AbstractHex::AbstractHex(int board_size_val,int game_type_val){
			playGame(board_size_val,game_type_val);
		}

		AbstractHex::AbstractHex(const AbstractHex& game): board_size(game.get_board_size()),game_type(game.get_game_type()),user_select(game.get_user_select()),
		                                                             user1_pos(game.get_user1_pos()),user1_row(game.get_user1_row()),last_move_pos(game.get_last_move_pos()),
		                                                             last_move_row(game.get_last_move_row()),winner_user(game.get_winner_user()){
			//Intentionally empty//
		}

		AbstractHex& AbstractHex:: operator =(const AbstractHex& game){
			board_size=game.board_size;
			game_type=game.game_type;
			user_select=game.user_select;
			user1_pos=game.user1_pos;
			user1_row=game.user1_row;
			last_move_pos=game.last_move_pos;
			last_move_row=game.last_move_row;
			winner_user=game.winner_user;
			return *this;
		}

         void AbstractHex::set_board_size(int board_size_val){
		 	board_size=board_size_val;
		 }
		 int AbstractHex::get_board_size() const{
		 return board_size;
		 }
		 int AbstractHex::get_width() const{
		 	return board_size;
		 }
		 int AbstractHex::get_height() const{
		 	return board_size;
		 }
		 void AbstractHex::set_game_type(int game_type_val){
		 	game_type=game_type_val;
		 }
		 int AbstractHex::get_game_type() const{
		     return game_type;
		 }
		 void AbstractHex::set_user_select(int user_val){
		 	user_select=user_val;
		 }

		 int AbstractHex::get_user_select() const{
		 	return user_select;
		 }

		 void AbstractHex::set_user1_pos(char pos_val){
		 	user1_pos=pos_val;
		 }

		 char AbstractHex::get_user1_pos() const{
		 	return user1_pos;
		 }

         void AbstractHex::set_user1_row(int row_val){
		 	user1_row=row_val;
		 }

		 int AbstractHex::get_user1_row() const{
		 	return user1_row;
		 }

		 void AbstractHex::set_last_move_row(int last_move_row_val){
		 	last_move_row=last_move_row_val;
		 }

         int AbstractHex::get_last_move_row() const{
		 	return last_move_row;
		 }

         void AbstractHex::set_last_move_pos(int last_move_pos_val){
		 	last_move_pos=last_move_pos_val;
		 }

         int AbstractHex::get_last_move_pos() const{
		 	return last_move_pos;
		 }

		 void AbstractHex::set_winner_user(int winner_user_val){
		 	winner_user=winner_user_val;
		 }

		 int AbstractHex::get_winner_user() const{
		 	return winner_user;
		 }

		 void AbstractHex::Cell::set_pos(char pos_val){
			pos=pos_val;
		}
		 void AbstractHex::Cell::set_row(int row_val){
            row=row_val;
		}
		 void AbstractHex::Cell::set_user_type(cell_states user_type_val){
			user_type=user_type_val;
		}
		 void AbstractHex::Cell::set_destinated_cell(){
			destinated_cell=0;
		}
		 void AbstractHex::Cell::set2_destinated_cell(int destinated_cell_val){
			destinated_cell=destinated_cell_val;
		}
		 void AbstractHex::Cell::change_destinated_cell(){
			destinated_cell=1;
		}
		 char AbstractHex::Cell::get_pos() const{
			return pos;
		}
		 int AbstractHex::Cell::get_row() const{
			return row;
		}
		 cell_states AbstractHex::Cell::get_user_type() const{
			return user_type;
		}
		 int AbstractHex::Cell::get_destinated_cell() const{
             return destinated_cell;
		}
		 void AbstractHex::Cell::set_invalid_char(char invalid_char_val){
             invalid_char=invalid_char_val;
		 }
		 char AbstractHex::Cell::get_invalid_char() const{
		 	 return invalid_char;
		 }

         
		
		
        /* This function takes the size of the board and game type from the user. */

		void AbstractHex::playGame(){
			
			int board_size_val,game_type_val;
			int input_truth=0;

			while(input_truth==0){
		  
		        cout<<"Please enter board size:";
		        cin>>board_size_val;

		        if(cin.fail()){
		          cerr<<"Wrong choice"<<endl;
		          cin.clear();
		          cin.ignore(1000,'\n');
		        }

		        else{
		           if(board_size_val>=6 && board_size_val<=26)
		              input_truth=1;
		        }
		        
		  	}

		  	set_board_size(board_size_val);
		  	
		  	input_truth=0;


			 while(input_truth==0) {

		         cout<<"Please if this a two player game,enter 0 or user versus computer game,enter 1 :";
		         cin>>game_type_val;
		          
		         if(cin.fail()){
		          cerr<<"Wrong choice"<<endl;
		          cin.clear();
		          cin.ignore(1000,'\n');
		         }

		        else{
		           if(game_type_val==0 || game_type_val==1)
		              input_truth=1;
		        }
			}

			set_game_type(game_type_val);

			
		}


		void AbstractHex::playGame(int board_size_val){

			int game_type_val;
			int input_truth=0;
			
			while(board_size_val<6 || board_size_val>26){
				
		        while(input_truth==0){
		  
		        cout<<"Please enter board size:";
		        cin>>board_size_val;

		        if(cin.fail()){
		          cerr<<"Wrong choice"<<endl;
		          cin.clear();
		          cin.ignore(1000,'\n');
		        }

		        else{
		           if(board_size_val>=6 && board_size_val<=26)
		              input_truth=1;
		        }
		        
		  	}
		        
			}
			
			set_board_size(board_size_val);
			input_truth=0;
			
			while(input_truth==0) {

		         cout<<"Please if this a two player game,enter 0 or user versus computer game,enter 1 :";
		         cin>>game_type_val;
		          
		         if(cin.fail()){
		          cerr<<"Wrong choice"<<endl;
		          cin.clear();
		          cin.ignore(1000,'\n');
		         }

		        else{
		           if(game_type_val==0 || game_type_val==1)
		              input_truth=1;
		        }
			}

			set_game_type(game_type_val);

			      
		}


		void AbstractHex::playGame(int board_size_val,int game_type_val){
			
			int input_truth=0;
			
			while(board_size_val<6 || board_size_val>26){
				
		        while(input_truth==0){
		  
		        cout<<"Please enter board size:";
		        cin>>board_size_val;

		        if(cin.fail()){
		          cerr<<"Wrong choice"<<endl;
		          cin.clear();
		          cin.ignore(1000,'\n');
		        }

		        else{
		           if(board_size_val>=6 && board_size_val<=26)
		              input_truth=1;
		        }
		        
		  	}
		        
			}
			
			set_board_size(board_size_val);
			input_truth=0;
			
			while(game_type_val!=0 && game_type_val!=1){
			
			while(input_truth==0) {

		         cout<<"Please if this a two player game,enter 0 or user versus computer game,enter 1 :";
		         cin>>game_type_val;
		          
		         if(cin.fail()){
		          cerr<<"Wrong choice"<<endl;
		          cin.clear();
		          cin.ignore(1000,'\n');
		         }

		        else{
		           if(game_type_val==0 || game_type_val==1)
		              input_truth=1;
		        }
			}
			
		}

		    set_game_type(game_type_val);

			      
		}


		bool AbstractHex::operator ==(const AbstractHex& game) const{
			if(get_board_size()==game.get_board_size() && get_game_type()==game.get_game_type())
				return true;
			else
				return false;
		}


		AbstractHex::~AbstractHex(){
        	
        }

       
        bool valid_check(AbstractHex *game){
        	
        	if(game->valid_moves()==true)
               return true;
        	else
        	return false;
        }


}




        

        


	    
