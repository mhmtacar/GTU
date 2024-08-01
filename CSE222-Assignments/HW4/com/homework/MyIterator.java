package com.homework;
import com.homework.*; 
import java.util.ArrayList;
import java.util.Iterator;

/**
 * MyIterator class
 * 
 * @author mehmet_acar
 */

public class MyIterator implements Iterator {

	private Heap h;
	private int cur_index;

	/**
	 * Constructor for MyIterator.
	 * @param heap copying contents to h
	 */
	public MyIterator(Heap heap) {
		int i;
		h = new Heap();
		for (i = 0; i < heap.getSize(); i++) {
			h.insert(heap.getNum(i));
		}
		cur_index = 0;
	}

	/**
	 * Returns current position of iterator.
	 * @return current index
	 */
	public int getCurIndex() {
		return cur_index;
	}

	/**
	 * Returns true or false according to iterator has next value or not.
	 * @return true or false
	 */
	@Override
	public boolean hasNext() {
		return cur_index < h.getSize() && h.getSize() != 0;
	}

	/**
	 * Returns next item pointing by iterator and advances iterator.
	 * @return next item
	 */
	@Override
	public Object next() {
		Object next_num = h.getNum(cur_index);
		cur_index++;
		return next_num;
	}

	/**
	 * Throws Exception.
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Sets the old heap value which is pointing by iterator with new value.
	 * @param val new value for setting proper heap value pointing by iterator
	 * @return new heap after setting operation
	 */
	public Heap set(int val) {
		h.setNum(cur_index - 1, val);
		return h;
	}

}
