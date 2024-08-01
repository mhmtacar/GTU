package com.homework;
import java.util.LinkedList;

/**
 * HashtableChainLinkedList class
 * 
 * @author mehmet_acar
 */

/** Hash table implementation using chaining. */
public class HashtableChainLinkedList<K, V> implements KWHashMap<K, V> {
	
	/** Contains key value pairs for a hash table. */
	private static class Entry<K, V>{
	 /** The key */
	 private K key;
	 /** The value */
	 private V value;
	 /** Creates a new key value pair.
	 @param key The key
	 @param value The value
	 */
	 public Entry(K key, V value) {
	 this.key = key;
	 this.value = value;
	 }
	 /** Retrieves the key.
	 @return The key
	 */
	 public K getKey() {
	 return key;
	 }
	 /** Retrieves the value.
	 @return The value
	 */
	 public V getValue() {
	 return value;
	 }
	 /** Sets the value.
	 @param val The new value
	 @return The old value
	 */
	 public V setValue(V val) {
	 V oldVal = value;
	 value = val;
	 return oldVal;
	 }
	 
	
	}
	
 // Insert inner class Entry<K, V> here.
 /** The table */
 private LinkedList<Entry<K, V>>[] table;
 /** The number of keys */
 private int numKeys;
 /** The capacity */
 private static final int CAPACITY = 10;
 /** The maximum load factor */
 private static final double LOAD_THRESHOLD = 3.0;
 
 
 // Constructor
 public HashtableChainLinkedList() {
 table = new LinkedList[CAPACITY];
 }
 
 
 /** Method get for class HashtableChain.
 @param key The key being sought
 @return The value associated with this key if found;
 otherwise, null
 */
 @Override
 public V get(Object key) {
  int index = key.hashCode() % table.length;
  if (index < 0)
  index += table.length;
  if (table[index] == null)
  return null; // key is not in the table.
  // Search the list at table[index] to find the key.
  for (Entry<K, V> nextItem : table[index]) {
  if (nextItem.getKey().equals(key))
  return nextItem.getValue();
  }
  // assert: key is not in the table.
  return null;
 }
 
 
 /** Method rehash for allocating a new hash table that is double the size and reinsert each table entry in the new hash table.
  */
 private void rehash()
    {
        LinkedList<Entry<K, V>>[] old_table = table ;
        table = new LinkedList[old_table.length * 2 + 1] ;
        numKeys=0;

        for (LinkedList<Entry<K, V>> entryList : old_table)
        {
            if (entryList != null)
            {
                for (Entry<K, V> entry : entryList)
                {
                    put(entry.getKey(), entry.getValue()) ;
                    numKeys++;
                }
            }
        }
    }
 
 
 /** Method put for class HashtableChain.
 @param key The key of item being inserted
 @param value The value for this key
 @return The old value associated with this key if
 found; otherwise, null
*/
 @Override
 public V put(K key, V value) {
  int index = key.hashCode() % table.length;
  if (index < 0)
  index += table.length;
  if (table[index] == null) {
  // Create a new linked list at table[index].
  table[index] = new LinkedList<>();
  }
  
  // Search the list at table[index] to find the key.
  for (Entry<K, V> nextItem : table[index]) {
  // If the search is successful, replace the old value.
  if (nextItem.getKey().equals(key)) {
  // Replace value for this key.
  V oldVal = nextItem.getValue();
  nextItem.setValue(value);
  System.out.println("Key " + key + "'s Value is changed from " + oldVal + " to " + value);
  return oldVal; 
  }
  }
  // assert: key is not in the table, add new item.
  table[index].addFirst(new Entry<>(key, value));
  System.out.println("Key " + key + " Value " + value + " is inserted");
  numKeys++;
  if (numKeys > (LOAD_THRESHOLD * table.length))
  rehash();
  return null;
 }
 
 
 /** Method remove for removing proper key from table.
  * @param key The key of item being removed
  * @return if removing key is found in table, return value of this key. Otherwise, return null.
  */
 @Override
    public V remove(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            return null; 
        }
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                V value = entry.getValue();
                table[index].remove(entry);
                System.out.println("Key " + entry.getKey() + " Value " + entry.getValue() + " is removed");
                numKeys--;
                if (table[index].isEmpty()) {
                    table[index] = null;
                }
                return value;
            }
        }
        return null;
    }
 
 
 /** Method print for printing keys and values of each indexes.
 */
 @Override
 public void print(){
        int i = 0;
        System.out.println("\nIndex - Key - Value");
        for(LinkedList<Entry<K,V>> list : table){
            if(list != null){
                System.out.print(i + "-  ");
                for(Entry<K, V> entry : list){
                    System.out.print(entry.getKey() + "- ");
                    System.out.print(entry.getValue() + "   ");
                }
                System.out.println();
            }
            i++;
        }
    }

 
}
	
	
	
	

