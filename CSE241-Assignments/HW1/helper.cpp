#include<iostream>
#include<random>
#include "helper.h"
using namespace std;


/* This function sets the numbers in the two-dimensional integer array board to 0 according to board size. */

void set_board(int board[SIZE][SIZE],int board_size) {

    int i,j;

     for(i=0;i<board_size;i++) {
        for(j=0;j<board_size;j++) {
            board[i][j]=0;
        }
     }
}


/* This function prints the characters x, o, X, O, which represent users according to the numbers in the cells of the two-dimensional array board. */

void print_board(int board[SIZE][SIZE],int destinated_board[SIZE][SIZE],int board_size) {

    int i,j,k;
    char ch='a';

    cout<<endl<<"  ";

    for(i=0;i<board_size;i++) {
        cout<<ch<<" ";
        ch++;
    }

    cout<<endl;

     for(i=0;i<board_size;i++) {

         cout<<i+1<<" ";

        for(k=0;k<i;k++) {
            cout<<" ";
        }

        for(j=0;j<board_size;j++) {

            if(board[i][j]==0)
                cout<<". ";
            else if(board[i][j]==1){
            	if(destinated_board[i][j]==0)
                cout<<"x ";
                else
                cout<<"X ";
            }
            else{
            	if(destinated_board[i][j]==0)
                cout<<"o ";
                else
                cout<<"O ";
            }
         }

           cout<<endl;

     }
           cout<<endl;
}


/* This function checks whether there are users in each cell of the two-dimensional array board. */

int all_board_fill(int board[SIZE][SIZE],int board_size) {

    int i,j,counter=0;

    for(i=0;i<board_size;i++) {
        for(j=0;j<board_size;j++){
            if(board[i][j]==0)
                counter++;
        }
    }

    if(counter==0)
        return 1;
    else
        return 0;
}


/* This function checks whether the specified location is in the board area using row and column parameters. */

int in_board_area(int board_size,int row,int col){
  if(row<0 || row>=board_size || col<0 || col>=board_size)
    return 0;
  else
    return 1;
}


/* This function checks whether there is a user in the specified cell using row and column parameters. */

int control_empty_cell(int board[SIZE][SIZE],int row,int col) {
    if(board[row][col]==0)
        return 1;
    else
        return 0;
}


/* This function checks if cells adjacent to the specified cell are empty or not. */

int control_empty_neighbors(int board[SIZE][SIZE],int board_size,int row,int col){

  int counter=0;

  if(in_board_area(board_size,row+1,col)==1 && control_empty_cell(board,row+1,col)==1)
    counter++;
  if(in_board_area(board_size,row,col-1)==1 && control_empty_cell(board,row,col-1)==1)
    counter++;
  if(in_board_area(board_size,row+1,col-1)==1 && control_empty_cell(board,row+1,col-1)==1)
    counter++;
  if(in_board_area(board_size,row,col+1)==1 && control_empty_cell(board,row,col+1)==1)
    counter++;
  if(in_board_area(board_size,row-1,col+1)==1 && control_empty_cell(board,row-1,col+1)==1)
    counter++;
  if(in_board_area(board_size,row-1,col)==1 && control_empty_cell(board,row-1,col)==1)
    counter++;

  if(counter>0)
    return 1;
  else
    return 0;
}


/* This function checks if neighbor cells of the cell at the specified location on the board are visited and whether the user in the cell at the specified location and the users in the neighboring cells are the same user. */

int control_same_cell1(int board[SIZE][SIZE],int destinated_board[SIZE][SIZE],int board_size,int row,int col) {

  int counter=0;

  if(in_board_area(board_size,row+1,col)==1){
    if(destinated_board[row+1][col]!=1 && board[row][col]==1 && board[row+1][col]==1)
      counter++;
  }

  if(in_board_area(board_size,row,col-1)==1){
    if(destinated_board[row][col-1]!=1 && board[row][col]==1 && board[row][col-1]==1)
      counter++;
  }


  if(in_board_area(board_size,row+1,col-1)==1){
    if(destinated_board[row+1][col-1]!=1 && board[row][col]==1 && board[row+1][col-1]==1)
      counter++;
  }

  if(in_board_area(board_size,row,col+1)==1){
    if(destinated_board[row][col+1]!=1 && board[row][col]==1 && board[row][col+1]==1)
      counter++;
  }

  if(in_board_area(board_size,row-1,col+1)==1){
    if(destinated_board[row-1][col+1]!=1 && board[row][col]==1 && board[row-1][col+1]==1)
      counter++;
  }


  if(in_board_area(board_size,row-1,col)==1){
    if(destinated_board[row-1][col]!=1 && board[row][col]==1 && board[row-1][col]==1)
      counter++;
  }


    if(counter==0)
      return 1;
    else
      return 0;



}


