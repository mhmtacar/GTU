import com.homework.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;



public class Main {
   public static void main(String[] args) {
	   
	   System.out.println("PART 1\n");
	   System.out.println("Skip List");
	   SkipList<Integer> sl1=new SkipList<Integer>();
	   int num=10;
	   sl1.add(num);
	   System.out.println(num + " is inserted to Skip List");
	   num=20;
	   sl1.add(num);
	   System.out.println(num + " is inserted to Skip List");
	   num=30;
	   sl1.add(num);
	   System.out.println(num + " is inserted to Skip List");
	   num=10;
	   if(sl1.remove(num)) {
	   System.out.println(num + " is deleted from Skip List");
	   }
	   else{
	   System.out.println("Skip list does not contain" + num);
	   }
	   
       
	   Iterator<Integer>itr=sl1.descendingIterator();
	   while(itr.hasNext()) {
		   System.out.println("Skip list iterator next(): " + itr.next());
	   }
	   

	   System.out.println("\nAVL Tree");
	   AVLTree<Integer>avl=new AVLTree<Integer>();
	   num=1;
	   avl.add(num);
	   System.out.println(num + " is inserted to AVL Tree");
	   num=2;
	   avl.add(num);
	   System.out.println(num + " is inserted to AVL Tree");
	   num=3;
	   avl.add(num);
	   System.out.println(num + " is inserted to AVL Tree");
	   num=4;
	   avl.add(num);
	   System.out.println(num + " is inserted to AVL Tree");
	   num=5;
	   avl.add(num);
	   System.out.println(num + " is inserted to AVL Tree");
	   num=6;
	   avl.add(num);
	   System.out.println(num + " is inserted to AVL Tree");
	   num=7;
	   avl.add(num);
	   System.out.println(num + " is inserted to AVL Tree");
	   itr=avl.iterator();
	   while(itr.hasNext()) {
	   System.out.println("AVL Tree iterator next(): " + itr.next());
	   }
	   
	   SortedSet<Integer> ts=new TreeSet<Integer>();
	   num=4;
	   ts=avl.headSet(num);
	   System.out.println("In AVL Tree, smaller number from " + num + "(headSet): " + ts);
	   ts=avl.tailSet(num);
	   System.out.println("In AVL Tree, bigger number from " + num + "(tailSet): " + ts);
	   
	   

	   System.out.println("\n\nPART 2\n");
	   BinarySearchTree<Integer>bst1=new BinarySearchTree<Integer>();
	   System.out.println("New Binary Search Tree is created");
	   num=12;
	   bst1.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
	   num=8;
	   bst1.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
	   num=18;
	   bst1.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
	   num=5;
	   bst1.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
	   num=11;
	   bst1.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
	   num=17;
	   bst1.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
	   num=4;
	   bst1.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
	   
	   if(bst1.check_AVL()==true)
		  System.out.println("The Binary Search Tree is AVL Tree\n");
	   else
		  System.out.println("The Binary Search Tree is not AVL Tree\n");
	   

	   BinarySearchTree<Integer>bst2=new BinarySearchTree<Integer>();
	   System.out.println("New Binary Search Tree is created");
	   num=12;
	   bst2.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
	   num=8;
	   bst2.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
	   num=18;
	   bst2.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
	   num=5;
	   bst2.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
	   num=11;
	   bst2.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
	   num=17;
	   bst2.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
	   num=4;
	   bst2.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
	   num=7;
	   bst2.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
       num=2;
	   bst2.add(num);
	   System.out.println(num + " is inserted to Binary Search Tree");
	   
	   if(bst2.check_AVL()==true)
		  System.out.println("The Binary Search Tree is AVL Tree");
	   else
		  System.out.println("The Binary Search Tree is not AVL Tree");
	   
	   

	   System.out.println("\n\nPART 3\n");
	   BinarySearchTree<Integer> bst3;
	   RedBlackTree<Integer> rbt;
	   BTree<Integer> bt;
	   SkipList2<Integer> sl2;
	   Random randNum = new Random();
	   Set<Integer>set;
	   Set<Integer>set2;
	   Iterator<Integer> itr2;
	   
	   int rand_size=10000;
	   long startTime,endTime,timeElapsed,totaltimeElapsed = 0;
	   int value;
	   
	   while(rand_size<=80000) {

		   
	      for(int i=0;i<10;i++) {
	    	  
	    	  set = new LinkedHashSet<Integer>();
	    	  set2 = new LinkedHashSet<Integer>();
	    	  
	    	  while (set.size() < rand_size) {
	 	         set.add(randNum.nextInt(rand_size)+1);
	 	      }
	 	      
	 	      while (set2.size() < 100) {
	 		         set2.add(randNum.nextInt(100)+1);
	 		  }
	    	  
	      bst3=new BinarySearchTree<Integer>();
	      itr2 = set.iterator();
	      

	      while(itr2.hasNext()) {
	        value = itr2.next();
	        bst3.add(value);
	       }
	      
	      itr2=set2.iterator();
	      startTime = System.nanoTime();
	      
	      while(itr2.hasNext()) {
		    value = itr2.next();
		    bst3.add(value);
		  }
	      
	      endTime = System.nanoTime();
	      timeElapsed = endTime - startTime;
	      totaltimeElapsed+=timeElapsed;
	      
	      }
	      
	      System.out.println("Average execution time in nanoseconds for Binary Search Tree for 100 random numbers insertion after " + rand_size + " insertion: " + totaltimeElapsed/10);
	      totaltimeElapsed=0;
	      
	      
	      for(int i=0;i<10;i++) {
	    	  
	    	  set = new LinkedHashSet<Integer>();
	    	  set2 = new LinkedHashSet<Integer>();
	    	  
	    	  while (set.size() < rand_size) {
		 	         set.add(randNum.nextInt(rand_size)+1);
		 	      }
		 	      
		 	      while (set2.size() < 100) {
		 		         set2.add(randNum.nextInt(100)+1);
		 		  }
	    	  
	      rbt=new RedBlackTree<Integer>();
	      itr2 = set.iterator();
	  
	      while(itr2.hasNext()) {
	        value = itr2.next();
	        rbt.add(value);
	       }
	      
	      itr2=set2.iterator();
	      startTime = System.nanoTime();
	      
	      while(itr2.hasNext()) {
		    value = itr2.next();
		    rbt.add(value);
		  }
	      
	      endTime = System.nanoTime();
	      timeElapsed = endTime - startTime;
	      totaltimeElapsed+=timeElapsed;
	      
	      }
	      
	      System.out.println("Average execution time in nanoseconds for Red Black Tree Tree for 100 random numbers insertion after " + rand_size + " insertion: " + totaltimeElapsed/10);
	      totaltimeElapsed=0;
	      
	      
	      for(int i=0;i<10;i++) {
	    	  
	    	  set = new LinkedHashSet<Integer>();
	    	  set2 = new LinkedHashSet<Integer>();
	    	  
	    	  while (set.size() < rand_size) {
		 	         set.add(randNum.nextInt(rand_size)+1);
		 	      }
		 	      
		 	      while (set2.size() < 100) {
		 		         set2.add(randNum.nextInt(100)+1);
		 		  }
	    	  
	      bt=new BTree<Integer>(rand_size);
	      itr2 = set.iterator();
	      
	      while(itr2.hasNext()) {
	        value = itr2.next();
	        bt.add(value);
	       }
	      
	      itr2=set2.iterator();
	      startTime = System.nanoTime();
	      
	      while(itr2.hasNext()) {
		    value = itr2.next();
		    bt.add(value);
		  }
	      
	      endTime = System.nanoTime();
	      timeElapsed = endTime - startTime;
	      totaltimeElapsed+=timeElapsed;
	      
	      }
	      
	      System.out.println("Average execution time in nanoseconds for B Tree for 100 random numbers insertion after " + rand_size + " insertion: " + totaltimeElapsed/10);
	      totaltimeElapsed=0;
	      
	      
	      for(int i=0;i<10;i++) {
	    	  
	    	  set = new LinkedHashSet<Integer>();
	    	  set2 = new LinkedHashSet<Integer>();
	    	  
	    	  while (set.size() < rand_size) {
		 	         set.add(randNum.nextInt(rand_size)+1);
		 	      }
		 	      
		 	      while (set2.size() < 100) {
		 		         set2.add(randNum.nextInt(100)+1);
		 		  }
	    	  
	      sl2=new SkipList2<Integer>();
	      itr2 = set.iterator();
	      
	      while(itr2.hasNext()) {
	        value = (int) itr2.next();
	        sl2.add(value);
	       }
	      
	      itr2=set2.iterator();
	      startTime = System.nanoTime();
	      
	      while(itr2.hasNext()) {
		    value = itr2.next();
		    sl2.add(value);
		  }
	      
	      endTime = System.nanoTime();
	      timeElapsed = endTime - startTime;
	      totaltimeElapsed+=timeElapsed;
	      
	      }
	      
	      System.out.println("Average execution time in nanoseconds for Skip List for 100 random numbers insertion after " + rand_size + " insertion: " + totaltimeElapsed/10);
	      totaltimeElapsed=0;
	      
	      rand_size*=2; 
	       

   }

   
   }
   

}
