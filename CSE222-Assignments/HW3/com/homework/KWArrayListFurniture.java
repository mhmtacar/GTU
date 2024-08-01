package com.homework;
import java.util.Arrays;

public class KWArrayListFurniture<E> {
	
	// Data Fields
		/** The default initial capacity */
		private static final int INITIAL_CAPACITY = 5;
		
		/** The underlying data array */
		private E[] theData;
		
		/** The current size */
		private int size = 0;
		
		/** The current capacity */
		private int capacity = 0;
		
		public KWArrayListFurniture() {
			capacity = INITIAL_CAPACITY;
			theData = (E[]) new Object[capacity];
		}
		
		/** Adds anEntry to the end of array list 
		 *@param anEntry the data to be added
		 *@return true
		 */
		public boolean add(E anEntry) {
			if (size == capacity) {
			reallocate();
			}
			theData[size]=anEntry;
			size++;
			return true;
		}
		
		/** Adds anEntry to the given index of array list 
		 * @param index The position where anEntry is added
		 *@param anEntry the data to be added
		 *@throws IndexOutOfBoundsException if index is out of range
		 */
		public void add(int index, E anEntry) {
			if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
			}
			if (size == capacity) {
			reallocate();
			}
			// Shift data in elements from index to size - 1
			for (int i = size; i > index; i--) {
			theData[i] = theData[i - 1];
			}
			// Insert the new item.
			theData[index] = anEntry;
			size++;
		}
		
		/** Get the data at the given index
		@param index The position of data in arraylist
		@return data
		@throws IndexOutOfBoundsException if index is out of range
		*/
		public E get(int index) {
			if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
			}
			return theData[index];
		}
		
		/** Set the data at the given index with newValue
		@param index The position of data in arraylist
		@param newValue The new value of data at the given index
		@return oldValue
		@throws IndexOutOfBoundsException if index is out of range
		*/
		public E set(int index, E newValue) {
			if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		    }
			E oldValue = theData[index];
			theData[index] = newValue;
			return oldValue;
		}
		
		/** Removes data from the given index of arraylist
		@param index The position where data is removed
		@return removed data
		*/
		public E remove(int index) {
			if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
			}
			E returnValue = theData[index];
			for (int i = index + 1; i < size; i++) {
			theData[i - 1] = theData[i];
			}
			size--;
			return returnValue;
		}
		
		/** Frees up memory when the size of the arraylist is equal to its capacity*/
		private void reallocate() {
			capacity = 2 * capacity;
			theData = Arrays.copyOf(theData, capacity);
		}

}
