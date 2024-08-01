package com.homework;

/**
 * HashtableCoalesced class
 * 
 * @author mehmet_acar
 */

/** Hash table implementation using open addressing. */
public class HashtableCoalesced<K, V> implements KWHashMap<K, V> {

	/** Contains key-value pairs for a hash table. */
	private static class Entry<K, V> {
	 /** The key */
	 private K key;
	 /** The value */
	 private V value;
	 private int next;
	 /** Creates a new key-value pair.
	 @param key The key
	 @param value The value
	 */
	 public Entry(K key, V value) {
	 this.key = key;
	 this.value = value;
	 this.next=-1;
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
	 
	 public void setNext(int next_index) {
		 next=next_index;
	 }
	 public int getNext() {
		 return next;
	 }
	}
	
 // Insert inner class Entry<K, V> here.
 // Data Fields
 private Entry<K, V>[] table;
 private static final int START_CAPACITY = 10;
 private double LOAD_THRESHOLD = 0.75;
 private int numKeys;
 private int numDeletes;
 private final Entry<K, V> DELETED = 
 new Entry<>(null, null);
 
 
 // Constructor 
 public HashtableCoalesced() {
 table = new Entry[START_CAPACITY]; 
 }
 
 
 /** Finds either the target key or the first empty slot in the
 search chain using linear probing.
 @pre The table is not full.
 @param key The key of the target object
 @return The position of the target or the first empty slot if
 the target is not in the table.
*/
 private int find(Object key) {
	 // Calculate the starting index.
	 int index = key.hashCode() % table.length;
	 int temp_index=index;
	 int next_index;
	 int collision_num=0;
	 if (index < 0)
	 index += table.length; // Make it positive.
	 // Increment index until an empty slot is reached
	 // or the key is found.
	 while ((table[index] != null)
	 && (!key.equals(table[index].getKey()))) {
	 collision_num++;
	 next_index=(int) (temp_index+Math.pow(collision_num, collision_num));
	 if(table[next_index]==null || ((table[next_index].getKey().hashCode())%table.length)==index) {
	 table[index].setNext(next_index);
	 index=next_index;
	 }
	 // Check for wraparound.
	 if (index >= table.length)
	 index = 0; // Wrap around.
	 }
	 
	 return index;
	}
 
 
 /** Method get for class HashtableOpen.
 @param key The key being sought
 @return the value associated with this key if found;
 otherwise, null
*/
 @Override
 public V get(Object key) {
  // Find the first table element that is empty
  // or the table element that contains the key.
  int index = find(key);
//If the search is successful, return the value.
  if (table[index] != null)
	return table[index].getValue();
  else
	return null; // key not found.
  }
 
 
 /** Method put for class HashtableOpen.
 @param key The key of item being inserted
 @param value The value for this key
 @return Old value associated with this key if found;
 otherwise, null
*/
 @Override
 public V put(K key, V value) {
  // Find the first table element that is empty
  // or the table element that contains the key.
  int index = find(key);
  // If an empty element was found, insert new entry.
  if (table[index] == null) {
  table[index] = new Entry<>(key, value);
  numKeys++;
  // Check whether rehash is needed.
  double loadFactor = 
  (double) (numKeys + numDeletes) / table.length;
  if (loadFactor > LOAD_THRESHOLD)
  rehash();
  return null;
  }
  // assert: table element that contains the key was found.
  // Replace value for this key.
  V oldVal = table[index].getValue();
  table[index].setValue(value);
  return oldVal;
 }
 
 
 /** Expands table size when loadFactor exceeds LOAD_THRESHOLD
 @post The size of the table is doubled and is an odd integer.
 Each nondeleted entry from the original table is
 reinserted into the expanded table.
 The value of numKeys is reset to the number of items
 actually inserted; numDeletes is reset to 0.
*/
 private void rehash() {
	 // Save a reference to oldTable.
	 Entry<K, V>[] oldTable = table;
	 // Double capacity of this table.
	 table = new Entry[2 * oldTable.length + 1];
	 // Reinsert all items in oldTable into expanded table.
	 numKeys = 0;
	 numDeletes = 0;
	 for (int i = 0; i < oldTable.length; i++) {
	 if ((oldTable[i] != null) && (oldTable[i] != DELETED)) {
	 // Insert entry in expanded table
	 put(oldTable[i].getKey(), oldTable[i].getValue());
	 }
	 }
	}
 
 /*
 public void arrangeNext() {
	 int i;
	 int next_index=0;
	 int temp_index=0;
	 int collision_num=0;
	 for(i=0;i<table.length;i++) {
		 if(table[i]!=null) {
			 temp_index=i;
			 next_index=(int) (temp_index+Math.pow(collision_num, collision_num));
			 while(next_index<table.length && table[next_index]!=null && (table[temp_index].getKey().hashCode())%table.length==(table[next_index].getKey().hashCode())%table.length) {
			
			 }
		 }
	 }
 }
 */


/** Method remove for removing proper key from table.
  * @param key The key of item being removed
  * @return if removing key is found in table, return value of this key. Otherwise, return null.
  */
@Override
public V remove(Object key) {
	
    int index = key.hashCode() % table.length;

    if (table[index] == null){
        return null;
    }
    Entry<K,V> entry = table[index];
    if (entry.getKey().equals(key)){
        V value = entry.getValue();
        table[index]=DELETED;
        numKeys--;
        numDeletes++;
        //arrangeNext();
        return value;
    }
    return null;
}


/** Method print for printing key and next of each hash value.
 */
@Override
public void print() {
	int i,j;
	
	for(i=0;i<table.length;i++) {
		if(table[i]!=null) {
			if(table[i].getNext()==-1) {
				System.out.println("Hash Value: " + i + "   Key: " + table[i].getKey() + "   Next: NULL");
			}
			else {
				System.out.println("Hash Value: " + i + "   Key: " + table[i].getKey() + "   Next: " + table[i].getNext());
			}
		}
		else {
			System.out.println("Hash Value: " + i + "   Key: " + "   Next: NULL");
		}
	}
	
	System.out.println("\n");
	
}
 
}
 
 
 

