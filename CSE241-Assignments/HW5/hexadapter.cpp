#include<iostream>
#include<fstream>
#include<random>
#include<string>
#include<vector>
#include<deque>

#include "hexadapter.h"

using namespace std;

namespace Mehmet{

    template<typename T1,typename T2>
	HexAdapter<T1,T2> ::HexAdapter() : AbstractHex() {
		set_board();
		print();
		finish_control();
	}

    template<typename T1,typename T2>
	HexAdapter<T1,T2> ::HexAdapter(int board_size_val) : AbstractHex(board_size_val) {
		set_board();
		print();
		finish_control();
	}

    template<typename T1,typename T2>
	HexAdapter<T1,T2> ::HexAdapter(int board_size_val,int game_type_val) : AbstractHex(board_size_val,game_type_val) {
		set_board();
		print();
		finish_control();
	}

    
	/* This function sets the position,row and user type of cells in the two dimensional template cell. */

    template<typename T1,typename T2>
	void HexAdapter<T1,T2>::set_board(){

		    T1 temp;
			AbstractHex::Cell c1;
		    int i,j;

			for(i=0;i<get_board_size();i++){
				for(j=0;j<get_board_size();j++){
					c1.set_pos(65+j);
		            c1.set_row(i+1);
		            c1.set_user_type(empty);
		            c1.set_destinated_cell();
		            temp.push_back(c1);
			}
		           hexCells.push_back(temp);
		           temp.clear();
		}
		     
	}


	/* This function resets the cells passed over while finding the end of the game. */
 
    template<typename T1,typename T2>
    void HexAdapter<T1,T2>::set_destinated_board(){

			int i,j;

			for(i=0;i<get_board_size();i++){
				for(j=0;j<get_board_size();j++){
					hexCells[i][j].set_destinated_cell();
				}
			}
		}


    /* This function checks whether the specified location is in the board area using pos and row parameters. */

    template<typename T1,typename T2>
	int HexAdapter<T1,T2>::in_board_area(int pos=0,int row=0) const{

			  if(  pos<0 || pos>=get_board_size() || row<0 || row>=get_board_size())
			    return 0;
			  else
			    return 1;
	}


    /* This function checks whether there is a user in the specified cell using pos_val and row_val parameters. */

    template<typename T1,typename T2>
    int HexAdapter<T1,T2>::control_empty_cell(int pos_val,int row_val) const{

		    if(hexCells[row_val][pos_val].get_user_type()==empty)
		    	return 1;
		    else
		    	return 0;

	}


    /* This function checks whether there are users in each cell of the two-dimensional template cell. */

    template<typename T1,typename T2>
	int HexAdapter<T1,T2>::all_board_fill() const{

			auto counter=0;
			int i,j;

			for(i=0;i<get_board_size();i++){
				for(j=0;j<get_board_size();j++){
		            if(hexCells[i][j].get_user_type()==empty)
		            	counter++;
				}
			}

			if(counter==0)
				return 1;
			else
				return 0;
	}


     /* This function checks if neighbor cells of the cell at the specified location on the board are visited and whether the user in the cell at the specified location 
        and the users in the neighboring cells are the same user. */

