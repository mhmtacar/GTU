package com.homework;
import com.homework.*; 

/**
 * BSTHeapTree class
 * 
 * @author mehmet_acar
 */

public class BSTHeapTree {

	public static class Node {
		Heap h;
		Node left;
		Node right;
		int size;
		int[] numbers;
		int[] num_occur;

		/**
		 * Constructor for Node.
		 */
		Node() {
			h = new Heap();
			right = null;
			left = null;
			size = 0;

			numbers = new int[7];
			num_occur = new int[7];
			for (int i = 0; i < 7; i++) {
				num_occur[i] = 1;
			}
		}

		/**
		 * Adds new element to heap.
		 * @param val inserted value
		 * @return number of occurence of val after add operation in BST
		 */
		public int add(int val) {

			int i, j = 0, count = 0;

			for (i = 0; i < h.getSize(); i++) {
				if (val == h.getNum(i)) {
					for (j = 0; j < h.getSize(); j++) {
						if (val == numbers[j]) {
							num_occur[j]++;
							count++;
							break;
						}
					}

				}
			}

			if (count == 0) {
				h.insert(val);
				numbers[size] = val;
				size++;
				return 1;
			}

			return num_occur[j];
		}

		/**
		 * Finds the given value in heap
		 * @param val finding value
		 * @return number of occurence of val in BST
		 */
		public int find(int val) {
			int i, j;
			int counter = 0;

			for (i = 0; i < h.getSize(); i++) {
				counter++;
				if (val == h.getNum(i)) {
					for (j = 0; j < h.getSize(); j++) {
						if (val == numbers[j])
							return num_occur[j];
					}
				}
			}
			if (counter == 0) {
				return -1;
			} else {
				return 0;
			}
		}

		/**
		 * Removes given value from heap.
		 * @param val removed value
		 * @return number of occurence of val after remove operation in BST
		 * @throws Exception throw exception
		 */
		public int remove(int val) throws Exception {

			int i, j = 0, count1 = 0, count2 = 0;

			for (i = 0; i < h.getSize(); i++) {
				if (val == h.getNum(i)) {
					count1++;
					for (j = 0; j < h.getSize(); j++) {
						if (val == numbers[j] && num_occur[j] != 0) {
							num_occur[j]--;
							count2++;
							break;
						}
					}

				}
			}

			if (count1 == 0)
				return -1;
			else if (count2 == 0)
				return -2;

			else {
				if (num_occur[j] == 0) {
					h.remove2(i);
					size--;
				}
			}

			return num_occur[j];
		}
	}

	Node root;
	Node temp;
	static int counter = 0;

	/**
	 * Constructor for BSTHeapTree.
	 */
	public BSTHeapTree() {
		root = null;
	}

	/**
	 * Sends val parameter to insertHelper method.
	 * @param val sending value
	 */
	public void insert(int val) {
		root = insertHelper(temp, val);
		if (counter == 0) {
			temp = root;
		}
		counter++;
	}

	/**
	 * Moves on BST by comparing inserted value and root of heap and adds new value
	 * to heap.
	 * @param root root of current position
	 * @param val inserted value
	 * @return added heap root
	 */
	private Node insertHelper(Node root, int val) {

		if (root == null) {
			root = new Node();
			root.add(val);
			return root;
		}

		else if (root.size == 7) {

			if (val < root.h.getNum(0))
				root.left = insertHelper(root.left, val);
			else if (val >= root.h.getNum(0))
				root.right = insertHelper(root.right, val);

		}

		else {
			int num_occur = root.add(val);
			System.out.println("Number " + val + "'s occur in BST after add operation is " + num_occur);
		}

		return root;
	}

	/**
	 * Sends given value to deleteHelper method.
	 * @param val sending value
	 * @throws Exception throw exception
	 */
	public void delete(int val) throws Exception {
		root = deleteHelper(temp, val);
		if (root == null)
			System.out.println("You can not apply remove operation.Because number " + val
					+ " can not be find in BST or " + "number " + val + "'s occur in BST before remove operation is 0");
	}

	/**
	 * Deletes given value from proper position of BST.
	 * @param root root of current position
	 * @param val removed value
	 * @return removed heap root
	 * @throws Exception throw exception
	 */
	private Node deleteHelper(Node root, int val) throws Exception {

		if (root == null) {
			return root;
		}

		int num_occur = root.remove(val);

		if (num_occur == -1) {

			if (val < root.h.getNum(0))
				root.left = insertHelper(root.left, val);
			else if (val >= root.h.getNum(0))
				root.right = insertHelper(root.right, val);

		}

		else if (num_occur == -2) {
			return null;
		}

		else {
			System.out.println("Number " + val + "'s occur in BST after remove operation is " + num_occur);
		}

		return root;
	}

	/**
	 * Sends given value to searchHelper method.
	 * @param val searched value
	 * @return number of occurrence of finding value
	 */
	public int search(int val) {
		int num_occur = searchHelper(temp, val);
		if (num_occur == -1 || num_occur == 0) {
			System.out.println("\nNumber " + val + " can not be find in BST");
		}
		return num_occur;
	}

	/**
	 * Searches given value on BST.
	 * @param root of current position
	 * @param val searched value
	 * @return number of occurrence of finding value
	 */
	public int searchHelper(Node root, int val) {

		if (root == null) {
			return 0;
		}

		int num_occur = root.find(val);

		if (num_occur == 0) {
			if (val < root.h.getNum(0))
				searchHelper(root.left, val);
			else if (val > root.h.getNum(0))
				searchHelper(root.right, val);
		}

		else if (num_occur == -1) {
			return -1;
		}

		else {
			return num_occur;
		}

		return 0;
	}

}