/* This function checks if neighbor cells of the cell at the specified location on the board are visited and whether the user in the cell at the specified location and the users in the neighboring cells are the same user. */

int control_same_cell2(int board[SIZE][SIZE],int destinated_board[SIZE][SIZE],int board_size,int row,int col) {

  int counter=0;

  if(in_board_area(board_size,row+1,col)==1){
    if(destinated_board[row+1][col]!=1 && board[row][col]==2 && board[row+1][col]==2)
      counter++;
  }

  if(in_board_area(board_size,row,col-1)==1){
    if(destinated_board[row][col-1]!=1 && board[row][col]==2 && board[row][col-1]==2)
      counter++;
  }

  if(in_board_area(board_size,row+1,col-1)==1){
    if(destinated_board[row+1][col-1]!=1 && board[row][col]==2 && board[row+1][col-1]==2)
      counter++;
  }

  if(in_board_area(board_size,row,col+1)==1){
    if(destinated_board[row][col+1]!=1 && board[row][col]==2 && board[row][col+1]==2)
      counter++;
  }

  if(in_board_area(board_size,row-1,col+1)==1){
    if(destinated_board[row-1][col+1]!=1 && board[row][col]==2 && board[row-1][col+1]==2)
      counter++;
  }


  if(in_board_area(board_size,row-1,col)==1){
    if(destinated_board[row-1][col]!=1 && board[row][col]==2 && board[row-1][col]==2)
      counter++;
  }


    if(counter==0)
      return 1;
    else
      return 0;



}


/* This function checks if user1 has won the game. This function proceeds from one of the cells in the first column and checks if it has the same user as neighboring cells and whether neighboring cells have been visited before. If it has the same user as one of the neighbor cells and the neighbor cell has not been visited before, it calls the game_end1 function again. If the function can get to the far right of the board, the function returns 1, otherwise it returns 0. */

int game_end1(int board[SIZE][SIZE],int destinated_board[SIZE][SIZE],int board_size,int row,int col) {


    if(col==board_size-1){
       destinated_board[row][col]=1;
       return 1;
   }

    else if(control_same_cell1(board,destinated_board,board_size,row,col)==1)
        return 0;

    else{

    if(in_board_area(board_size,row+1,col)==1){
         if(board[row][col]==1 && board[row+1][col]==1 && destinated_board[row+1][col]==0){
            destinated_board[row][col]=1;
            return 0+game_end1(board,destinated_board,board_size,row+1,col);
        }
    }

    if(in_board_area(board_size,row,col-1)==1){
         if(board[row][col]==1 && board[row][col-1]==1 && destinated_board[row][col-1]==0){
            destinated_board[row][col]=1;
            return 0+game_end1(board,destinated_board,board_size,row,col-1);
        }
    }


    if(in_board_area(board_size,row+1,col-1)==1){
         if(board[row][col]==1 && board[row+1][col-1]==1 && destinated_board[row+1][col-1]==0){
            destinated_board[row][col]=1;
            return 0+game_end1(board,destinated_board,board_size,row+1,col-1);
        }
    }

    if(in_board_area(board_size,row,col+1)==1){
         if(board[row][col]==1 && board[row][col+1]==1 && destinated_board[row][col+1]==0){
            destinated_board[row][col]=1;
            return 0+game_end1(board,destinated_board,board_size,row,col+1);
        }
    }

    if(in_board_area(board_size,row-1,col+1)==1){
         if(board[row][col]==1 && board[row-1][col+1]==1 && destinated_board[row-1][col+1]==0){
            destinated_board[row][col]=1;
            return 0+game_end1(board,destinated_board,board_size,row-1,col+1);
        }
    }


    if(in_board_area(board_size,row-1,col)==1){
         if(board[row][col]==1 && board[row-1][col]==1 && destinated_board[row-1][col]==0){
            destinated_board[row][col]=1;
            return 0+game_end1(board,destinated_board,board_size,row-1,col);
        }
      }
    }
      return 0;
    }


/* This function checks if user2 has won the game. This function proceeds from one of the cells in the first row and checks if it has the same user as neighboring cells and whether neighboring cells have been visited before. If it has the same user as one of the neighbor cells and the neighbor cell has not been visited before, it calls the game_end2 function again. If the function can get to the far down of the board, the function returns 1, otherwise it returns 0. */

