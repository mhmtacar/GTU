import java.util.ArrayList;
import java.util.Random;
import com.homework.*;

public class Main {
	
	public static void main(String[] args) throws Exception {

		System.out.println("\nPART 1");
		
		Heap heap1= new Heap();
		Heap heap2= new Heap();

        int target=30;
        System.out.println("\nSearch operation on heap1");
		try {
		System.out.println("Trying to search " + target + " in heap1");
		boolean search_res=heap1.search(target);
		if(search_res)
		   System.out.println(target + " is found in heap1");
		else
		   System.out.println(target + " is not found in heap1");
		}
		catch(Exception ex) {
	    	System.out.println(ex.getMessage());
	    	System.out.println();
	    }

		try {
		System.out.println("\nTrying to merge heap1 and heap2");
		heap1.merge(heap2);
		System.out.println("After merge heap1 and heap2, heap1's print");
		}
		catch(Exception ex) {
	    	System.out.println(ex.getMessage());
	    	System.out.println();
	    }

	    heap1.print();
		
		heap1.insert(90);
		heap1.insert(25);
		heap1.insert(50);
		heap1.insert(10);
		heap1.insert(120);
		heap1.insert(70);
		heap1.insert(150);
		heap1.insert(40);

		System.out.println("\nHeap1's elements are inserted");
		
		heap2.insert(45);
		heap2.insert(200);
		heap2.insert(30);
		heap2.insert(60);
		heap2.insert(20);
		heap2.insert(100);
		heap2.insert(35);
		heap2.insert(80);

		System.out.println("Heap2's elements are inserted");
		
		int i,num;
		
		System.out.println("\nHeap1 print");
		heap1.print();
		System.out.println();
		
		System.out.println("Heap 2 print");
		heap2.print();
		System.out.println();
		
		target=50;
        System.out.println("\nSearch operation on heap1");
		try {
		System.out.println("Trying to search " + target + " in heap1");
		boolean search_res=heap1.search(target);
		if(search_res)
		   System.out.println(target + " is found in heap1");
		else
		   System.out.println(target + " is not found in heap1");
		}
		catch(Exception ex) {
	    	System.out.println(ex.getMessage());
	    	System.out.println();
	    }
		
		target=15;
        System.out.println("\nSearch operation on heap2");
		try {
		System.out.println("Trying to search " + target + " in heap2");
		boolean search_res=heap2.search(target);
		if(search_res)
		   System.out.println(target + " is found in heap2");
		else
		   System.out.println(target + " is not found in heap2");
		}
		catch(Exception ex) {
	    	System.out.println(ex.getMessage());
	    	System.out.println();
	    }
		
		System.out.println();
		
	    try {
	    System.out.println("\nTrying to merge heap1 and heap2");
		heap1.merge(heap2);
		System.out.println("After merge heap1 and heap2, heap1's print");
		}
		catch(Exception ex) {
	    	System.out.println(ex.getMessage());
	    	System.out.println();
	    }

    
	    heap1.print();
	    System.out.println();
	    
	    
	    MyIterator iter=new MyIterator(heap2);
	    Object object;
	    int value=0;

	    for(i=0;i<3;i++) {
	    	object=iter.next();
	    	value=(int)object;
	    }
	    
	    int new_value=5;
	    heap2=iter.set(new_value);
	    int cur_index=iter.getCurIndex()-1;
	    heap2.heapUp(cur_index);
	    cur_index=heap2.getCurPos();
	    heap2.heapDown(cur_index);
	    
	    System.out.println("\nHeap2 after setting the value of " + value + " to " + new_value);
	    heap2.print();
	    System.out.println();

        System.out.println("\nRemove operation on heap2\n");
        int largest_i_th_element=10;
	    try {
	    num=heap2.remove(largest_i_th_element);
	    System.out.println("\nRemoved num is " + num);
	    heap2.print();
		System.out.println();
	    }
        catch(Exception ex) {
	    	System.out.println(ex.getMessage());
	    }

	    
	    for(i=8;i>=0;i--) {
	    try {
	    num=heap2.remove(i);
	    System.out.println("\nRemoved num is " + num);
	    heap2.print();
		System.out.println();
	    }
	    catch(Exception ex) {
	    	System.out.println(ex.getMessage());
	    	System.out.println();
	    }
	    }
	    
		
		System.out.println("\n\nPART 2\n");

		int [] random_numbers=new int[10];
		Random rand=new Random();

		for(i=0;i<10;i++) {
			random_numbers[i]=rand.nextInt(10);
		}

		BSTHeapTree bst=new BSTHeapTree();
		int number=3;
		
		try{
			bst.delete(number);
		}
		catch(Exception ex) {
	    	System.out.println(ex.getMessage());
	    	System.out.println();
	    }
	    
	    bst.search(number);
        System.out.println();
		
		for(i=0;i<10;i++) {
			bst.insert(random_numbers[i]);
		}
		
		int num_occur=bst.search(number);
		System.out.println();
		System.out.println("Number of occurrence of " + number + " in BST is " + num_occur);
		bst.delete(number);
	    	
		
    }
	
}
