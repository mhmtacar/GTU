package com.homework;
import java.util.HashMap;
import java.util.Random;

/**
 * MyHashMap class
 * 
 * @author mehmet_acar
 */

public class MyHashMap<K, V>{
	
	private HashMap< K, V > map;
	private K[] map_key;
	
	
	public MyHashMap() {
		map=new HashMap<K, V>();
	}
	
	public void put(K key,V val) {
		map.put(key, val);
	}
	
	/** This method converts map's keys to array map_key.
    */
	public void convert() {
		map_key=(K[]) map.keySet().toArray();
	}
	
	public Iterator<K> getIterator() {
		return new MapIterator();
	}
	
	public Iterator<K> getIterator(K key) {
		return new MapIterator(key);
	}
	
	
	private class MapIterator<K> implements Iterator<K>{
		
		private int index;
		
		/** The iterator starts from any key in the Map when the starting key is not 
            specified
        */
		public MapIterator() {
			System.out.println("\nStarting key is not specified");
			Random rand=new Random();
		    index=rand.nextInt(map_key.length);
		}
		
		
		/** The iterator should start from the given key and still iterate though all 
            the keys in the Map. The iterator starts from any key in the Map when the starting key is not 
            in the Map
	    */
		public MapIterator(K key) {
			
			int count=0;
			
			for(int i=0;i<map_key.length;i++) {
				if(map_key[i]==key) {
				   System.out.println("\nKey " + key + " is in the Map");
				   index=i;
				   count++;
				}
			}
			
			if(count==0) {
				System.out.println("\nKey " + key + " is not in the Map");
				Random rand=new Random();
			    index=rand.nextInt(map_key.length);
			}
			
		}
		
		
		/** The method returns True if there are still not-iterated key/s in the Map, otherwise 
             returns False.
		  * @return true or false
		 */
		@Override
		public boolean hasNext() {
			
			if(index<map_key.length) {
				return true;
			}
			
			else {
				return false;
			}
			
		}

		
		/** The function returns the next key in the Map. It returns the first key when there is no 
             not-iterated key in the Map.
	    * @return next key
	    */
		@Override
		public K next() {
			
			if(hasNext()) {
			   return (K) map_key[index++];
		    }
			
			else {
			   index=0;
			   return (K) map_key[index++];
			}
			
	   }
		
		
		/** The iterator points to the previous key in the Map. It returns the last key when the 
            iterator is at the first key.
        * @return prev key
        */
		@Override
		public K prev() {
			
			index--;
			
			if(index>=0) {
			   return (K) map_key[index];
		    }
			
			else {
			   index=map_key.length-1;
			   return (K) map_key[index];
			}
			
	   }

		
		/** The function returns iterator's pointing key
        * @return iterator's pointing key
        */
		@Override
		public K getKey() {
			return (K) map_key[index];
		}
	
   }
	
}

