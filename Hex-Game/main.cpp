#include<iostream>
#include<fstream>
#include<random>
#include<string>
#include "helper.h"

using namespace std;

int main() {
	
    Hex game1,game2;
    int game1_marked,game2_marked;
    int all_marked;

    game1_marked=game1.one_game_markedcells();
    game2_marked=game2.one_game_markedcells();
    
    all_marked=game1.all_games_markedcells(game1_marked);
    all_marked=game2.all_games_markedcells(game2_marked);


	cout<<"These two games have "<<all_marked<<" marked cells"<<endl;

	if(game1==game2)
		cout<<"These two games have the same number of marked cells"<<endl<<endl;
	   
    else{

     if(game1_marked<game2_marked)
        cout<<"Second game has more marked cells than first game"<<endl<<endl;
	 else
	    cout<<"First game has more marked cells than second game"<<endl<<endl;
    
     }
    
	return 0;
    
}


