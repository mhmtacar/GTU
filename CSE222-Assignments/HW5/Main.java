import com.homework.HashtableChainLinkedList;
import com.homework.HashtableChainTreeSet;
import com.homework.HashtableCoalesced;
import com.homework.Iterator;
import com.homework.MyHashMap;

public class Main {
	
   public static void main(String[] args) {

   	   System.out.println("\nPART 1");
	   
	   MyHashMap<Integer,String> map=new MyHashMap<Integer,String>();
	   map.put(1, "Value1");
	   map.put(2, "Value2");
	   map.put(3, "Value3");
	   map.put(4, "Value4");
	   map.put(5, "Value5");
	   map.convert();
	   
	   int search_key=4,key;
	   Iterator<Integer> iter=map.getIterator(search_key);
	   key=iter.getKey();
	   System.out.println("\nIterator points to key " + key + " at the beginning");
	   System.out.println("Iterator hasNext: " + iter.hasNext());
	   System.out.println("Iterator next: " + iter.next());
	   System.out.println("Iterator next: " + iter.next());
	   System.out.println("Iterator hasNext: " + iter.hasNext());
	   System.out.println("Iterator next: " + iter.next());
	   System.out.println("Iterator prev: " + iter.prev());
	   System.out.println("Iterator prev: " + iter.prev());
	   System.out.println("Iterator prev: " + iter.prev());
	   System.out.println();
	   
	   
	   search_key=10;
	   iter=map.getIterator(search_key);
	   key=iter.getKey();
	   System.out.println("\nIterator points to key " + key + " at the beginning");
	   System.out.println("Iterator hasNext: " + iter.hasNext());
	   System.out.println("Iterator next: " + iter.next());
	   System.out.println("Iterator next: " + iter.next());
	   System.out.println("Iterator next: " + iter.next());
	   System.out.println("Iterator prev: " + iter.prev());
	   System.out.println("Iterator prev: " + iter.prev());
	   System.out.println("Iterator prev: " + iter.prev());
	   System.out.println();
	   
	   
	   iter=map.getIterator();
	   key=iter.getKey();
	   System.out.println("\nIterator points to key " + key + " at the beginning");
	   System.out.println("Iterator hasNext: " + iter.hasNext());
	   System.out.println("Iterator next: " + iter.next());
	   System.out.println("Iterator next: " + iter.next());
	   System.out.println("Iterator next: " + iter.next());
	   System.out.println("Iterator prev: " + iter.prev());
	   System.out.println("Iterator prev: " + iter.prev());
	   System.out.println("Iterator prev: " + iter.prev());
	   
	   
	   System.out.println("\n\nPART 2");
	   System.out.println("\nLinkedList HashTable Chain\n");
	   HashtableChainLinkedList<Integer,String> hash1=new HashtableChainLinkedList<Integer,String>();
	   long startTime = System.nanoTime();
	   hash1.put(3, "Test1");
	   hash1.put(12, "Test2");
	   hash1.put(13, "Test3");
	   hash1.put(25, "Test4");
	   hash1.put(23, "Test5");
	   hash1.put(51, "Test6");
	   hash1.put(42, "Test7");
	   hash1.remove(13);
	   System.out.println("\nLinkedList Hash table is below");
	   hash1.print();
	   System.out.println("\nGet key: 25 - " + hash1.get(25));
	   hash1.remove(42);
	   System.out.println("Get key: 42 - " + hash1.get(42));
	   System.out.println("Get key: 100 - " + hash1.get(100));
	   long endTime = System.nanoTime();
       System.out.println("\nTotal time during this operations (ns): ");
       System.out.println(endTime - startTime);
	   System.out.println("\n");
	   

	   
	   HashtableChainTreeSet<Integer,String> hash2=new HashtableChainTreeSet<Integer,String>();
	   System.out.println("\nTreeSet HashTable Chain\n");
	   long startTime2 = System.nanoTime();
	   hash2.put(3, "Test1");
	   hash2.put(12, "Test2");
	   hash2.put(13, "Test3");
	   hash2.put(25, "Test4");
	   hash2.put(23, "Test5");
	   hash2.put(51, "Test6");
	   hash2.put(42, "Test7");
	   hash2.remove(13);
	   System.out.println("\nTreeSet Hash table is below");
	   hash2.print();
	   System.out.println("\nGet key: 3 - " + hash2.get(3));
	   hash2.remove(23);
	   System.out.println("Get key: 23 - " + hash2.get(23));
	   System.out.println("Get key: 150 - " + hash2.get(150));
	   long endTime2 = System.nanoTime();
       System.out.println("\nTotal time during this operations (ns): ");
       System.out.println(endTime2 - startTime2);
	   System.out.println("\n\n");
	   
	   
	   
	   System.out.println();
	   HashtableCoalesced<Integer,String> hash3=new HashtableCoalesced<Integer,String>();
	   System.out.println("HashTable Coalesced\n");
	   long startTime3 = System.nanoTime();
	   hash3.put(3, "Test1");
	   hash3.print();
	   hash3.put(12, "Test2");
	   hash3.print();
	   hash3.put(13, "Test3");
	   hash3.print();
	   hash3.put(25, "Test4");
	   hash3.print();
	   hash3.put(23, "Test5");
	   hash3.print();
	   hash3.put(51, "Test6");
	   hash3.print();
	   hash3.put(42, "Test7");
	   hash3.print();
	   long endTime3 = System.nanoTime();
       System.out.println("Total time during this operations (ns): ");
       System.out.println(endTime3 - startTime3);
	   
	   
   }
   
}