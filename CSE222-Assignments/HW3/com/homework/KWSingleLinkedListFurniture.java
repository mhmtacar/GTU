package com.homework;

public class KWSingleLinkedListFurniture {
	
	/** Reference to list head. */
	private Node head = null;
	/** The number of items in the list */
	private int size = 0;
	
	private static class Node {
		// Data Fields
		/** The reference to the data. */
		private KWArrayListFurniture<String> furniture_name;
		private KWArrayListFurniture<Integer> model_num;
		private KWArrayListFurniture<Integer> color_num;
		/** The reference to the next node. */
		private Node next;
		// Constructors
		
		/** Creates a new node that references another node.
		@param furniture name The furniture name stored
		@param model_num The model num stored
		@param color_num The color num stored
		@param nodeRef The node referenced by new node
		*/
		private Node(String furniture_name,int model_num,int color_num,Node nodeRef) {
			this.furniture_name=new KWArrayListFurniture<String>();
			this.model_num=new KWArrayListFurniture<Integer>();
			this.color_num=new KWArrayListFurniture<Integer>();
			this.furniture_name.add(0,furniture_name);
			this.model_num.add(0,model_num);
			this.color_num.add(0,color_num);
			next=nodeRef;
		}
}
	
	/** Add an given parameters to the front of the list.
	@param furniture_name The furniture name to be added
	@param model_num The model num to be added
	@param color_num The color num to be added
	*/
	public void addFirst(String furniture_name,int model_num,int color_num) {
	 head = new Node(furniture_name,model_num,color_num,head);
	 size++;
	}
	
	/** Add a node after a given node
	@param node The node preceding the new item
	@param furniture_name The furniture name to insert
	@param model_num The model num to insert
	@param color_num The color num to insert
	*/
	private void addAfter(Node node, String furniture_name,int model_num,int color_num) {
	node.next = new Node(furniture_name,model_num,color_num, node.next);
	size++;
	}
	
	/** Remove the node after a given node
	@param node The node before the one to be removed
	@return The furniture name from the removed node, or null
	if there is no node to remove
	*/
	private String removeAfter(Node node) {
		Node temp = node.next;
		if (temp != null) {
		node.next = temp.next;
		size--;
		return temp.furniture_name.get(0);
		} 
		else {
		return null;
		}
	}
	
	/** Remove the first node from the list
	@return The removed node's furniture name or null if the list is empty
	*/
	private String removeFirst() {
		Node temp = head;
		if (head != null) {
		head = head.next;
		}
		// Return data at old head or null if list is empty
		if (temp != null) {
		size--;
		return temp.furniture_name.get(0);
		} 
		else {
		return null;
		}
	}
	
	/** Find the node at a specified position
	@param index The position of the node sought
	@return The node at index or null if it does not exist
	*/
	private Node getNode(int index) {
	    Node node = head;
		for (int i = 0; i < index && node != null; i++) {
		node = node.next;
		}
	    return node;
	}
	
	/** Get the furniture name
	@param index The position of the node
	@return furniture name
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public String get(int index) {
	if (index < 0 || index >= size) {
	throw new IndexOutOfBoundsException(Integer.toString(index));
	}
	Node node = getNode(index);
	return node.furniture_name.get(0);
	}
	
	/** Get the model num
	@param index The position of the node
	@return model num
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public int get2(int index) {
		if (index < 0 || index >= size) {
		throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node node = getNode(index);
		return node.model_num.get(0);
	}
	
	/** Get the color num
	@param index The position of the node
	@return color num
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public int get3(int index) {
		if (index < 0 || index >= size) {
		throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node node = getNode(index);
		return node.color_num.get(0);
	}
	
	/** Get the furniture num
	@return furniture num
	*/
	public int getSize() {
		return size;
	}
	
	/** Store a reference to furniture_name in the element at position index.
	@param index The position of the furniture name to change
	@param furniture_name The new furniture name
	@return The furniture name previously at index
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public String set(int index, String furniture_name) {
		if (index < 0 || index >= size) {
		throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node node = getNode(index);
		String result = node.furniture_name.get(0);
		node.furniture_name.set(0,furniture_name);
		return result;
	}
	
	/** Store a reference to model_num in the element at position index.
	@param index The position of the model_num to change
	@param model_num The new model num
	@return The model num previously at index
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public int set2(int index, int model_num) {
		if (index < 0 || index >= size) {
		throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node node = getNode(index);
		int result = node.model_num.get(0);
		node.model_num.set(0,model_num);
		return result;
	}
	
	/** Store a reference to color_num in the element at position index.
	@param index The position of the color_num to change
	@param color_num The new color num
	@return The color_num previously at index
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public int set3(int index, int color_num) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
			}
			Node node = getNode(index);
			int result = node.color_num.get(0);
			node.color_num.set(0,color_num);
			return result;
	}
	
	/** Insert the specified parameters at index
	@param index The position where parameters is to be inserted
	@param furniture_name The furniture name to be inserted
	@param model_num The model num to be inserted
	@param color_num The color num to be inserted
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public void add(int index, String furniture_name,int model_num,int color_num) {
		if (index < 0 || index > size) {
		throw new IndexOutOfBoundsException(Integer.toString(index));
	   }
		
	    if (index == 0) {
		    addFirst(furniture_name,model_num,color_num);
		} 
	    else {
			Node node = getNode(index-1);
			addAfter(node,furniture_name,model_num,color_num);
			
	    }
	    
	}
	
	/** Append parameters to the end of the list
	@param furniture_name The furniture name to be appended
	@param model_num The model num to be appended
	@param color_num The color num to be appended
	@return true (as specified by the Collection interface)
	*/
	
	public boolean add(String furniture_name,int model_num,int color_num) {
		add(size, furniture_name,model_num,color_num);
		return true;
	}
	
	/** Removes furniture from the given index of list
	@param index The position where furniture is removed
	@return removed furniture
	*/
	public String remove(int index) {
		String res;
		if(index==0) {
		   res=removeFirst();
		}
		else {
			Node node = getNode(index-1);
			res=removeAfter(node);
		}
		return res;
	}
	
	/** Removes furniture at the beginning of the list
	@return removed furniture
	*/
	public String remove() {
		String res=remove(0);
		return res;
	}
	
	
}
		
		
		