int game_end2(int board[SIZE][SIZE],int destinated_board[SIZE][SIZE],int board_size,int row,int col) {


    if(row==board_size-1){
       destinated_board[row][col]=1;
       return 1;
    }
    else if(control_same_cell2(board,destinated_board,board_size,row,col)==1)
       return 0;

    else{

    if(in_board_area(board_size,row+1,col)==1){
         if(board[row][col]==2 && board[row+1][col]==2 && destinated_board[row+1][col]==0){
            destinated_board[row][col]=1;
            return 0+game_end2(board,destinated_board,board_size,row+1,col);
        }
    }

    if(in_board_area(board_size,row,col-1)==1){
         if(board[row][col]==2 && board[row][col-1]==2 && destinated_board[row][col-1]==0){
            destinated_board[row][col]=1;
            return 0+game_end2(board,destinated_board,board_size,row,col-1);
        }
    }

    
    if(in_board_area(board_size,row+1,col-1)==1){
         if(board[row][col]==2 && board[row+1][col-1]==2 && destinated_board[row+1][col-1]==0){
            destinated_board[row][col]=1;
            return 0+game_end2(board,destinated_board,board_size,row+1,col-1);
        }
    }

    if(in_board_area(board_size,row,col+1)==1){
         if(board[row][col]==2 && board[row][col+1]==2 && destinated_board[row][col+1]==0){
            destinated_board[row][col]=1;
            return 0+game_end2(board,destinated_board,board_size,row,col+1);
        }
    }

    if(in_board_area(board_size,row-1,col+1)==1){
         if(board[row][col]==2 && board[row-1][col+1]==2 && destinated_board[row-1][col+1]==0){
            destinated_board[row][col]=1;
            return 0+game_end2(board,destinated_board,board_size,row-1,col+1);
        }
    }


    if(in_board_area(board_size,row-1,col)==1){
         if(board[row][col]==2 && board[row-1][col]==2 && destinated_board[row-1][col]==0){
            destinated_board[row][col]=1;
            return 0+game_end2(board,destinated_board,board_size,row-1,col);
        }
      }
    }
      return 0;
    }


/* If any of the neighbor cells are empty according to the action of user1, one of the neighboring cells belongs to the computer randomly. If the neighbor cells are all full, the first empty cell on the board belongs to the computer. */

