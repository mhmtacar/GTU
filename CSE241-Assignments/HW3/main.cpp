#include<iostream>
#include<fstream>
#include<random>
#include<string>
#include<vector>
#include "helper.h"

using namespace std;

int main() {
	
    Hex game1,game2;
    int game1_marked,game2_marked;
    int all_marked;
    bool marked_compare;

    game1_marked=game1.one_game_markedcells();
    game2_marked=game2.one_game_markedcells();
    
    all_marked=game1.all_games_markedcells(game1_marked);
    all_marked=game2.all_games_markedcells(game2_marked);
    
    marked_compare=game1.compare_marked_cells(game2);

	cout<<"These two games have "<<all_marked<<" marked cells"<<endl;

	if(marked_compare==true)
	   cout<<"First game has more marked cells than second game"<<endl;

    else{

     if(game1_marked<game2_marked)
       cout<<"Second game has more marked cells than first game"<<endl;
	 else
	   cout<<"These two games have the same number of marked cells"<<endl;
     }

	return 0;
    
}


