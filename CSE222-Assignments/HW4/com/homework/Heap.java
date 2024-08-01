package com.homework;
import com.homework.*; 
import java.util.ArrayList;
import java.util.Collections;

/**
 * Heap class
 * 
 * @author mehmet_acar
 */

public class Heap {

	private ArrayList<Integer> heap;
	private int cur_pos;

	/**
	 * Constructor for Heap.
	 */
	public Heap() {
		heap = new ArrayList<Integer>();
	}

	/**
	 * Getter for parent index.
	 * @return parent index
	 */
	private int parent(int child_index) {
		return (child_index - 1) / 2;
	}

	/**
	 * Getter for left child index.
	 * @return left child index
	 */
	private int leftChild(int parent_index) {
		return (parent_index * 2) + 1;
	}

	/**
	 * Getter for rightchild index.
	 * @return rightchild index
	 */
	private int rightChild(int parent_index) {
		return (parent_index * 2) + 2;
	}

	/**
	 * Getter for heap size.
	 * @return heap size
	 */
	public int getSize() {
		return heap.size();
	}

	/**
	 * Getter for heap element according to index parameter.
	 * @param index Index of heap
	 * @return heap element
	 */
	public int getNum(int index) {
		return heap.get(index);
	}

	/**
	 * Setter for heap element according to index parameter.
	 * @param index Index of heap
	 * @param val new heap element value
	 */
	public void setNum(int index, int val) {
		heap.set(index, val);
	}

	/**
	 * Getter for current heap position
	 * @return current heap position
	 */
	public int getCurPos() {
		return cur_pos;
	}

	/**
	 * Prints max heap
	 */
	public void print() {

		if (heap.size() == 1) {
			System.out.print("PARENT : " + heap.get(0));
		}

		else {
			for (int i = 0; i < heap.size() / 2; i++) {

				System.out.print(" PARENT : " + heap.get(i));

				if (leftChild(i) < heap.size()) {
					System.out.print(" LEFT CHILD : " + heap.get(leftChild(i)));
				}
				if (rightChild(i) < heap.size()) {
					System.out.print(" RIGHT CHILD : " + heap.get(rightChild(i)));
				}
				System.out.println();

			}
		}

	}

	/**
	 * Controls from current position to parent direction
	 * @param cur_index current index
	 */
	public void heapUp(int cur_index) {

		while (cur_index != 0 && heap.get(cur_index) > heap.get(parent(cur_index))) {
			Collections.swap(heap, cur_index, parent(cur_index));
			cur_index = (cur_index - 1) / 2;
		}
		cur_pos = cur_index;
	}

	/**
	 * Controls from current position to child direction
	 * @param cur_index current index
	 */
	public void heapDown(int cur_index) {

		boolean control = true;
		int biggestChildIndex = 0;

		while (control == true) {

			if (leftChild(cur_index) >= heap.size() && rightChild(cur_index) >= heap.size()) {
				control = false;
			}

			else {
				if (leftChild(cur_index) < heap.size()) {
					biggestChildIndex = leftChild(cur_index);
				}

				if (rightChild(cur_index) < heap.size()
						&& heap.get(rightChild(cur_index)) > heap.get(leftChild(cur_index))) {
					biggestChildIndex = rightChild(cur_index);
				}

				if (heap.get(cur_index) < heap.get(biggestChildIndex)) {
					Collections.swap(heap, cur_index, biggestChildIndex);
					cur_index = biggestChildIndex;
				}

				else {
					control = false;
				}

			}

		}
		cur_pos = cur_index;
	}

	/**
	 * Inserts new element to heap.
	 * @param val inserted value
	 */
	public void insert(int val) {

		heap.add(val);
		int cur_index = heap.size() - 1;
		heapUp(cur_index);

	}

	/**
	 * Searches target value in heap or throws exception.
	 * @param target searched value
	 * @return true or false
	 * @throws Exception throw exception
	 */
	public boolean search(int target) throws Exception {

		if (heap.isEmpty())
			throw new Exception("Heap is empty.You can not search any element in the heap.");
		else
			return heap.contains(target);

	}

	/**
	 * Merges two heaps or throws exception.
	 * @param heap2 added heap to another heap
	 * @throws Exception throw exception
	 */
	public void merge(Heap heap2) throws Exception {

		if (getSize() == 0 && heap2.getSize() == 0)
			throw new Exception("Heap1 and heap2 are empty.You can not apply merge operation.");

		for (int i = 0; i < heap2.getSize(); i++) {
			insert(heap2.getNum(i));
		}

	}

	/**
	 * Finds largest i_th element in heap according to parameter.
	 * @param i shows largest i_th element
	 * @return largest i_th element
	 */
	private int find_i_th_largest(int i) {

		ArrayList<Integer> dest = new ArrayList<Integer>();

		for (int j = 0; j < heap.size(); j++) {
			dest.add(heap.get(j));
		}

		Collections.sort(dest);
		return dest.get(dest.size() - i);

	}

	/**
	 * Removes element from heap according to parameter or throws exception.
	 * @param largest_index decides which element removed from heap
	 * @return removed num
	 * @throws Exception throw exception
	 */
	public int remove(int largest_index) throws Exception {

		if (heap.isEmpty()) {
			throw new Exception("Heap is empty.You can not remove any element.");
		}

		else if (heap.size() - largest_index < 0) {
			throw new Exception("Largest i_th number can not be " + largest_index);
		}

		else if (heap.size() == 1) {
			int removed_num = heap.get(0);
			heap.remove(0);
			return removed_num;
		}

		else {

			int removed_num = find_i_th_largest(largest_index);
			int removed_index = heap.indexOf(removed_num);
			heap.remove(removed_index);
			heap.add(removed_index, heap.get(heap.size() - 1));
			heap.remove(heap.size() - 1);

			int cur_index = removed_index;
			heapDown(cur_index);
			return removed_num;

		}

	}

	/**
	 * Removes element from beginning of heap or throws exception.
	 * @param index index value
	 * @return removed num
	 * @throws Exception throw exception
	 */
	public int remove2(int index) throws Exception {

		if (heap.isEmpty()) {
			throw new Exception("Heap is empty.You can not remove any element.");
		}

		else if (heap.size() == 1) {
			int removed_num = heap.get(0);
			heap.remove(0);
			return removed_num;
		}

		else {

			int removed_num = heap.get(0);
			heap.remove(0);
			heap.add(0, heap.get(heap.size() - 1));
			heap.remove(heap.size() - 1);

			int cur_index = 0;
			heapDown(cur_index);
			return removed_num;

		}

	}

}
