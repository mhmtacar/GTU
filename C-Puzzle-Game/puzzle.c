#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<string.h>


typedef enum {empty,full}Puzzle;
#define Empty_Row {empty,empty,empty,empty,empty,empty,empty,empty,empty,empty,empty,empty,empty,empty,empty,empty,empty,empty,empty,empty}


		/* This function returns the length of word. */

		int length_of_words(char word[20]) {

		     int length=strlen(word);
		     return length;

		}
             

		/* This function provides that same integer word_place variable does not come in each loop. If word_place variable has different value from previous word_place_variable values,
		I assign this value to word_place_array which is integer array. After that, according to word_place variable, fp pointer will come to desired line and assign these characters to
		words which is two dimensional character array. */

		void random_words(char words[10][20]) {

		    FILE *fp=fopen("words.txt","r");
			int i=0,j,k;
			int word_place;
		    char c;
		    int col=0;
		    int word_place_array[10];
		    int counter=0;

			while(i<10) {

			word_place=1+rand()%100;
		    
		    for(j=0;j<i;j++) {
		    	if(word_place_array[j]==word_place) {
		    		counter++;
		    	}
		    }
			
			if(counter==0) {

				word_place_array[i]=word_place;
		    
			for(k=0;k<word_place-1;k++) {

		           c=getc(fp);

		           while(c!='\n') {
		                c=getc(fp);
		           }

		       }

		           c=getc(fp);

		           while(c!='\n' && c!=EOF) {
		                words[i][col]=c;
		                c=getc(fp);
		                col++;
		       }

		               if(word_place==100) {
		                  words[i][col]='\0';
		               }
		               else {
		               words[i][col-1]='\0';
		               }

		               col=0;
		               fseek(fp,0,SEEK_SET);
		               i++;
		           }

		           counter=0;    
		      }

		      
		              fclose(fp);

		}


		/* This function prints words which is two dimensional character array. */

		void print_words(char words[10][20]) {

		         int k;

			 for(k=0;k<10;k++) {

		      	  printf("%s\n",words[k]);
		      	  
		      }
                  printf("\n");
		}


		/* This function controls that intented direction of puz which is two dimensional enum array has empty or not. The length parameter determines how many coordinates to move in the 
		puz array in the specified direction. Direction is determined according to integer random parameter. Row and column parameters determines the beginning coordinates of the puz array 
		in the desired direction. If whole coordinates is empty in intented direction, function returns 1, otherwise returns 0. */

		int control_empty(int row,int column,int length,int random,Puzzle puz[20][20]) {

		  int i;
		  int counter=0;


		    if(random==1) {

		    for(i=0;i<length;i++) {
		        
		        if(puz[row][column]!=empty) {
		          counter++;
		        }

		        column++;

		    }

		}


		    else if(random==2) {

		    for(i=0;i<length;i++) {
		        
		        if(puz[row][column]!=empty) {
		          counter++;
		        }

		        column--;

		    }

		}


		    else if(random==3) {

		    for(i=0;i<length;i++) {
		        
		        if(puz[row][column]!=empty) {
		          counter++;
		        }

		        row++;

		    }

		}


		   else if(random==4) {

		    for(i=0;i<length;i++) {
		        
		        if(puz[row][column]!=empty) {
		          counter++;
		        }

		        row--;

		    }

		}


		   else if(random==5) {

		    for(i=0;i<length;i++) {
		        
		        if(puz[row][column]!=empty) {
		          counter++;
		        }

		        row++;
		        column++;

		    }

		}


		   else if(random==6) {

		    for(i=0;i<length;i++) {
		        
		        if(puz[row][column]!=empty) {
		          counter++;
		        }

		        row--;
		        column--;

		    }

		}


		   else if(random==7) {

		    for(i=0;i<length;i++) {
		        
		        if(puz[row][column]!=empty) {
		          counter++;
		        }

		        row++;
		        column--;

		    }

		}


		   else {

		         for(i=0;i<length;i++) {
		        
		        if(puz[row][column]!=empty) {
		          counter++;
		        }

		        row--;
		        column++;

		    }

		   }


		   if(counter==0) {
		    return 1;
		   }

		   else {
		    return 0;
		   }


		}

    
		/* This function puts words parameter's different word to puzzle parameter. In while loop, function produces random direction, random row beginning and random column beginning
		which are integer value. According to direction value, if word of words parameter has proper length to put in this direction, function called control_empty function in order to
		coordinates of the puzzle in this direction is empty or not. If control_empty function returns 1, program puts word to puzzle according to row beginning and column beginning 
		value. If 10 words are put to puzzle, function exits from while loop. After that, function fill puzzle's empty coordinates with random characters. */    

		void fill_puzzle(char puzzle[20][20],char words[10][20],Puzzle puz[20][20]) {

		     int random_dir;
		     int row_beg,col_beg;
		     int counter1=0;
		     int pos_proper;
		     int temp1,temp2;
		     int len;
		     char random;
		     int i=0,j=0;
		     int k,l,m;

		     while(counter1<10) {

		           random_dir=1+rand()%8;
		           row_beg=rand()%20;
		           col_beg=rand()%20;
		           len=length_of_words(words[counter1]);


		               if(random_dir==1) {

		              if(len<=(19-col_beg)+1) {

		              	pos_proper=control_empty(row_beg,col_beg,len,random_dir,puz);

		                 if(pos_proper==1) {

		                 	for(k=0;k<len;k++) {

		                 puzzle[row_beg][col_beg]=words[i][j];
		                 puz[row_beg][col_beg]=full;
		                 j++;
		                 col_beg++;
		             }
		                i++;
		                j=0;
		                counter1++;

		              	}

		            }

		              }


		            

		            else if(random_dir==2) {

		              if(len<=(col_beg-0)+1) {

		              	pos_proper=control_empty(row_beg,col_beg,len,random_dir,puz);

		                 if(pos_proper==1) {

		                 	for(k=0;k<len;k++) {

		                 puzzle[row_beg][col_beg]=words[i][j];
		                 puz[row_beg][col_beg]=full;
		                 j++;
		                 col_beg--;
		             }
		                i++;
		                j=0;
		                counter1++;

		              	}

		            }

		              }


		              else if(random_dir==3) {

		              	 if(len<=(19-row_beg)+1) {

		              	 	pos_proper=control_empty(row_beg,col_beg,len,random_dir,puz);

		                 if(pos_proper==1) {

		                 	for(k=0;k<len;k++) {

		                 puzzle[row_beg][col_beg]=words[i][j];
		                 puz[row_beg][col_beg]=full;
		                 j++;
		                 row_beg++;
		             }
		                i++;
		                j=0;
		                counter1++;

		              	}

		            }

		              }


		               else if(random_dir==4) {

		              	 if(len<=(row_beg-0)+1) {

		              	 	 pos_proper=control_empty(row_beg,col_beg,len,random_dir,puz);

		                 if(pos_proper==1) {

		                 	for(k=0;k<len;k++) {

		                 puzzle[row_beg][col_beg]=words[i][j];
		                 puz[row_beg][col_beg]=full;
		                 j++;
		                 row_beg--;
		             }
		                i++;
		                j=0;
		                counter1++;

		              	}

		            }

		              }



		              else if(random_dir==5) {

		              	 if(len<=(19-row_beg)+1  &&  len<=(19-col_beg)+1 ) {

		              	 pos_proper=control_empty(row_beg,col_beg,len,random_dir,puz);

		                 if(pos_proper==1) {

		                 	for(k=0;k<len;k++) {

		                 puzzle[row_beg][col_beg]=words[i][j];
		                 puz[row_beg][col_beg]=full;
		                 j++;
		                 row_beg++;
		                 col_beg++;
		             }
		                i++;
		                j=0;
		                counter1++;

		              	}

		            }

		              }


		               else if(random_dir==6) {

		              	 if(len<=(row_beg-0)+1  &&  len<=(col_beg-0)+1 ) {

		              	 	pos_proper=control_empty(row_beg,col_beg,len,random_dir,puz);

		                 if(pos_proper==1) {

		                 	for(k=0;k<len;k++) {

		                 puzzle[row_beg][col_beg]=words[i][j];
		                 puz[row_beg][col_beg]=full;
		                 j++;
		                 row_beg--;
		                 col_beg--;
		             }
		                i++;
		                j=0;
		                counter1++;

		              	}

		            }

		              }


		               else if(random_dir==7) {

		              	 if(len<=(19-row_beg)+1  &&  len<=(col_beg-0)+1 ) {

		              	 	pos_proper=control_empty(row_beg,col_beg,len,random_dir,puz);

		                 if(pos_proper==1) {

		                 	for(k=0;k<len;k++) {

		                 puzzle[row_beg][col_beg]=words[i][j];
		                 puz[row_beg][col_beg]=full;
		                 j++;
		                 row_beg++;
		                 col_beg--;
		             }
		                i++;
		                j=0;
		                counter1++;

		              	}

		            }

		              }


		               else {

		              	 if(len<=(row_beg-0)+1  &&  len<=(19-col_beg)+1 ) {

		              	 pos_proper=control_empty(row_beg,col_beg,len,random_dir,puz);

		                 if(pos_proper==1) {

		                 	for(k=0;k<len;k++) {

		                 puzzle[row_beg][col_beg]=words[i][j];
		                 puz[row_beg][col_beg]=full;
		                 j++;
		                 row_beg--;
		                 col_beg++;
		             }
		                i++;
		                j=0;
		                counter1++;

		              	}

		            }

		              }


		           }


		           for(l=0;l<20;l++) {

		           	for(m=0;m<20;m++) {

		           		if(puz[l][m]==empty) {
		                   random= 97+rand()%26;
		                   puzzle[l][m]=random;
		                   puz[l][m]=full;
		           		}

		           	}

		           }

		        }


        /* This function prints the puzzle on the screen.*/

        void print_puzzle(char puzzle[20][20]) {

          int i,j,k;
          char ch=65;

          printf("\n");
          printf("    ");

          for(i=0;i<=19;i++) {
             
             if(i<10) {
               printf("%d  ",i);
             }

             else {
               printf("%d ",i);
          }

        }

          printf("\n");

        	for(j=0;j<20;j++) {

            printf("%c   ",ch);

        		for(k=0;k<20;k++) {
                            
                            printf("%c  ",puzzle[j][k]);
                                
        		}
        		printf("\n");
            ch++;
        	}
        	printf("\n");
        }


         /* This function controls that coordinate_letter parameter which is character is between 'A' and 'T'. If coordinate_letter parameter is between 'A' and 'T', function 
		returns 1. Otherwise, returns 0. */

        int coordinate_letter_compare(char coordinate_letter) {

            if(coordinate_letter>=65 && coordinate_letter<=84) {
              return 1;
            }

            else {
              return 0;
            }

        }


        /* This function controls that coordinate_number parameter which is integer is between 0 and 19. If coordinate_number parameter is between 0 and 19, function returns 1.
		Otherwise, returns 0. */

        int coordinate_number_compare(int coordinate_number) {

          if(coordinate_number>=0 && coordinate_number<=19) {
            return 1;
          }

          else {
            return 0;
          }

        }


        /*This function controls that words parameter which is two dimensional character array has word parameter which is one dimensional character array. If words parameter has
        word parameter, function returns 1. Otherwise, returns 0. */

        int word_compare(char word[20],char words[10][20]) {

            int i;
            int counter=0;

            for(i=0;i<10;i++) {

                  if(strcmp(word,words[i])==0) {
                    counter++;
                  }

                if(counter==1) {
                  return 1;
                }

            }

                return 0;
        }


        /*This function controls that puzzle parameter which is two dimensional character array has word parameter which is one dimensional character array. coordinate_letter
        and coordinate_number parameter determines the beginning coordinates of the puzzle in order to search in puzzle. If puzzle parameter has word parameter, function returns 1.
        Otherwise, returns 0. */

        int search_in_puzzle(char puzzle[20][20],char word[20],char coordinate_letter,int coordinate_number) {
          int row=coordinate_letter-65;
          int length=length_of_words(word);
          int counter=0;
          int i,j;
          int temp1,temp2;


           if(length<=(19-coordinate_number)+1) {

              temp1=row;
              temp2=coordinate_number;

             for(i=0;i<length;i++) {
                 if(puzzle[temp1][temp2]==word[i]) {
                  counter++;
                 }
                 temp2++;
             }
             if(counter==length) {
              return 1;
             }
             counter=0;
          }



          if(length<=(coordinate_number+1)) {

             temp1=row;
             temp2=coordinate_number;

             for(i=0;i<length;i++) {
                 if(puzzle[temp1][temp2]==word[i]) {
                  counter++;
                 }
                 temp2--;
             }
             if(counter==length) {
              return 2;
             }
             counter=0;
          }


          if(length<=(19-row)+1) {

            temp1=row;
            temp2=coordinate_number;

             for(i=0;i<length;i++) {
                 if(puzzle[temp1][temp2]==word[i]) {
                  counter++;
                 }
                 temp1++;
             }
             if(counter==length) {
              return 3;
             }
              counter=0;
          }


          if(length<=(row+1)) {

            temp1=row;
            temp2=coordinate_number;

             for(i=0;i<length;i++) {
                 if(puzzle[temp1][temp2]==word[i]) {
                  counter++;
                 }
                 temp1--;
             }
             if(counter==length) {
              return 4;
             }
              counter=0;
          }


           if(length<=(19-row)+1 && length<=(19-coordinate_number)+1) {

            temp1=row;
            temp2=coordinate_number;

             for(i=0;i<length;i++) {
                 if(puzzle[temp1][temp2]==word[i]) {
                  counter++;
                 }
                 temp1++;
                 temp2++;
             }
             if(counter==length) {
              return 5;
             }
              counter=0;
          }


           if(length<=(row+1) && length<=(coordinate_number+1)) {

            temp1=row;
            temp2=coordinate_number;

             for(i=0;i<length;i++) {
                 if(puzzle[temp1][temp2]==word[i]) {
                  counter++;
                 }
                 temp1--;
                 temp2--;
             }
             if(counter==length) {
              return 6;
             }
              counter=0;
          }


          if(length<=(19-row)+1 && length<=(coordinate_number+1)) {

            temp1=row;
            temp2=coordinate_number;

             for(i=0;i<length;i++) {
                 if(puzzle[temp1][temp2]==word[i]) {
                  counter++;
                 }
                 temp1++;
                 temp2--;
             }
             if(counter==length) {
              return 7;
             }
              counter=0;
          }


           if(length<=(19-coordinate_number)+1 && length<=(row+1)) {

            temp1=row;
            temp2=coordinate_number;

             for(i=0;i<length;i++) {
                 if(puzzle[temp1][temp2]==word[i]) {
                  counter++;
                 }
                 temp1--;
                 temp2++;
             }
             if(counter==length) {
              return 8;
             }
              counter=0;
          }

              return 0;
  }


	  /* This function finds word parameter which is one dimensional character array in words parameter which is two dimensional character array. If found, in words parameter,
	  the letters of the word parameter are capitalized. After that, in puzzle parameter which is two dimensional character array, the letters of the word parameter are capitalized.
	  coordinate_letter and coordinate_number parameter determines the beginning coordinates of the puzzle in order to capitalize. word_proper_place parameter determines which 
	  direction is capitalized in puzzle array. */

	  void capital_letter(char puzzle[20][20],char word[20],char words[10][20],char coordinate_letter,int coordinate_number,int word_proper_place) {

	       int row=coordinate_letter-65;
	       int length=length_of_words(word);
	       int i,j,k;
	       int counter=0;

	       for(i=0;i<10;i++) {
	           
	           if(strcmp(word,words[i])==0) {
	               counter++;
	          }
	           
	           if(counter==1) {
	              for(j=0;words[i][j]!='\0';j++) {
	                words[i][j]-=32;
	              }
	              break;
	           }

	       }



	       if(word_proper_place==1) {
	          for(k=0;k<length;k++) {
	            puzzle[row][coordinate_number]-=32;
	            coordinate_number++;
	          }
	       }

	       else if(word_proper_place==2) {
	          for(k=0;k<length;k++) {
	            puzzle[row][coordinate_number]-=32;
	            coordinate_number--;
	          }
	       }

	         else if(word_proper_place==3) {
	          for(k=0;k<length;k++) {
	            puzzle[row][coordinate_number]-=32;
	            row++;
	          }
	       }

	         else if(word_proper_place==4) {
	          for(k=0;k<length;k++) {
	            puzzle[row][coordinate_number]-=32;
	            row--;
	          }
	       }

	         else if(word_proper_place==5) {
	          for(k=0;k<length;k++) {
	            puzzle[row][coordinate_number]-=32;
	            row++;
	            coordinate_number++;
	          }
	       }

	         else if(word_proper_place==6) {
	          for(k=0;k<length;k++) {
	            puzzle[row][coordinate_number]-=32;
	            row--;
	            coordinate_number--;
	          }
	       }

	         else if(word_proper_place==7) {
	          for(k=0;k<length;k++) {
	            puzzle[row][coordinate_number]-=32;
	            row++;
	            coordinate_number--;
	          }
	       }

	           else if(word_proper_place==8) {
	          for(k=0;k<length;k++) {
	            puzzle[row][coordinate_number]-=32;
	            row--;
	            coordinate_number++;
	          }
	       }


	  }


        /* This function takes coordinate and the word from user in while loop. If user enters "Exit", program is terminating. Otherwise, program controls that these entries are 
		proper or not. In order to know these entries proper or not, I called coordinate_letter_compare, coordinate_number_compare and word_compare function. If these function 
		returns 1, I controlled that coordinate and the word which was taken from user in puzzle or not. If in puzzle, in order to capitalize word in puzzle parameter which is 
		two dimensional character array and in words parameter which is two dimensional character array, I called capital_letter function. After that, in order to print new puzzle 
		and new words, I called print_puzzle function and print_words function. In this condition, I incremented counter variable 1 which is 0 at the beginning. If user enters wrong 
		coordinate or word, in order to print one previous puzzle and words, I called print_puzzle and print_words function. If counter is equal to 10, function exits from loop and 
		program is terminating. */ 

        void play_game(char puzzle[20][20],char words[10][20]) {

          char coordinate_letter;
          int coordinate_number;
          char word[20];
          int counter=0;
          int coordinate_letter_proper;
          int coordinate_number_proper;
          int word_proper;
          int word_proper_place;
          

          while(counter<10) {

          printf("Please enter the coordinate and the word:\n");
          scanf(" %c",&coordinate_letter);
          scanf("%d ",&coordinate_number);
          scanf("%s",word);

           if(coordinate_letter=='E' && strcmp(word,"xit")==0) {
          	exit(0);
           }

           else if(coordinate_letter=='e' && strcmp(word,"xit")==0) {
          	exit(0);
           }

           else {

          coordinate_letter_proper=coordinate_letter_compare(coordinate_letter);
          coordinate_number_proper=coordinate_number_compare(coordinate_number);
          word_proper=word_compare(word,words);


          if(coordinate_letter_proper==1 && coordinate_number_proper==1 && word_proper==1) {

             word_proper_place=search_in_puzzle(puzzle,word,coordinate_letter,coordinate_number);

             if(word_proper_place!=0) {
              capital_letter(puzzle,word,words,coordinate_letter,coordinate_number,word_proper_place);
               print_puzzle(puzzle);
               print_words(words);
               counter++;
             }
             else {
              print_puzzle(puzzle);
              print_words(words);
             }
          }

          else {
            print_puzzle(puzzle);
            print_words(words);
          }

        }

        }

  }
           
int main() {

        srand(time(NULL));
	    char words[10][20];
        char puzzle[20][20];
        Puzzle puz[20][20]={Empty_Row,Empty_Row,Empty_Row,Empty_Row,Empty_Row,
        	                Empty_Row,Empty_Row,Empty_Row,Empty_Row,Empty_Row,
        	                Empty_Row,Empty_Row,Empty_Row,Empty_Row,Empty_Row,
        	                 Empty_Row,Empty_Row,Empty_Row,Empty_Row,Empty_Row };

        random_words(words);
        fill_puzzle(puzzle,words,puz);
        print_puzzle(puzzle);
        print_words(words);
        play_game(puzzle,words);
       
        
        return 0;

    }