int computer_move(int board[SIZE][SIZE],int destinated_board[SIZE][SIZE],int board_size,int row,int col) {

     int rand_direction,num,counter=0,empty_cell_found=0,i=0,j=0,k,user2_won;
     char comp_letter;

     random_device device;
     mt19937 generator(device());
     uniform_int_distribution<int> distribution(0,5);

     if(control_empty_neighbors(board,board_size,row,col)==1) {

      while(counter==0) {

        rand_direction= distribution(generator);

     if(rand_direction==0 && in_board_area(board_size,row+1,col)==1 && control_empty_cell(board,row+1,col)==1){
            board[row+1][col]=2;
            comp_letter=col+65;
            num=row+2;
            counter++;
        }
        else if(rand_direction==1 && in_board_area(board_size,row,col-1)==1 && control_empty_cell(board,row,col-1)==1){
            board[row][col-1]=2;
            comp_letter=col+64;
            num=row+1;
            counter++;
        }
        
        else if(rand_direction==2 && in_board_area(board_size,row+1,col-1)==1 && control_empty_cell(board,row+1,col-1)==1){
            board[row+1][col-1]=2;
            comp_letter=col+64;
            num=row+2;
            counter++;
        }
        else if(rand_direction==3 && in_board_area(board_size,row,col+1)==1 && control_empty_cell(board,row,col+1)==1){
            board[row][col+1]=2;
            comp_letter=col+66;
            num=row+1;
            counter++;
        }
        else if(rand_direction==4 && in_board_area(board_size,row-1,col+1)==1 && control_empty_cell(board,row-1,col+1)==1){
            board[row-1][col+1]=2;
            comp_letter=col+66;
            num=row;
            counter++;
        }
       
        else if(rand_direction==5 && in_board_area(board_size,row-1,col)==1 && control_empty_cell(board,row-1,col)==1){
            board[row-1][col]=2;
            comp_letter=col+65;
            num=row;
            counter++;
        }
      }
    }

    else {

       while(empty_cell_found==0) {
        if(j==board_size-1) {
            if(board[i][j]==0){
                board[i][j]=2;
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
            if(board[i][j]==0) {
                board[i][j]=2;
                comp_letter=j+65;
                num=i+1;
                empty_cell_found=1;
            }
            else
                j++;
        }
       }
    }


               for(k=0;k<board_size;k++) {
                   user2_won=game_end2(board,destinated_board,board_size,0,k);
                   if(user2_won==1){
                   	  print_board(board,destinated_board,board_size);
                      cout<<"Computer move is: "<<comp_letter<<" "<<num<<endl<<endl;
                      return 1;
                   }
                      set_board(destinated_board,board_size);
               }

               print_board(board,destinated_board,board_size);
               cout<<"Computer move is: "<<comp_letter<<" "<<num<<endl<<endl;
               
               return 0;

}


/* This function fills the board according to the actions of user 1 and user 2. */

int two_user(int board[SIZE][SIZE],int destinated_board[SIZE][SIZE],int board_size) {

     char ch;
     int num,control,i,j,user1_won,user2_won;

     cout<<"Please enter User1 move such as A 1, B 7, C 10 :";
     cin>>ch>> num;

     while( in_board_area(board_size,num-1,ch-65)==0 ) {
         cerr<<"This cell is wrong.Please enter new User1 move:";
         cin>>ch>> num;
     }

     control=control_empty_cell(board,num-1,ch-65);

      while(control==0) {
          cerr<<"This cell is filled.Please enter new User1 move:";
          cin>>ch>> num;

           while( in_board_area(board_size,num-1,ch-65)==0 ) {
                 cerr<<"This cell is wrong.Please enter new User1 move:";
                 cin>>ch>> num;
           }

          control=control_empty_cell(board,num-1,ch-65);
     }

          board[num-1][ch-65]=1;
          

          for(i=0;i<board_size;i++) {
              user1_won=game_end1(board,destinated_board,board_size,i,0);
              if(user1_won==1){
              	 print_board(board,destinated_board,board_size);
                 return 1;
              }
            set_board(destinated_board,board_size);
           }

           print_board(board,destinated_board,board_size);


      cout<<"Please enter User2 move such as A 1, B 7, C 10 :";
      cin>>ch>> num;

     while( in_board_area(board_size,num-1,ch-65)==0 ) {
         cerr<<"This cell is wrong.Please enter new User2 move:";
         cin>>ch>> num;
     }

     control=control_empty_cell(board,num-1,ch-65);

      while(control==0) {
          cerr<<"This cell is filled.Please enter new User2 move:";
          cin>>ch>> num;

           while( in_board_area(board_size,num-1,ch-65)==0 ) {
                 cerr<<"This cell is wrong.Please enter new User2 move:";
                 cin>>ch>> num;
           }

          control=control_empty_cell(board,num-1,ch-65);
     }

          board[num-1][ch-65]=2;
          

          for(j=0;j<board_size;j++) {
         user2_won=game_end2(board,destinated_board,board_size,0,j);
         if(user2_won==1){
          print_board(board,destinated_board,board_size);
          return 2;
       }
            set_board(destinated_board,board_size);
           }

           print_board(board,destinated_board,board_size);
           
           return 0;

}


/* This function fills the board according to the action of user 1 and calls the computer_move function to make the computer move. */

int user_computer(int board[SIZE][SIZE],int destinated_board[SIZE][SIZE],int board_size) {

    char ch;
    int num,control,i,user1_won,user2_won;

     cout<<"Please enter User1 move such as A 1, B 7, C 10 :";
     cin>>ch>> num;

     while( in_board_area(board_size,num-1,ch-65)==0 ) {
         cerr<<"This cell is wrong.Please enter new User1 move:";
         cin>>ch>> num;
     }

     control=control_empty_cell(board,num-1,ch-65);

     while(control==0) {
          cerr<<"This cell is filled.Please enter new User1 move:";
          cin>>ch>> num;

           while( in_board_area(board_size,num-1,ch-65)==0 ) {
                 cerr<<"This cell is wrong.Please enter new User1 move:";
                 cin>>ch>> num;
           }

          control=control_empty_cell(board,num-1,ch-65);
     }

     board[num-1][ch-65]=1;
     
     
     for(i=0;i<board_size;i++) {
         user1_won=game_end1(board,destinated_board,board_size,i,0);
         if(user1_won==1){
          print_board(board,destinated_board,board_size);
          return 1;
       }
            set_board(destinated_board,board_size);
           }

     print_board(board,destinated_board,board_size);
     user2_won=computer_move(board,destinated_board,board_size,num-1,ch-65);
     
     if(user2_won==1)
        return 2;
     else
        return 0;

}
