#include<iostream>
#include<fstream>
#include<random>
#include<string>
#include<vector>
#include<deque>
#include "abstracthex.h"
#include "hexvector.h"
#include "hexarray1d.h"
#include "hexadapter.h"

using namespace std;

using namespace Mehmet;

int main() {

    int input_truth=0;
    int choice;

    while(input_truth==0){
    	
        cout<<"1-If you want to create two HexVector object and play two game, please enter 1"<<endl;
        cout<<"2-If you want to create two HexArray1D object and play two game, please enter 2"<<endl;
        cout<<"3-If you want to create two HexAdapter vector object and play two game, please enter 3"<<endl;
        cout<<"4-If you want to create two HexAdapter deque object and play two game, please enter 4"<<endl;
        cout<<"Please enter your choice:";
        cin>>choice;

        if(cin.fail()){
           cerr<<"Wrong choice"<<endl;
           cin.clear();
           cin.ignore(1000,'\n');
        }

        else{
          if(choice>=1 && choice<=4)
             input_truth=1;
        }
    }

    if(choice==1){
        HexVector game1,game2;

        if(game1==game2)
        cout<<"These two games are same"<<endl;
        else
        cout<<"These two games are not same"<<endl;
    }

    else if(choice==2){
        HexArray1D game1,game2;

        if(game1==game2)
        cout<<"These two games are same"<<endl;
        else
        cout<<"These two games are not same"<<endl;
    }

    else if(choice==3){
        HexAdapter<vector<AbstractHex::Cell>, vector<vector<AbstractHex::Cell>> > game1;
        HexAdapter<vector<AbstractHex::Cell>, vector<vector<AbstractHex::Cell>> > game2;

        if(game1==game2)
        cout<<"These two games are same"<<endl;
        else
        cout<<"These two games are not same"<<endl;
    }

    else if(choice==4){
        HexAdapter<deque<AbstractHex::Cell>, deque<deque<AbstractHex::Cell>> > game1;
        HexAdapter<deque<AbstractHex::Cell>, deque<deque<AbstractHex::Cell>> > game2;

        if(game1==game2)
        cout<<"These two games are same"<<endl;
        else
        cout<<"These two games are not same"<<endl;
    }
	

	return 0;
	
    
}


