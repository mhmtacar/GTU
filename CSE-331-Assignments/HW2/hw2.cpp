#include<iostream>

using namespace std;

const int MAX_SIZE=100;

int CheckSumPossibility(int num,int arr[],int size){
	
	int x1,x2;
	
	if(num==0){
		return 1;
	}
	
	else if(size<1){
		return 0;
	}
	
	else if(num<0){
		return 0;
	}

    else{

         x1=CheckSumPossibility(num,arr,size-1);  
	     x2=CheckSumPossibility(num-arr[size-1],arr,size-1);
	     return x1||x2;
	
   }
    
}


int main() { 

	int arraySize; 
	int arr[MAX_SIZE]; 
	int num; 
	int returnVal; 
	
	cin >> arraySize; 
	cin >> num; 
	
	for(int i = 0; i < arraySize; ++i) 
	{ 
	    cin >> arr[i]; 
	} 
	
	returnVal = CheckSumPossibility(num, arr, arraySize); 
	
	if(returnVal == 1) 
	{ 
	   cout << "Possible!" << endl; 
	} 
	else 
	{ 
	cout << "Not possible!" << endl; 
	} 
	
	return 0; 

}



