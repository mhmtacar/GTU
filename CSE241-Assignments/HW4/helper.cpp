#include<iostream>
#include<fstream>
#include<random>
#include<string>
#include "helper.h"

using namespace std;

int Hex::marked_cells=0;
int Hex::user1_score=0;
int Hex::user2_score=0;
int Hex::temp1_score=0;
int Hex::temp2_score=0;


		Hex::Hex(){
			playGame();
		}
		Hex::Hex(int board_size_val){
		    playGame(board_size_val);
		}

		Hex::Hex(int board_size_val,int game_type_val){
			playGame(board_size_val,game_type_val);
		}

		Hex::Hex(const Hex& game){
			//Intentionally empty//
		}

		Hex& Hex:: operator =(const Hex& game){
			//Intentionally empty//
		}

         inline void Hex::set_board_size(int board_size_val){
		 	board_size=board_size_val;
		 }
		 inline int Hex::get_board_size() const{
		 return board_size;
		 }
		 inline int Hex::get_width() const{
		 	return board_size;
		 }
		 inline int Hex::get_height() const{
		 	return board_size;
		 }
		 inline void Hex::set_game_type(int game_type_val){
		 	game_type=game_type_val;
		 }
		 inline int Hex::get_game_type() const{
		     return game_type;
		 }
		 inline void Hex::set_user_select(int user_val){
		 	user_select=user_val;
		 }

		 inline int Hex::get_user_select() const{
		 	return user_select;
		 }

		 inline void Hex::set_user1_pos(char pos_val){
		 	user1_pos=pos_val;
		 }

		 inline char Hex::get_user1_pos() const{
		 	return user1_pos;
		 }

         inline void Hex::set_user1_row(int row_val){
		 	user1_row=row_val;
		 }

		 inline int Hex::get_user1_row() const{
		 	return user1_row;
		 }

		 inline void Hex::set_undo_num(int undo_val){
		 	undo_num=undo_val;
		 }

		 inline int Hex::get_undo_num() const{
		 	return undo_num;
		 }

		 inline void Hex::Cell::set_pos(char pos_val){
			pos=pos_val;
		}
		 inline	void Hex::Cell::set_row(int row_val){
            row=row_val;
		}
		inline void Hex::Cell::set_user_type(cell_states user_type_val){
			user_type=user_type_val;
		}
		inline void Hex::Cell::set_destinated_cell(){
			destinated_cell=0;
		}
		inline void Hex::Cell::set_cell_order(int cell_order_val){
			cell_order=cell_order_val;
		}
		inline void Hex::Cell::change_destinated_cell(){
			destinated_cell=1;
		}
		inline char Hex::Cell::get_pos() const{
			return pos;
		}
		inline int Hex::Cell::get_row() const{
			return row;
		}
		inline cell_states Hex::Cell::get_user_type() const{
			return user_type;
		}
		inline int Hex::Cell::get_destinated_cell() const{
             return destinated_cell;
		}
        inline int Hex::Cell::get_cell_order() const{
        	return cell_order;
        }
		
		


		/* This function sets the position,row and user type of cells in the two dimensional dynamic Cell array. */

		void Hex::set_board(){

			int i,j;

		    hexCells=new Cell*[board_size];

		    if(hexCells!=nullptr){

		    for(i=0;i<board_size;i++){
		    	hexCells[i]=new Cell[board_size];
		    }
			

			for(i=0;i<board_size;i++){
				for(j=0;j<board_size;j++){
					hexCells[i][j].set_pos(65+j);
		            hexCells[i][j].set_row(i+1);
		            hexCells[i][j].set_user_type(empty);
		            hexCells[i][j].set_destinated_cell();
		            hexCells[i][j].set_cell_order(0);
			   }
		   }

	     }
		     
	}


        /* This function resets the cells passed over while finding the end of the game. */

		void Hex::set_destinated_board(){

			int i,j;

			for(i=0;i<board_size;i++){
				for(j=0;j<board_size;j++){
					hexCells[i][j].set_destinated_cell();
				}
			}
		}

		
        /* This function takes the size of the board and game type from the user. */

		void Hex::playGame(){
			
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
            set_undo_num(0);

			set_board();
		    cout<<*this;
		    finish_control();
			
			 
		}


		void Hex::playGame(int board_size_val){

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

			set_board();
			cout<<*this;
			finish_control();
			      
		}


		void Hex::playGame(int board_size_val,int game_type_val){
			
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

		    set_board();
			cout<<*this;
			finish_control();
			      
		}


        /* This function checks whether the specified location is in the board area using pos and row parameters. */

		int Hex::in_board_area(int pos=0,int row=0) const{

			  if(pos<0 || pos>=board_size || row<0 || row>=board_size)
			    return 0;
			  else
			    return 1;
		}


        /* This function checks whether there is a user in the specified cell using pos_val and row_val parameters. */

		int Hex::control_empty_cell(int pos_val,int row_val) const{

		    if(hexCells[row_val][pos_val].get_user_type()==empty)
		    	return 1;
		    else
		    	return 0;

		}


        /* This function checks whether there are users in each cell of the two-dimensional dynamic Cell array. */

		int Hex::all_board_fill() const{

			auto counter=0;
			int i,j;

			for(i=0;i<board_size;i++){
				for(j=0;j<board_size;j++){
		            if(hexCells[i][j].get_user_type()==empty)
		            	counter++;
				}
			}

			if(counter==0)
				return 1;
			else
				return 0;
		}


        /* This function takes a command from the user to move or save-load the board. If the user wants to save the board, it calls overloaded stream insertion operator 
           and returns 0. If the user wants to load the board, it calls overloaded stream extraction operator and returns 2. If the user wants to move on the board, returns 3. 
           If the user wants to apply pre undo to hex board, it calls overloaded --(pre) operator and overloaded stream insertion operator and returns 6. If the user wants 
           to apply post undo to hex board, it calls overloaded --(post) operator and overloaded stream insertion operator and returns 8. */

		int Hex::get_command(int& pos,int& row){
			  
			  string str1,str2;
			  string filename;
			  int load_control;
			  char ch;
			  int num;

			  cin>>str1>>str2;

			  if(str1=="SAVE"){
			      filename=str2;
			      ofstream outputStream;
		    	  outputStream.open(filename);
			      outputStream<<*this;
			      return 0;
			     }

			     else if(str1=="LOAD"){
			      filename=str2;
			      ifstream inputStream;
			      inputStream.open(filename);

			      if(!inputStream.is_open()){
			      	 cerr<<"File can not open"<<endl;
			      	 return 1;
			      }

		          else{
			         inputStream>>*this;
			         cout<<*this;
			         return 2;
			      }

			     }

			     else if(str1=="--" && str2=="UNDO"){
			     	if(get_undo_num()==0){
			     		cout<<"Board is empty"<<endl;
			     	    return 5;
			     	}
			     	else{
			     	--(*this);
			     	cout<<*this;
			     	return 6;
			       }
			     }

			      else if(str1=="UNDO" && str2=="--"){
			     	if(get_undo_num()==0){
			     		cout<<"Board is empty"<<endl;
			     	    return 7;
			     	}
			     	else{
			     	(*this)--;
			     	cout<<*this;
			     	return 8;
			       }
			     }

			     else{

		          if(board_size<10){

				      if(str1.length()==1 && str2.length()==1){
				        pos=str1[0];
				        row=str2[0]-48;
				        return 3;
				      }
				      else{
				      	pos=str1[0];
				        row=str2[0]-48;
				      	return 4;
				      }
			      }

			      else {

			    	if(str1.length()==1 && str2.length()<=2){
			           pos=str1[0];
			           row=stoi(str2);
			           return 3;
			        }
			        else{
			            pos=str1[0];
				        row=str2[0]-48;
			      	   return 4;
			        }
			      }
			   }
			}

	
        /* This function checks if neighbor cells of the cell at the specified location on the board are visited and whether the user in the cell at the specified location 
           and the users in the neighboring cells are the same user. */

	    int Hex::control_same_cell1(int row,int pos) const{

		  decltype(0)counter=0;

		  if(in_board_area(pos,row+1)==1){
		    if(hexCells[row+1][pos].get_destinated_cell()!=1 && hexCells[row][pos].get_user_type()==user1 && hexCells[row+1][pos].get_user_type()==user1)
		      counter++;
		  }

		  if(in_board_area(pos-1,row)==1){
		    if(hexCells[row][pos-1].get_destinated_cell()!=1 && hexCells[row][pos].get_user_type()==user1 && hexCells[row][pos-1].get_user_type()==user1)
		      counter++;
		  }


		  if(in_board_area(pos-1,row+1)==1){
		    if(hexCells[row+1][pos-1].get_destinated_cell()!=1 && hexCells[row][pos].get_user_type()==user1 && hexCells[row+1][pos-1].get_user_type()==user1)
		      counter++;
		  }

		  if(in_board_area(pos+1,row)==1){
		    if(hexCells[row][pos+1].get_destinated_cell()!=1 && hexCells[row][pos].get_user_type()==user1 && hexCells[row][pos+1].get_user_type()==user1)
		      counter++;
		  }

		  if(in_board_area(pos+1,row-1)==1){
		    if(hexCells[row-1][pos+1].get_destinated_cell()!=1 && hexCells[row][pos].get_user_type()==user1 && hexCells[row-1][pos+1].get_user_type()==user1)
		      counter++;
		  }


		  if(in_board_area(pos,row-1)==1){
		    if(hexCells[row-1][pos].get_destinated_cell()!=1 && hexCells[row][pos].get_user_type()==user1 && hexCells[row-1][pos].get_user_type()==user1)
		      counter++;
		  }


		    if(counter==0)
		      return 1;
		    else
		      return 0;

		}


        /* This function checks if neighbor cells of the cell at the specified location on the board are visited and whether the user in the cell at the specified location 
           and the users in the neighboring cells are the same user. */

		int Hex::control_same_cell2(int row,int pos) const{

		  decltype(0)counter=0;

		  if(in_board_area(pos,row+1)==1){
		    if(hexCells[row+1][pos].get_destinated_cell()!=1 && hexCells[row][pos].get_user_type()==user2 && hexCells[row+1][pos].get_user_type()==user2)
		      counter++;
		  }

		  if(in_board_area(pos-1,row)==1){
		    if(hexCells[row][pos-1].get_destinated_cell()!=1 && hexCells[row][pos].get_user_type()==user2 && hexCells[row][pos-1].get_user_type()==user2)
		      counter++;
		  }


		  if(in_board_area(pos-1,row+1)==1){
		    if(hexCells[row+1][pos-1].get_destinated_cell()!=1 && hexCells[row][pos].get_user_type()==user2 && hexCells[row+1][pos-1].get_user_type()==user2)
		      counter++;
		  }

		  if(in_board_area(pos+1,row)==1){
		    if(hexCells[row][pos+1].get_destinated_cell()!=1 && hexCells[row][pos].get_user_type()==user2 && hexCells[row][pos+1].get_user_type()==user2)
		      counter++;
		  }

		  if(in_board_area(pos+1,row-1)==1){
		    if(hexCells[row-1][pos+1].get_destinated_cell()!=1 && hexCells[row][pos].get_user_type()==user2 && hexCells[row-1][pos+1].get_user_type()==user2)
		      counter++;
		  }


		  if(in_board_area(pos,row-1)==1){
		    if(hexCells[row-1][pos].get_destinated_cell()!=1 && hexCells[row][pos].get_user_type()==user2 && hexCells[row-1][pos].get_user_type()==user2)
		      counter++;
		  }


		    if(counter==0)
		      return 1;
		    else
		      return 0;

		}


        /* This function checks if user1 has won the game. This function proceeds from one of the cells in the first column and checks if it has the same user as neighboring cells 
           and whether neighboring cells have been visited before. If it has the same user as one of the neighbor cells and the neighbor cell has not been visited before, it calls 
           the game_end1 function again. If the function can get to the far right of the board, the function returns 1, otherwise it returns 0. Also, user1_score which is a static
           variable is incrementing in every condition in order to print user1 score to the screen. */

		int Hex::game_end1(int row,int pos) {
	        

		    if(pos==board_size-1){
		       user1_score++;
		       hexCells[row][pos].change_destinated_cell();
		       return 1;
		   }

		    else if(control_same_cell1(row,pos)==1){
		    	user1_score++;
		        return 0;
		    }

		    else{

		    if(in_board_area(pos,row+1)==1){
		         if(hexCells[row][pos].get_user_type()==user1 && hexCells[row+1][pos].get_user_type()==user1 && hexCells[row+1][pos].get_destinated_cell()==0){
		         	user1_score++;
		            hexCells[row][pos].change_destinated_cell();
		            return 0+game_end1(row+1,pos);
		        }
		    }

		    if(in_board_area(pos-1,row)==1){
		         if(hexCells[row][pos].get_user_type()==user1 && hexCells[row][pos-1].get_user_type()==user1 && hexCells[row][pos-1].get_destinated_cell()==0){
		         	user1_score++;
		            hexCells[row][pos].change_destinated_cell();
		            return 0+game_end1(row,pos-1);
		        }
		    }


		    if(in_board_area(pos-1,row+1)==1){
		         if(hexCells[row][pos].get_user_type()==user1 && hexCells[row+1][pos-1].get_user_type()==user1 && hexCells[row+1][pos-1].get_destinated_cell()==0){
		         	user1_score++;
		            hexCells[row][pos].change_destinated_cell();
		            return 0+game_end1(row+1,pos-1);
		        }
		    }

		    if(in_board_area(pos+1,row)==1){
		         if(hexCells[row][pos].get_user_type()==user1 && hexCells[row][pos+1].get_user_type()==user1 && hexCells[row][pos+1].get_destinated_cell()==0){
		         	user1_score++;
		            hexCells[row][pos].change_destinated_cell();
		            return 0+game_end1(row,pos+1);
		        }
		    }

		    if(in_board_area(pos+1,row-1)==1){
		         if(hexCells[row][pos].get_user_type()==user1 && hexCells[row-1][pos+1].get_user_type()==user1 && hexCells[row-1][pos+1].get_destinated_cell()==0){
		         	user1_score++;
		            hexCells[row][pos].change_destinated_cell();
		            return 0+game_end1(row-1,pos+1);
		        }
		    }


		    if(in_board_area(pos,row-1)==1){
		         if(hexCells[row][pos].get_user_type()==user1 && hexCells[row-1][pos].get_user_type()==user1 && hexCells[row-1][pos].get_destinated_cell()==0){
		         	user1_score++;
		            hexCells[row][pos].change_destinated_cell();
		            return 0+game_end1(row-1,pos);
		        }
		      }
		    }

		      return 0;

		    }


       /* This function checks if user2 has won the game. This function proceeds from one of the cells in the first row and checks if it has the same user as neighboring cells 
          and whether neighboring cells have been visited before. If it has the same user as one of the neighbor cells and the neighbor cell has not been visited before, it calls 
          the game_end2 function again. If the function can get to the far down of the board, the function returns 1, otherwise it returns 0. Also, user2_score which is a static
          variable is incrementing in every condition in order to print user2 score or computer score to the screen. */

	   int Hex::game_end2(int row,int pos) {
       

	    if(row==board_size-1){
	       user2_score++;
	       hexCells[row][pos].change_destinated_cell();
	       return 1;
	   }

	    else if(control_same_cell2(row,pos)==1){
	    	user2_score++;
	        return 0;
	    }

	    else{

	    if(in_board_area(pos,row+1)==1){
	         if(hexCells[row][pos].get_user_type()==user2 && hexCells[row+1][pos].get_user_type()==user2 && hexCells[row+1][pos].get_destinated_cell()==0){
	         	user2_score++;
	            hexCells[row][pos].change_destinated_cell();
	            return 0+game_end2(row+1,pos);
	        }
	    }

	    if(in_board_area(pos-1,row)==1){
	         if(hexCells[row][pos].get_user_type()==user2 && hexCells[row][pos-1].get_user_type()==user2 && hexCells[row][pos-1].get_destinated_cell()==0){
	         	user2_score++;
	            hexCells[row][pos].change_destinated_cell();
	            return 0+game_end2(row,pos-1);
	        }
	    }


	    if(in_board_area(pos-1,row+1)==1){
	         if(hexCells[row][pos].get_user_type()==user2 && hexCells[row+1][pos-1].get_user_type()==user2 && hexCells[row+1][pos-1].get_destinated_cell()==0){
	         	user2_score++;
	            hexCells[row][pos].change_destinated_cell();
	            return 0+game_end2(row+1,pos-1);
	        }
	    }

	    if(in_board_area(pos+1,row)==1){
	         if(hexCells[row][pos].get_user_type()==user2 && hexCells[row][pos+1].get_user_type()==user2 && hexCells[row][pos+1].get_destinated_cell()==0){
	         	user2_score++;
	            hexCells[row][pos].change_destinated_cell();
	            return 0+game_end2(row,pos+1);
	        }
	    }

	    if(in_board_area(pos+1,row-1)==1){
	         if(hexCells[row][pos].get_user_type()==user2 && hexCells[row-1][pos+1].get_user_type()==user2 && hexCells[row-1][pos+1].get_destinated_cell()==0){
	         	user2_score++;
	            hexCells[row][pos].change_destinated_cell();
	            return 0+game_end2(row-1,pos+1);
	        }
	    }


	    if(in_board_area(pos,row-1)==1){
	         if(hexCells[row][pos].get_user_type()==user2 && hexCells[row-1][pos].get_user_type()==user2 && hexCells[row-1][pos].get_destinated_cell()==0){
	         	user2_score++;
	            hexCells[row][pos].change_destinated_cell();
	            return 0+game_end2(row-1,pos);
	        }
	      }
	    }

	      return 0;

	    }


        /* This function fills the cell that user 1 or user 2 has selected on the board using the pos_val and row_val parameters and 
           puts the order of selection in the appropriate cell on the board according to the given pos_val and row_val parameters. */

		void Hex::play(char pos_val,int row_val){
			
		    if(get_user_select()==1){
		       undo_num+=1;
		       hexCells[row_val-1][pos_val-65].set_user_type(user1);
		       hexCells[row_val-1][pos_val-65].set_cell_order(undo_num);
		    }        
		    else{
		       undo_num+=1;
		       hexCells[row_val-1][pos_val-65].set_user_type(user2);
		       hexCells[row_val-1][pos_val-65].set_cell_order(undo_num);
		    }
		    
		}


        /* This function makes the computer move according to the cell user 1 has selected and puts the order of selection in the selected 
           cell from computer and returns selected computer cell. */

		Hex::Cell Hex::play(){
		    
		         int rand_direction,num,counter=0,empty_cell_found=0,i=0,j=0,k,user2_won;
			     char comp_letter;

			     char pos=get_user1_pos();
			     int row=get_user1_row();

			     random_device device;
			     mt19937 generator(device());
			     uniform_int_distribution<int> distribution(0,5);

			     if(control_empty_neighbors(pos-65,row-1)==1) {

			      while(counter==0) {

			        rand_direction= distribution(generator);

			     if(rand_direction==0 && in_board_area(pos-65,row)==1 && control_empty_cell(pos-65,row)==1){
			            hexCells[row][pos-65].set_user_type(user2);
			            comp_letter=pos;
			            num=row+1;
			            counter++;
			        }
			        else if(rand_direction==1 && in_board_area(pos-66,row-1)==1 && control_empty_cell(pos-66,row-1)==1){
			            hexCells[row-1][pos-66].set_user_type(user2);
			            comp_letter=pos-1;
			            num=row;
			            counter++;
			        }
			        
			        else if(rand_direction==2 && in_board_area(pos-66,row)==1 && control_empty_cell(pos-66,row)==1){
			            hexCells[row][pos-66].set_user_type(user2);
			            comp_letter=pos-1;
			            num=row+1;
			            counter++;
			        }
			        else if(rand_direction==3 && in_board_area(pos-64,row-1)==1 && control_empty_cell(pos-64,row-1)==1){
			            hexCells[row-1][pos-64].set_user_type(user2);
			            comp_letter=pos+1;
			            num=row;
			            counter++;
			        }
			        else if(rand_direction==4 && in_board_area(pos-64,row-2)==1 && control_empty_cell(pos-64,row-2)==1){
			            hexCells[row-2][pos-64].set_user_type(user2);
			            comp_letter=pos+1;
			            num=row-1;
			            counter++;
			        }
			       
			        else if(rand_direction==5 && in_board_area(pos-65,row-2)==1 && control_empty_cell(pos-65,row-2)==1){
			            hexCells[row-2][pos-65].set_user_type(user2);
			            comp_letter=pos;
			            num=row-1;
			            counter++;
			        }
			      }
			    }

			    else {
			       while(empty_cell_found==0) {
			        if(j==board_size-1) {
			            if(hexCells[i][j].get_user_type()==empty){
			                hexCells[i][j].set_user_type(user2);
			                comp_letter=j+65;
			                num=i+1;
			                empty_cell_found=1;
			            }
			            else {
			                i++;
			                j=0;
			            }
			        }
			        else {
			            if(hexCells[i][j].get_user_type()==empty) {
			                hexCells[i][j].set_user_type(user2);
			                comp_letter=j+65;
			                num=i+1;
			                empty_cell_found=1;
			            }
			            else
			                j++;
			        }
			       }
			    }

                           undo_num+=1;
			               hexCells[num-1][comp_letter-65].set_cell_order(undo_num);
			               return hexCells[num-1][comp_letter-65];
			               
		     
		 }


        /* This function fills the board and sets user transitions according to the actions of user 1 and user 2. */

		int Hex::two_user(){
			 
			 int command;
			 int control;
			 int pos,row;
			 int i,j;


		     if(get_user_select()==1){
			 cout<<"Please enter new User1 move such as A 1, B 7, C 10 (If you want to undo the last play,please enter command  -- UNDO or UNDO --) :";
			 command=get_command(pos,row);
		     

			 if(command==0 || command==1 || command==2)
			        return 0;
			  else if(command==5 || command==6 || command==7 || command==8)
			  	    return 0;
		     
		     else{
		     	while( in_board_area(pos-65,row-1)==0 || command!=3){
		              cerr<<"This cell is wrong.Please enter new User1 move such as A 1, B 7, C 10 (If you want to undo the last play,please enter command  -- UNDO or UNDO --) :";
		              command=get_command(pos,row);

		              if(command==0 || command==1 || command==2)
			             return 0;
			          else if(command==5 || command==6 || command==7 || command==8)
			          	 return 0;
		     	}
		     }

		     control=control_empty_cell(pos-65,row-1);

		     while(control==0){
		     	cerr<<"This cell is filled.Please enter new User1 move such as A 1, B 7, C 10 (If you want to undo the last play,please enter command  -- UNDO or UNDO --) :";
		     	command=get_command(pos,row);

		     	if(command==0 || command==1 || command==2)
			        return 0;
			    else if(command==5 || command==6 || command==7 || command==8)
			    	return 0;

			    else{
			    	 while( in_board_area(pos-65,row-1)==0 || command!=3) {
			                 cerr<<"This cell is wrong.Please enter new User1 move such as A 1, B 7, C 10 (If you want to undo the last play,please enter command  -- UNDO or UNDO --) :";
			                 command=get_command(pos,row);

			                 if(command==0 || command==1 || command==2)
			                    return 0;
			                else if(command==5 || command==6 || command==7 || command==8)
			                	return 0;
			             }
			    }

			    control=control_empty_cell(pos-65,row-1);
		        
		     }
		   
		     play(pos,row);

		     set_user_select(2);

             
		     for(i=0;i<board_size;i++) {
			              int user1_won=game_end1(i,0);

			              if(user1_score>temp1_score){
			                 temp1_score=user1_score;
			              }
			                 user1_score=0;

			              if(user1_won==1){
			              	 cout<<*this;
			              	 cout<<"User1 Score: "<<temp1_score<<endl<<endl;
			              	 temp1_score=0;
			                 return 1;
			              }
			            set_destinated_board();
			 }

		     cout<<*this;
		     cout<<"User1 Score: "<<temp1_score<<endl<<endl;
		     temp1_score=0;
		     
		 }

		     if(get_user_select()==2){
		     	cout<<"Please enter new User2 move such as A 1, B 7, C 10 (If you want to undo the last play,please enter command  -- UNDO or UNDO --) :";
			    command=get_command(pos,row);

		        if(command==0 || command==1 || command==2)
			        return 0;
			    else if(command==5 || command==6 || command==7 || command==8)
			        return 0;
		     
		      else{
		     	while( in_board_area(pos-65,row-1)==0 || command!=3){
		              cerr<<"This cell is wrong.Please enter new User2 move such as A 1, B 7, C 10 (If you want to undo the last play,please enter command  -- UNDO or UNDO --) :";
		              command=get_command(pos,row);

		              if(command==0 || command==1 || command==2)
			             return 0;
			          else if(command==5 || command==6 || command==7 || command==8)
			             return 0;
		     	}
		     }

		     control=control_empty_cell(pos-65,row-1);

		     while(control==0){
		     	cerr<<"This cell is filled.Please enter new User2 move such as A 1, B 7, C 10 (If you want to undo the last play,please enter command  -- UNDO or UNDO --) :";
		     	command=get_command(pos,row);

		     	if(command==0 || command==1 || command==2)
			        return 0;
			    else if(command==5 || command==6 || command==7 || command==8)
			        return 0;

			    else{
			    	 while( in_board_area(pos-65,row-1)==0 || command!=3) {
			                 cerr<<"This cell is wrong.Please enter new User2 move such as A 1, B 7, C 10 (If you want to undo the last play,please enter command  -- UNDO or UNDO --) :";
			                 command=get_command(pos,row);

			                 if(command==0 || command==1 || command==2)
			                    return 0;
			                 else if(command==5 || command==6 || command==7 || command==8)
			                	return 0;
			             }
			    }

			    control=control_empty_cell(pos-65,row-1);
		        
		     }
		     
		     
		     play(pos,row);

		     set_user_select(1);

		     for(j=0;j<board_size;j++) {
			              int user2_won=game_end2(0,j);

			              if(user2_score>temp2_score){
			                 temp2_score=user2_score;
			              }
			                 user2_score=0;

			              if(user2_won==1){
			              	 cout<<*this;
			              	 cout<<"User2 Score: "<<temp2_score<<endl<<endl;
			              	 temp2_score=0;
			                 return 2;
			              }
			            set_destinated_board();
			  }

			     cout<<*this;
			     cout<<"User2 Score: "<<temp2_score<<endl<<endl;
			     temp2_score=0;
			}
		       
		              return 0;
		}


        /* This function checks if cells adjacent to the specified cell are empty or not. */

		int Hex::control_empty_neighbors(int col,int row) const{

			  auto counter=0;

			  if(in_board_area(col,row+1)==1 && control_empty_cell(col,row+1)==1)
			    counter++;
			  if(in_board_area(col-1,row)==1 && control_empty_cell(col-1,row)==1)
			    counter++;
			  if(in_board_area(col-1,row+1)==1 && control_empty_cell(col-1,row+1)==1)
			    counter++;
			  if(in_board_area(col+1,row)==1 && control_empty_cell(col+1,row)==1)
			    counter++;
			  if(in_board_area(col+1,row-1)==1 && control_empty_cell(col+1,row-1)==1)
			    counter++;
			  if(in_board_area(col,row-1)==1 && control_empty_cell(col,row-1)==1)
			    counter++;

			  if(counter>0)
			    return 1;
			  else
			    return 0;
			}


        /* This function fills the board according to the action of user 1 and calls the play function to make the computer move. */

		int Hex::user_computer(){
		     
		     int command;
			 int control,pos,row;
			 Cell user2_move;
			 int i,j,user1_won,user2_won;
		     int temp=0;

			 cout<<"Please enter new User1 move such as A 1, B 7, C 10 (If you want to undo the last play,please enter command  -- UNDO or UNDO --) :";
			 command=get_command(pos,row);
		    

			 if(command==0 || command==1 || command==2)
			        return 0;
			 else if(command==5 || command==6 || command==7 || command==8)
			        return 0;
		     
		     else{
		     	while( in_board_area(pos-65,row-1)==0 || command!=3){
		              cerr<<"This cell is wrong.Please enter new User1 move such as A 1, B 7, C 10 (If you want to undo the last play,please enter command  -- UNDO or UNDO --) :";
		              command=get_command(pos,row);

		              if(command==0 || command==1 || command==2)
			             return 0;
			          else if(command==5 || command==6 || command==7 || command==8)
			             return 0;
		     	}
		     }

		     control=control_empty_cell(pos-65,row-1);

		     while(control==0){
		     	cerr<<"This cell is filled.Please enter new User1 move such as A 1, B 7, C 10 (If you want to undo the last play,please enter command  -- UNDO or UNDO --) :";
		     	command=get_command(pos,row);

		     	if(command==0 || command==1 || command==2)
			        return 0;
			    else if(command==5 || command==6 || command==7 || command==8)
			        return 0;

			    else{
			    	 while( in_board_area(pos-65,row-1)==0 || command!=3) {
			                 cerr<<"This cell is wrong.Please enter new User1 move such as A 1, B 7, C 10 (If you want to undo the last play,please enter command  -- UNDO or UNDO --) :";
			                 command=get_command(pos,row);

			                 if(command==0 || command==1 || command==2)
			                    return 0;
			                 else if(command==5 || command==6 || command==7 || command==8)
			                	return 0;
			             }
			    }

			    control=control_empty_cell(pos-65,row-1);
		        
		     }
		   
		     set_user_select(1);
		     play(pos,row);
		     set_user1_pos(pos);
		     set_user1_row(row);
             

		     for(i=0;i<board_size;i++) {
			              user1_won=game_end1(i,0);

			              if(user1_score>temp1_score){
			                 temp1_score=user1_score;
			              }
			                 user1_score=0;

			              if(user1_won==1){
			              	 cout<<*this;
			              	 cout<<"User1 Score: "<<temp1_score<<endl<<endl;
			              	 temp1_score=0;
			                 return 1;
			              }
			            set_destinated_board();
			 }

		     cout<<*this;
		     cout<<"User1 Score: "<<temp1_score<<endl;
		     temp1_score=0;

             set_user_select(2);
		     user2_move=play();

		     for(j=0;j<board_size;j++) {
			     user2_won=game_end2(0,j);

			     if(user2_score>temp2_score){
			         temp2_score=user2_score;
			     }
			         user2_score=0;

			      if(user2_won==1){
			         cout<<*this;
			         cout<<"Computer move is: "<<user2_move.get_pos()<<" "<<user2_move.get_row()<<endl;
			         cout<<"Computer Score: "<<temp2_score<<endl<<endl;
			         temp2_score=0;
			         return 2;
			       }
			         set_destinated_board();
			 }

			 cout<<*this;
			 cout<<"Computer move is: "<<user2_move.get_pos()<<" "<<user2_move.get_row()<<endl;
             cout<<"Computer Score: "<<temp2_score<<endl<<endl;
             temp2_score=0;

		     return 0;
		     
		 }


        /* This function is used when any user wins the game or both users fail to win the game, print the necessary things on the screen and finish the program. */

		void Hex::finish_control(){

			int board_full=0,winner=0;
			set_user_select(1);


			while(board_full!=1 && winner==0) {

				if(game_type==0) {
				winner=two_user();
				board_full=all_board_fill();           
			   }

			    else{
			    	winner=user_computer();
			    	board_full=all_board_fill();
			    }
		}

		   
		    if(winner==1)
		    	cout<<"User1 won"<<endl<<endl;

		    else if(winner==2){
		    	if(get_game_type()==0)
		    	   cout<<"User2 won"<<endl<<endl;
		    	else
		    	   cout<<"Computer won"<<endl<<endl;
		    }

		    else{
		   	       cout<<"Game end"<<endl<<endl;
		   	}
		}


        /* This function returns the number of marked cells in one game. */

		int Hex::one_game_markedcells() const{

		    int i,j;
		    int marked_cells_num=0;

			for(i=0;i<board_size;i++){
				for(j=0;j<board_size;j++){
					if(hexCells[i][j].get_user_type()!=empty)
						marked_cells_num++;
				}
			}
			return marked_cells_num;
		}


       /* This function returns the number of marked cells in all active games. */

	   int Hex::all_games_markedcells(int num){

		marked_cells+=num;
		return marked_cells;

	}


        /* This function takes another const Hex object as a parameter and compares the Hex games. If calling
           game has more marked cells than parameter game, returns true. Otherwise, returns false. */

		bool Hex::operator ==(const Hex& game) const{
             
             return one_game_markedcells() == game.one_game_markedcells();

		}


        /* This function prints the two-dimensional Hex board to the screen and returns ostream reference. */

          ostream& operator <<(ostream& outStream, const Hex& obj){

			 char ch='a';
			 int i,j,k;

		     
			 outStream<<endl<<"  ";


			 for(i=0;i<obj.board_size;i++){
			 	outStream<<ch<<" ";
			    ch++;
			}

			outStream<<endl;

			for(i=0;i<obj.board_size;i++) {
		        outStream<<i+1<<" ";

			    for(k=0;k<i;k++) {
			        outStream<<" ";
			    }

		     for(j=0;j<obj.board_size;j++){
		     		if(obj.hexCells[i][j].get_user_type()==empty)
		     		   outStream<<". ";

			        else if(obj.hexCells[i][j].get_user_type()==user1){
			               if(obj.hexCells[i][j].get_destinated_cell()==0)
			                outStream<<"x ";
			                else
			                outStream<<"X ";
			            }
			         else{
			            	if(obj.hexCells[i][j].get_destinated_cell()==0)
			                outStream<<"o ";
			                else
			                outStream<<"O ";
			            }
		     	
		     	}
		     	outStream<<endl;
		     }
		        outStream<<endl;
			    
			    return outStream;
		}


		/* This overloading stream insertion operator  prints game type, board size, user select, undo num, the board which has 'x' and 'o' cells and
		   cells' selection order to given file and returns ofstream reference. */

		ofstream& operator <<(ofstream& outputStream, Hex& obj) {

			    int row=0,col=0;
		    	int counter=0;

		        outputStream<<obj.get_game_type()<<endl;
		    	outputStream<<obj.get_board_size()<<endl;
		    	outputStream<<obj.get_user_select()<<endl;
		    	outputStream<<obj.get_undo_num()<<endl;

		    	if(outputStream.is_open()){

		    	while(counter<(obj.get_board_size()*obj.get_board_size())){

		    		if(obj.hexCells[row][col].get_user_type()==user1)
		               outputStream<<"x";
		               
		    		else if(obj.hexCells[row][col].get_user_type()==user2)
		               outputStream<<"o";
		            
		            else if(obj.hexCells[row][col].get_user_type()==empty)
		            	outputStream<<".";

		           col++;

		           if(col==obj.get_board_size()){
		           	row+=1;
		           	col=0;
		           	outputStream<<endl;
		           }
		           counter++;
		    	}

		    	row=0,col=0;
		    	counter=0;

		    	while(counter<(obj.get_board_size()*obj.get_board_size())){

		    		outputStream<<obj.hexCells[row][col].get_cell_order()<<" ";
		    		col++;

		    		if(col==obj.get_board_size()){
		           	   row+=1;
		           	   col=0;
		           	   outputStream<<endl;
		             }
		               counter++;
		    	}
		    	       outputStream.close();
		      }

		        return outputStream;
		}
	 

        /* This overloading stream extraction operator reads game type, board size, user select, undo num, the board which has 'x' and 'o' cells and
           cells' selection order from the file and creates the current board and returns ifstream reference. */

	    ifstream& operator >>(ifstream& inputStream, Hex& obj){
           
            int new_board_size;
	    	char user;
	    	int row=0,col=0;
	    	int counter=0;

	        inputStream>>obj.game_type;
	    	inputStream>>obj.board_size;
	    	inputStream>>obj.user_select;
	    	inputStream>>obj.undo_num;

	    	obj.set_board();

	    	while(counter<(obj.get_board_size()*obj.get_board_size())){

                inputStream>>user;

	    		if(user=='x')
	               obj.hexCells[row][col].set_user_type(user1);
	               
	    		else if(user=='o')
	                obj.hexCells[row][col].set_user_type(user2);

	            else if(user=='.')
	            	 obj.hexCells[row][col].set_user_type(empty);

	           col++;

	           if(col==obj.get_board_size()){
	           	row+=1;
	           	col=0;
	           }

	           counter++;
	           
	    	}

	    	row=0,col=0;
	    	counter=0;
            int cell_order_num;
            int numbers;

	    	while(counter<(obj.get_board_size()*obj.get_board_size())){

                 inputStream>>numbers;
                 cell_order_num=numbers;
                 obj.hexCells[row][col].set_cell_order(cell_order_num);

                 col++;

	           if(col==obj.get_board_size()){
	           	row+=1;
	           	col=0;
	           }

	           counter++;
                 
           }

	    	inputStream.close();

	    	return inputStream;
	    	
	    }


        /* It undo the last play from Hex board. */

	    void Hex::operator --(){

	    	int i,j;
	    	

            if(get_game_type()==0){
            	for(i=0;i<board_size;i++){
	    		  for(j=0;j<board_size;j++){
	    			if(hexCells[i][j].get_cell_order()==undo_num){
	    				hexCells[i][j].set_user_type(empty);
	    				if(get_user_select()==1)
	    					set_user_select(2);
	    				else
	    					set_user_select(1);
	    			}
	    	      }
	            }
	            undo_num-=1;
            }

            else{
	    	 for(i=0;i<board_size;i++){
	    		for(j=0;j<board_size;j++){
	    			if(hexCells[i][j].get_cell_order()==undo_num || hexCells[i][j].get_cell_order()==undo_num-1){
	    				hexCells[i][j].set_user_type(empty);
	    			}
	    	    }
	         }

               undo_num-=2;
            
	        }
	    }


        /* It undo the last play from Hex board. */
        
	    void Hex::operator --(int ignoreMe){

	    	int i,j;
	    	

            if(get_game_type()==0){
            	for(i=0;i<board_size;i++){
	    		  for(j=0;j<board_size;j++){
	    			if(hexCells[i][j].get_cell_order()==undo_num){
	    				hexCells[i][j].set_user_type(empty);
	    				if(get_user_select()==1)
	    					set_user_select(2);
	    				else
	    					set_user_select(1);
	    			}
	    	      }
	            }
	            undo_num-=1;
            }

            else{
	    	 for(i=0;i<board_size;i++){
	    		for(j=0;j<board_size;j++){
	    			if(hexCells[i][j].get_cell_order()==undo_num || hexCells[i][j].get_cell_order()==undo_num-1){
	    				hexCells[i][j].set_user_type(empty);
	    			}
	    	    }
	         }

               undo_num-=2;
            
	        }
	    }


	    Hex::~Hex(){

            int i;

	    	for(i=0;i<board_size;i++){
	    		delete [] hexCells[i];
	    	}

	    	delete [] hexCells;

	    }