     template<typename T1,typename T2>
	 int HexAdapter<T1,T2>::control_same_cell1(int row,int pos) const{

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

    template<typename T1,typename T2>
    int HexAdapter<T1,T2>::control_same_cell2(int row,int pos) const{

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


        /* This function fills the board and sets user transitions according to the actions of user 1 and user 2. */

        template<typename T1,typename T2>
		int HexAdapter<T1,T2>::two_user(){
			 
			 int command;
			 int control;
			 int pos,row;
			 int i,j;

		     if(get_user_select()==1){
			 cout<<"Please enter new User1 move such as A 1, B 7, C 10 (Reset:OP 1, SetSize:OP 2, LastMove:OP 3, numOfMoves:OP 4) :";
			 command=get_command(pos,row);
		     

			 if(command==0 || command==1 || command==2 || command==3 || command==4 || command==5 || command==6)
			        return 0;
		     
		     else{
		     	while( in_board_area(pos-65,row-1)==0 || command!=7){
		              cerr<<"This cell is wrong.Please enter new User1 move such as A 1, B 7, C 10 (Reset:OP 1, SetSize:OP 2, LastMove:OP 3, numOfMoves:OP 4) :";
		              command=get_command(pos,row);

		              if(command==0 || command==1 || command==2 || command==3 || command==4 || command==5 || command==6)
			             return 0;
		     	}
		     }

		     control=control_empty_cell(pos-65,row-1);

		     while(control==0){
		     	cerr<<"This cell is filled.Please enter new User1 move such as A 1, B 7, C 10 (Reset:OP 1, SetSize:OP 2, LastMove:OP 3, numOfMoves:OP 4) :";
		     	command=get_command(pos,row);

		     	if(command==0 || command==1 || command==2 || command==3 || command==4 || command==5 || command==6)
			        return 0;

			    else{
			    	 while( in_board_area(pos-65,row-1)==0 || command!=7) {
			                 cerr<<"This cell is wrong.Please enter new User1 move such as A 1, B 7, C 10 (Reset:OP 1, SetSize:OP 2, LastMove:OP 3, numOfMoves:OP 4) :";
			                 command=get_command(pos,row);

			                 if(command==0 || command==1 || command==2 || command==3 || command==4 || command==5 || command==6)
			                    return 0;
			             }
			    }

			    control=control_empty_cell(pos-65,row-1);
		        
		     }
		   
		     play(pos,row);
		     set_last_move_pos(pos);
		     set_last_move_row(row);
		     set_user_select(2);
		     

		     for(i=0;i<get_board_size();i++) {
			              bool user1_won=isEnd(i,0,1);
			              if(user1_won==true){
			              	 print();
			                 return 1;
			              }
			            set_destinated_board();
			 }

		     print();
		     
		 }

		     if(get_user_select()==2){
		     	cout<<"Please enter new User2 move such as A 1, B 7, C 10 (Reset:OP 1, SetSize:OP 2, LastMove:OP 3, numOfMoves:OP 4) :";
			    command=get_command(pos,row);

		        if(command==0 || command==1 || command==2 || command==3 || command==4 || command==5 || command==6)
			        return 0;
		     
		      else{
		     	while( in_board_area(pos-65,row-1)==0 || command!=7){
		              cerr<<"This cell is wrong.Please enter new User2 move such as A 1, B 7, C 10 (Reset:OP 1, SetSize:OP 2, LastMove:OP 3, numOfMoves:OP 4) :";
		              command=get_command(pos,row);

		              if(command==0 || command==1 || command==2 || command==3 || command==4 || command==5 || command==6)
			             return 0;
		     	}
		     }

		     control=control_empty_cell(pos-65,row-1);

		     while(control==0){
		     	cerr<<"This cell is filled.Please enter new User2 move such as A 1, B 7, C 10 (Reset:OP 1, SetSize:OP 2, LastMove:OP 3, numOfMoves:OP 4) :";
		     	command=get_command(pos,row);

		     	if(command==0 || command==1 || command==2 || command==3 || command==4 || command==5 || command==6)
			        return 0;

			    else{
			    	 while( in_board_area(pos-65,row-1)==0 || command!=7) {
			                 cerr<<"This cell is wrong.Please enter new User2 move such as A 1, B 7, C 10 (Reset:OP 1, SetSize:OP 2, LastMove:OP 3, numOfMoves:OP 4) :";
			                 command=get_command(pos,row);

			                 if(command==0 || command==1 || command==2 || command==3 || command==4 || command==5 || command==6)
			                    return 0;
			             }
			    }

			    control=control_empty_cell(pos-65,row-1);
		        
		     }
		     
		     play(pos,row);
		     set_last_move_pos(pos);
		     set_last_move_row(row);
		     set_user_select(1);
		     
		     for(j=0;j<get_board_size();j++) {
			              bool user2_won=isEnd(0,j,2);
			              if(user2_won==true){
			              	 print();
			                 return 2;
			              }
			            set_destinated_board();
			  }

			     print();
			}
		       
		              return 0;
		}


        /* This function checks if cells adjacent to the specified cell are empty or not. */

        template<typename T1,typename T2>
		int HexAdapter<T1,T2>::control_empty_neighbors(int col,int row) const{

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

        template<typename T1,typename T2>
        int HexAdapter<T1,T2>::user_computer(){
		     
		     int command;
			 int control,pos,row;
			 bool user1_won;
			 int i,user2_won;
		     

			 cout<<"Please enter new User1 move such as A 1, B 7, C 10 (Reset:OP 1, SetSize:OP 2, LastMove:OP 3, numOfMoves:OP 4) :";
			 command=get_command(pos,row);
		    

			 if(command==0 || command==1 || command==2 || command==3 || command==4 || command==5 || command==6)
			        return 0;
		     
		     else{
		     	while( in_board_area(pos-65,row-1)==0 || command!=7){
		              cerr<<"This cell is wrong.Please enter new User1 move such as A 1, B 7, C 10 (Reset:OP 1, SetSize:OP 2, LastMove:OP 3, numOfMoves:OP 4) :";
		              command=get_command(pos,row);

		              if(command==0 || command==1 || command==2 || command==3 || command==4 || command==5 || command==6)
			             return 0;
		     	}
		     }

		     control=control_empty_cell(pos-65,row-1);

		     while(control==0){
		     	cerr<<"This cell is filled.Please enter new User1 move such as A 1, B 7, C 10 (Reset:OP 1, SetSize:OP 2, LastMove:OP 3, numOfMoves:OP 4) :";
		     	command=get_command(pos,row);

		     	if(command==0 || command==1 || command==2 || command==3 || command==4 || command==5 || command==6)
			        return 0;

			    else{
			    	 while( in_board_area(pos-65,row-1)==0 || command!=7) {
			                 cerr<<"This cell is wrong.Please enter new User1 move such as A 1, B 7, C 10 (Reset:OP 1, SetSize:OP 2, LastMove:OP 3, numOfMoves:OP 4) :";
			                 command=get_command(pos,row);

			                 if(command==0 || command==1 || command==2 || command==3 || command==4 || command==5 || command==6)
			                    return 0;
			             }
			    }

			    control=control_empty_cell(pos-65,row-1);
		        
		     }
		   
		     set_user_select(1);
		     play(pos,row);
		     set_user1_pos(pos);
		     set_user1_row(row);
		     set_last_move_pos(pos);
		     set_last_move_row(row);

		     for(i=0;i<get_board_size();i++) {
			              user1_won=isEnd(i,0,1);
			              if(user1_won==true){
			              	 print();
			                 return 1;
			              }
			            set_destinated_board();
			 }

		     print();
             set_user_select(2);
		     user2_won=play();

		     if(user2_won==1)
		     	return 2;
		     else
		     	return 0;

		     
		 }


         /* This function takes a command from the user. If the user wants to save the board, it calls save_file function and returns 0. If the user wants to load the board, 
            if file can not open, returns 1, else it calls load_file function and returns 2. If the user wants to reset the board, it calls reset function and returns 3.
            If the user wants to set the size of board again, it calls setSize function and returns 4. If the user wants to see last move of board, it calls last_move function 
            and returns 5. If the user wants to see number of moves in the board, it calls numberOfMoves function and returns 6. If the user wants to move on the board, it
            applies move and returns 7. */

         template<typename T1,typename T2>
		 int HexAdapter<T1,T2>::get_command(int &pos,int &row){
			  
			  string str1,str2;
			  string filename;
			  int load_control;
			  char ch;
			  int num;

			  cin>>str1>>str2;

			  if(str1=="SAVE"){
			      filename=str2;
			      writeToFile(filename);
			      return 0;
			     }

			     else if(str1=="LOAD"){
			      filename=str2;
			      load_control=readFromFile(filename);

			      if(load_control==0)
		             return 1;
		          else{
			      print();
			      return 2;
			      }

			     }

			     else if(str1=="OP" && str2=="1"){
			     	cout<<"Board is resetting"<<endl;
			     	reset();
			     	print();
			     	return 3;
			     }

			     else if(str1=="OP" && str2=="2"){
			     	setSize();
			     	print();
			     	return 4;
			     }

			     else if(str1=="OP" && str2=="3"){
			     	try{
			     	   AbstractHex::Cell c=last_move();
			     	   cout<<"Last move:"<<c.get_pos()<<" "<<c.get_row()<<endl;
			     	   print();
			     	}
			     	catch(Exc2){
             	      cout<<"There is no last move in this board"<<endl;
             	      print();
                    }
			     	return 5;
			     }

			     else if(str1=="OP" && str2=="4"){
			     	int num_moves=numberOfMoves();
			     	cout<<"Number of moves in this board:"<<num_moves<<endl;
			     	print();
			     	return 6;
			     }

			     else{

		          if(get_board_size()<10){

				      if(str1.length()==1 && str2.length()==1){
				        pos=str1[0];
				        row=str2[0]-48;
				        return 7;
				      }
				      else{
				      	pos=str1[0];
				        row=str2[0]-48;
				      	return 8;
				      }
			      }

			      else {

			    	if(str1.length()==1 && str2.length()<=2){
			           pos=str1[0];
			           row=stoi(str2);
			           return 7;
			        }
			        else{
			            pos=str1[0];
				        row=str2[0]-48;
			      	   return 8;
			        }
			      }
			   }
			}


          /* This function is used when any user wins the game or both users fail to win the game or board has invalid character (other than 'x' and 'o') , 
             print the necessary things on the screen and finish the program. */
          
          template<typename T1,typename T2>
          void HexAdapter<T1,T2>::finish_control(){

			int board_full=0,winner=0;
			bool board_valid=true;
			set_user_select(1);

			while(board_full!=1 && winner==0 && board_valid==true) {

				if(get_game_type()==0) {
				    winner=two_user();
				    board_full=all_board_fill();  
				    board_valid=valid_check(this);         
			   }

			    else{
			    	winner=user_computer();
			    	board_full=all_board_fill();
			    	board_valid=valid_check(this);
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


        /* This function prints the two-dimensional template cell to the screen. */
        
        template<typename T1,typename T2>
        void HexAdapter<T1,T2>::print() const{

			 char ch='a';
			 int i,j,k;

			 cout<<endl<<"  ";

			 for(i=0;i<get_board_size();i++){
			 	cout<<ch<<" ";
			    ch++;
			}

			cout<<endl;

			for(i=0;i<get_board_size();i++) {
		        cout<<i+1<<" ";

			    for(k=0;k<i;k++) {
			        cout<<" ";
			    }

		     for(j=0;j<get_board_size();j++){
		     		if(hexCells[i][j].get_user_type()==empty)
		     		   cout<<". ";

			        else if(hexCells[i][j].get_user_type()==user1){
			               if(hexCells[i][j].get_destinated_cell()==0)
			                cout<<"x ";
			                else
			                cout<<"X ";
			            }
			         else if(hexCells[i][j].get_user_type()==user2){
			            	if(hexCells[i][j].get_destinated_cell()==0)
			                cout<<"o ";
			                else
			                cout<<"O ";
			            }
			         else
			         	cout<<hexCells[i][j].get_invalid_char();
		     	
		     	}
		     	cout<<endl;
		     }
		        cout<<endl;

		}


        /* This function opens the relevant file according to the filename parameter. If file can open, it reads game type, board size, user select, last move pos, last move row 
           and the board which has 'x' and 'o' cells from the file and creates the current board and returns 1. If file can not open, function returns 0. */

        template<typename T1,typename T2>
		int HexAdapter<T1,T2>::readFromFile(string filename){

	    	ifstream inputStream;
	    	inputStream.open(filename);
	    	char user;
	    	int game_type_val,board_size_val,user_select_val,last_move_pos_val,last_move_row_val;
	    	int row=0,col=0;

	    	if(inputStream.is_open()){

	        inputStream>>game_type_val;
	    	inputStream>>board_size_val;
	    	inputStream>>user_select_val;
	    	inputStream>>last_move_pos_val;
	    	inputStream>>last_move_row_val;

	    	set_game_type(game_type_val);
	    	set_board_size(board_size_val);
	    	set_user_select(user_select_val);
	    	set_last_move_pos(last_move_pos_val);
	    	set_last_move_row(last_move_row_val);
	    	hexCells.clear();
            set_board();

	    	while(inputStream>>user){

	    		if(user=='x')
	                 hexCells[row][col].set_user_type(user1);
	               
	    		else if(user=='o')
	                 hexCells[row][col].set_user_type(user2);

	            else if(user=='.')
	            	 hexCells[row][col].set_user_type(empty);
	            else{
	            	 hexCells[row][col].set_user_type(invalid);
	            	 hexCells[row][col].set_invalid_char(user);
	            }

	           col++;

	           if(col==get_board_size()){
	           	row+=1;
	           	col=0;
	           }
	           
	    	}
	    	inputStream.close();
	    	return 1;
	     }

	        else
	        	cerr<<"File can not open"<<endl;
	            return 0;
	    }


        /* This function opens the related file according to the filename parameter. It prints game type, board size, user select, last move pos, last move row 
           and the board which has 'x' and 'o' cells to given file. */

        template<typename T1,typename T2>
	    void HexAdapter<T1,T2>::writeToFile(string filename) const{

			    ofstream outputStream;
		    	outputStream.open(filename);
		    	int row=0,col=0;
		    	int counter=0;

		        outputStream<<get_game_type()<<endl;
		    	outputStream<<get_board_size()<<endl;
		    	outputStream<<get_user_select()<<endl;
		    	outputStream<<get_last_move_pos()<<endl;
		    	outputStream<<get_last_move_row()<<endl;

		    	if(outputStream.is_open()){

		    	while(counter<(get_board_size()*get_board_size())){

		    		if(hexCells[row][col].get_user_type()==user1)
		               outputStream<<'x';
		               
		    		else if(hexCells[row][col].get_user_type()==user2)
		               outputStream<<'o';
		            
		            else if(hexCells[row][col].get_user_type()==empty)
		            	outputStream<<'.';

		           col++;

		           if(col==get_board_size()){
		           	row+=1;
		           	col=0;
		           	outputStream<<endl;
		           }
		           counter++;
		    	}
		    	outputStream.close();
		      }

		        else
		        	cerr<<"File can not open"<<endl;
		    }


          /* This function resets the board to the beginning. */

          template<typename T1,typename T2>
		  void HexAdapter<T1,T2>::reset(){

			int i,j;

			for(i=0;i<get_board_size();i++){
				for(j=0;j<get_board_size();j++){
					hexCells[i][j].set_user_type(empty);
				}
			}

			if(get_user_select()==2)
				set_user_select(1);

			set_last_move_pos(-1);
            set_last_move_row(-1);
				
		}


        /* This function sets the board size to the given values. The board is reset after this operation. */

        template<typename T1,typename T2>
		void HexAdapter<T1,T2>::setSize(){

			int board_size_val;
			int input_truth=0;

			while(input_truth==0){
		  
		        cout<<"Please enter new board size:";
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

		  	if(get_user_select()==2)
		  		set_user_select(1);

            set_last_move_pos(-1);
            set_last_move_row(-1);
		  	hexCells.clear();
            set_board();

		}


         /* This function fills the cell that user 1 or user 2 has selected on the board using the pos_val and row_val parameters. */

         template<typename T1,typename T2>
         void HexAdapter<T1,T2>::play(char pos_val,int row_val){
			
		    if(get_user_select()==1)
		       hexCells[row_val-1][pos_val-65].set_user_type(user1);
		              
		    else
		       hexCells[row_val-1][pos_val-65].set_user_type(user2);
		    
		}


        /* This function makes the computer move according to the cell user 1 has selected. */

        template<typename T1,typename T2>
		int HexAdapter<T1,T2>::play(){
		    
		         int rand_direction,num,counter=0,empty_cell_found=0,i=0,j=0,k;
		         bool user2_won;
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
			        if(j==get_board_size()-1) {
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

                       set_last_move_pos(comp_letter);
                       set_last_move_row(num);

			               for(k=0;k<get_board_size();k++) {
			                   user2_won=isEnd(0,k,2);
			                   if(user2_won==true){
			                   	  print();
			                      cout<<"Computer move is: "<<comp_letter<<" "<<num<<endl<<endl;
			                      return 1;
			                   }
			                      set_destinated_board();
			               }

			               print();
			               cout<<"Computer move is: "<<comp_letter<<" "<<num<<endl<<endl;
			               
			               return 0;
		     
		 }


         /* This function checks if user1 or user2 has won the game. This function proceeds from one of the cells in the first column or first row and checks if it has the same user 
            as neighboring cells and whether neighboring cells have been visited before. If it has the same user as one of the neighbor cells and the neighbor cell has not been visited 
            before, it calls the isEnd function again. If the function can get to the far right or far down of the board, the function returns 1, otherwise it returns 0. */

         template<typename T1,typename T2>
         bool HexAdapter<T1,T2>::isEnd(int row,int pos,int user_select_val) {
	        
	       if(user_select_val==1){

		    if(pos==get_board_size()-1){
		       hexCells[row][pos].change_destinated_cell();
		       return true;
		   }

		    else if(control_same_cell1(row,pos)==1)
		        return false;

		    else{

		    if(in_board_area(pos,row+1)==1){
		         if(hexCells[row][pos].get_user_type()==user1 && hexCells[row+1][pos].get_user_type()==user1 && hexCells[row+1][pos].get_destinated_cell()==0){
		            hexCells[row][pos].change_destinated_cell();
		            return isEnd(row+1,pos,user_select_val);
		        }
		    }

		    if(in_board_area(pos-1,row)==1){
		         if(hexCells[row][pos].get_user_type()==user1 && hexCells[row][pos-1].get_user_type()==user1 && hexCells[row][pos-1].get_destinated_cell()==0){
		            hexCells[row][pos].change_destinated_cell();
		            return isEnd(row,pos-1,user_select_val);
		        }
		    }


		    if(in_board_area(pos-1,row+1)==1){
		         if(hexCells[row][pos].get_user_type()==user1 && hexCells[row+1][pos-1].get_user_type()==user1 && hexCells[row+1][pos-1].get_destinated_cell()==0){
		            hexCells[row][pos].change_destinated_cell();
		            return isEnd(row+1,pos-1,user_select_val);
		        }
		    }

		    if(in_board_area(pos+1,row)==1){
		         if(hexCells[row][pos].get_user_type()==user1 && hexCells[row][pos+1].get_user_type()==user1 && hexCells[row][pos+1].get_destinated_cell()==0){
		            hexCells[row][pos].change_destinated_cell();
		            return isEnd(row,pos+1,user_select_val);
		        }
		    }

		    if(in_board_area(pos+1,row-1)==1){
		         if(hexCells[row][pos].get_user_type()==user1 && hexCells[row-1][pos+1].get_user_type()==user1 && hexCells[row-1][pos+1].get_destinated_cell()==0){
		            hexCells[row][pos].change_destinated_cell();
		            return isEnd(row-1,pos+1,user_select_val);
		        }
		    }


		    if(in_board_area(pos,row-1)==1){
		         if(hexCells[row][pos].get_user_type()==user1 && hexCells[row-1][pos].get_user_type()==user1 && hexCells[row-1][pos].get_destinated_cell()==0){
		            hexCells[row][pos].change_destinated_cell();
		            return isEnd(row-1,pos,user_select_val);
		        }
		      }
		    }

		      return false;

		    }


		   else if(user_select_val==2){

              if(row==get_board_size()-1){
	            hexCells[row][pos].change_destinated_cell();
	            return true;
	          }

		    else if(control_same_cell2(row,pos)==1)
		        return false;

		    else{

		    if(in_board_area(pos,row+1)==1){
		         if(hexCells[row][pos].get_user_type()==user2 && hexCells[row+1][pos].get_user_type()==user2 && hexCells[row+1][pos].get_destinated_cell()==0){
		            hexCells[row][pos].change_destinated_cell();
		            return isEnd(row+1,pos,user_select_val);
		        }
		    }

		    if(in_board_area(pos-1,row)==1){
		         if(hexCells[row][pos].get_user_type()==user2 && hexCells[row][pos-1].get_user_type()==user2 && hexCells[row][pos-1].get_destinated_cell()==0){
		            hexCells[row][pos].change_destinated_cell();
		            return isEnd(row,pos-1,user_select_val);
		        }
		    }


		    if(in_board_area(pos-1,row+1)==1){
		         if(hexCells[row][pos].get_user_type()==user2 && hexCells[row+1][pos-1].get_user_type()==user2 && hexCells[row+1][pos-1].get_destinated_cell()==0){
		            hexCells[row][pos].change_destinated_cell();
		            return isEnd(row+1,pos-1,user_select_val);
		        }
		    }

		    if(in_board_area(pos+1,row)==1){
		         if(hexCells[row][pos].get_user_type()==user2 && hexCells[row][pos+1].get_user_type()==user2 && hexCells[row][pos+1].get_destinated_cell()==0){
		            hexCells[row][pos].change_destinated_cell();
		            return isEnd(row,pos+1,user_select_val);
		        }
		    }

		    if(in_board_area(pos+1,row-1)==1){
		         if(hexCells[row][pos].get_user_type()==user2 && hexCells[row-1][pos+1].get_user_type()==user2 && hexCells[row-1][pos+1].get_destinated_cell()==0){
		            hexCells[row][pos].change_destinated_cell();
		            return isEnd(row-1,pos+1,user_select_val);
		        }
		    }


		    if(in_board_area(pos,row-1)==1){
		         if(hexCells[row][pos].get_user_type()==user2 && hexCells[row-1][pos].get_user_type()==user2 && hexCells[row-1][pos].get_destinated_cell()==0){
		            hexCells[row][pos].change_destinated_cell();
		            return isEnd(row-1,pos,user_select_val);
		        }
		      }
		    }

		      return false;
			}

        }


        /* This function takes two indexes and returns the corresponding cell content. It throws an object of an Exc1 class if row or pos indexes are not in board area. */

        template<typename T1,typename T2>
        AbstractHex::Cell HexAdapter<T1,T2>::operator () (int row,int pos) throw(Exc1){

             try{
             	if(row<0 || row>=get_board_size() || pos<0 || pos>=get_board_size())
             		throw Exc1();

             	    return hexCells[row][pos];
             }

             catch(Exc1){
             	cout<<"Board cell's indexes is not proper"<<endl;
             }

        }


        /* If the boards are the same, it means that two boards are equal and return true. Otherwise, return false. */
 
        template<typename T1,typename T2>
        bool HexAdapter<T1,T2>::operator ==(HexAdapter& game){
            
             int i,j;
            int counter=0;

            bool same=AbstractHex::operator ==(game);
            AbstractHex::Cell c1,c2;

            for(i=0;i<get_board_size();i++){
            	for(j=0;j<get_board_size();j++){

            		c1=operator() (i,j);
            		c2=game.operator() (i,j);
            		
                    if(c1.get_user_type()==c2.get_user_type())
                    	counter++;
            	}
            }

            if(same==true && counter==get_board_size()*get_board_size())
            	return true;
            else
            	return false;
        	
        }


        /* This function returns the last move. If there is no last move, it throws an object of an Exc2 class. */

        template<typename T1,typename T2>
        AbstractHex::Cell HexAdapter<T1,T2>::last_move() throw(Exc2){

        	 	if(get_last_move_pos()==-1 && get_last_move_row()==-1)
        	 		throw Exc2();

                    char pos=get_last_move_pos();
        	 	    int row=get_last_move_row();
        	 	    
        	 	    return operator() (row-1,pos-65);
 
        }
         

        /* This function returns the number of moves this board made. */

        template<typename T1,typename T2>
        int HexAdapter<T1,T2>::numberOfMoves(){

            int i,j;
            int move_num=0;
            AbstractHex::Cell c;

            for(i=0;i<get_board_size();i++){
            	for(j=0;j<get_board_size();j++){
                    c=operator() (i,j);
                    if(c.get_user_type()!=empty)
                    	move_num++;
            	}
            }

            return move_num;

        }


         /* This function checks that board has any invalid character or not. If the board has a character other than 'x' and 'o', it returns false. Otherwise, it returns true. */

         template<typename T1,typename T2>
         bool HexAdapter<T1,T2>::valid_moves() const{

        	int i,j;

        	for(i=0;i<get_board_size();i++){
        		for(j=0;j<get_board_size();j++){
        			if(hexCells[i][j].get_user_type()==invalid)
        				return false;
        		}
        	}

        	return true;
        }


        template class HexAdapter<vector<AbstractHex::Cell>, vector<vector<AbstractHex::Cell>> >;
        template class HexAdapter<deque<AbstractHex::Cell>, deque<deque<AbstractHex::Cell>> >;
        

}
