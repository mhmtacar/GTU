package com.homework;
import com.homework.*;

public class KWSingleLinkedListBranch {
	
	/** Reference to list head. */
	private Node head = null;
	/** The number of items in the list */
	private int size = 0;
	
	private static class Node {
		// Data Fields
		/** The reference to the data. */
		private String branch_name;
		private String [] furniture_name;
		private int [] model_num;
		private int [] color_num;
		private int [][] furniture1_product_num;
		private int [][] furniture2_product_num;
		private int [][] furniture3_product_num;
		private int [][] furniture4_product_num;
		private int [][] furniture5_product_num;
		/** The reference to the next node. */
		private Node next;
		// Constructors
		
		/** Creates a new node that references another node.
		@param furniture The furniture stored
		@param nodeRef The node referenced by new node
		*/
		private Node(HybridList furniture,Node nodeRef) {
			furniture_name=new String[5];
			model_num=new int[5];
			color_num=new int[5];
			furniture_name[0]=furniture.get(0);
			model_num[0]=furniture.get2(0);
			color_num[0]=furniture.get3(0);
			furniture_name[1]=furniture.get(1);
			model_num[1]=furniture.get2(1);
			color_num[1]=furniture.get3(1);
			furniture_name[2]=furniture.get(2);
			model_num[2]=furniture.get2(2);
			color_num[2]=furniture.get3(2);
			furniture_name[3]=furniture.get(3);
			model_num[3]=furniture.get2(3);
			color_num[3]=furniture.get3(3);
			furniture_name[4]=furniture.get(4);
			model_num[4]=furniture.get2(4);
			color_num[4]=furniture.get3(4);
			furniture1_product_num=new int[model_num[0]][color_num[0]];
			furniture2_product_num=new int[model_num[1]][color_num[1]];
			furniture3_product_num=new int[model_num[2]][color_num[2]];
			furniture4_product_num=new int[model_num[3]][color_num[3]];
			furniture5_product_num=new int[model_num[4]][color_num[4]];
			
			int i,j;
			
			for(i=0;i<model_num[0];i++) {
				for(j=0;j<color_num[0];j++) {
				    furniture1_product_num[i][j]=100;
				}
			}
			
			for(i=0;i<model_num[1];i++) {
				for(j=0;j<color_num[1];j++) {
				    furniture2_product_num[i][j]=100;
				}
			}
			
			for(i=0;i<model_num[2];i++) {
				for(j=0;j<color_num[2];j++) {
				    furniture3_product_num[i][j]=100;
				}
			}
			
			for(i=0;i<model_num[3];i++) {
				for(j=0;j<color_num[3];j++) {
				    furniture4_product_num[i][j]=100;
				}
			}
			
			for(i=0;i<model_num[4];i++) {
				for(j=0;j<color_num[4];j++) {
				    furniture5_product_num[i][j]=100;
				}
			}
		    next = nodeRef;
		}
   }
	
	/** Add a furniture to the front of the list.
	@param furniture The furniture to be added
	*/
	public void addFirst(HybridList furniture) {
	 head = new Node(furniture,head);
	 int branch_num=size+1;
	 head.branch_name="Branch "+branch_num;
	 size++;
	}
	
	/** Add a node after a given node
	@param node The node preceding the new furniture
	@param furniture The furniture to insert
	*/
	private void addAfter(Node node,HybridList furniture) {
	node.next = new Node(furniture, node.next);
	int branch_num=size+1;
	node.next.branch_name="Branch "+branch_num;
	size++;
	}
	
	/** Remove the node after a given node
	@param node The node before the one to be removed
	@return The branch name from the removed node, or null
	if there is no node to remove
	*/
	
	private String removeAfter(Node node) {
		Node temp = node.next;
		if (temp != null) {
		node.next = temp.next;
		size--;
		return temp.branch_name;
		} 
		else {
		return null;
		}
	}
	
	/** Remove the first node from the list
	@return The removed node's branch name or null if the list is empty
	*/
	
	private String removeFirst() {
		Node temp = head;
		if (head != null) {
		head = head.next;
		}
		// Return data at old head or null if list is empty
		if (temp != null) {
		size--;
		return temp.branch_name;
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
	@param furniture_index
	@return The furniture name at the given indexes
	@throws IndexOutOfBoundsException if index is out of range
	*/
	
	public String get(int index,int furniture_index) {
		if (index < 0 || index >= size) {
		throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(furniture_index<0 || furniture_index>4) {
			throw new IndexOutOfBoundsException(Integer.toString(furniture_index));
		}
		Node node = getNode(index);
		return node.furniture_name[furniture_index];
	}
	
	/** Get the model num
	@param index The position of the node
	@param furniture_index
	@return The furniture model num at the given indexes
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public int get2(int index,int furniture_index) {
		if (index < 0 || index >= size) {
		throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(furniture_index<0 || furniture_index>4) {
			throw new IndexOutOfBoundsException(Integer.toString(furniture_index));
		}
		Node node = getNode(index);
		return node.model_num[furniture_index];
	}
	
	/** Get the color num
	@param index The position of the node
	@param furniture_index
	@return The color num at the given indexes
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public int get3(int index,int furniture_index) {
		if (index < 0 || index >= size) {
		throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(furniture_index<0 || furniture_index>4) {
			throw new IndexOutOfBoundsException(Integer.toString(furniture_index));
		}
		Node node = getNode(index);
		return node.color_num[furniture_index];
	}
	
	/** Returns branch size
	 * @return size
	 */
	public int getSize() {
		return size;
	}
	
	/** Returns product num at the given indexes
	 * @param index
	 * @param furniture_index
	 * @param model_index
	 * @param color_index
	 * @return product num
	 */
	public int getProductNum(int index,int furniture_index,int model_index,int color_index) {
		
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
			}
		Node node = getNode(index);
		
		if(furniture_index<0 || furniture_index>4) {
			throw new IndexOutOfBoundsException(Integer.toString(furniture_index));
		}
		if(model_index<0 || model_index>=node.model_num[furniture_index]) {
			throw new IndexOutOfBoundsException(Integer.toString(model_index));
		}
		if(color_index<0 || color_index>=node.color_num[furniture_index]) {
			throw new IndexOutOfBoundsException(Integer.toString(color_index));
		}
		
		if(furniture_index==0){
			return node.furniture1_product_num[model_index][color_index];
		}
		else if(furniture_index==1) {
			return node.furniture2_product_num[model_index][color_index];
		}
		else if(furniture_index==2) {
			return node.furniture3_product_num[model_index][color_index];
		}
		else if(furniture_index==3) {
			return node.furniture4_product_num[model_index][color_index];
		}
		else {
			return node.furniture5_product_num[model_index][color_index];
		}
		
	}
	
	/** Store a reference to furniture_name in the element at position index.
	@param index The position of the node
	@param furniture_index
	@param furniture_name The new furniture name
	@return The furniture name previously at index
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public String set(int index, int furniture_index,String furniture_name) {
		if (index < 0 || index >= size) {
		throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(furniture_index<0 || furniture_index>4) {
		throw new IndexOutOfBoundsException(Integer.toString(furniture_index));
		}
		Node node = getNode(index);
		String result = node.furniture_name[furniture_index];
		node.furniture_name[furniture_index] = furniture_name;
		return result;
	}
	
	/** Store a reference to model_num in the element at position index.
	@param index The position of the node
	@param furniture_index
	@param model_num The new model_num
	@return The model num previously at index
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public int set2(int index, int furniture_index,int model_num) {
		if (index < 0 || index >= size) {
		throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(furniture_index<0 || furniture_index>4) {
		throw new IndexOutOfBoundsException(Integer.toString(furniture_index));
		}
		Node node = getNode(index);
		int result = node.model_num[furniture_index];
		node.model_num[furniture_index] = model_num;
		return result;
	}
	
	/** Store a reference to color num in the element at position index.
	@param index The position of the node
	@param furniture_index
	@param color_num The new color_num
	@return The color num previously at index
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public int set3(int index, int furniture_index,int color_num) {
		if (index < 0 || index >= size) {
		throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(furniture_index<0 || furniture_index>4) {
		throw new IndexOutOfBoundsException(Integer.toString(furniture_index));
		}
		Node node = getNode(index);
		int result = node.color_num[furniture_index];
		node.color_num[furniture_index] = color_num;
		return result;
	}
	
	/** Insert the specified branch at index
	@param index The position where furniture is to be inserted
	@param furniture The furniture to be inserted
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public void add(int index, HybridList furniture) {
		if (index < 0 || index > size) {
		throw new IndexOutOfBoundsException(Integer.toString(index));
	   }
		
	    if (index == 0) {
		    addFirst(furniture);
		} 
	    else {
			Node node = getNode(index-1);
			addAfter(node,furniture);
			
	    }
	    
	}
	
	/** Append new branch to the end of the list
	@param furniture The furniture to be appended
	@return true (as specified by the Collection interface)
	*/
	public boolean add(HybridList furniture) {
		add(size, furniture);
		return true;
	}
	
	/** Removes branch from the given index of list
	@param index The position where branch is removed
	@return removed branch
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
	
	/** Removes branch at the beginning of the list
	@return removed branch
	*/
	public String remove() {
		String res=remove(0);
		return res;
	}
	
	/** Add product to proper place according to parameters
	@param index
	@param furniture_index
	@param model_index
	@param color_index
	@param product_val
	*/
	public void add_product(int index,int furniture_index,int model_index,int color_index,int product_val) {
		Node node = getNode(index);
		if(furniture_index==0) {
			node.furniture1_product_num[model_index][color_index]+=product_val;
		}
		else if(furniture_index==1) {
			node.furniture2_product_num[model_index][color_index]+=product_val;
		}
		else if(furniture_index==2) {
			node.furniture3_product_num[model_index][color_index]+=product_val;
		}
		else if(furniture_index==3) {
			node.furniture4_product_num[model_index][color_index]+=product_val;
		}
		else if(furniture_index==4) {
			node.furniture5_product_num[model_index][color_index]+=product_val;
		}
	}
	
	/** Removes product from proper place according to parameters
	@param index
	@param furniture_index
	@param model_index
	@param color_index
	@param product_val
	*/
	public void remove_product(int index,int furniture_index,int model_index,int color_index,int product_val) {
		Node node = getNode(index);
		if(furniture_index==0) {
			node.furniture1_product_num[model_index][color_index]-=product_val;
		}
		else if(furniture_index==1) {
			node.furniture2_product_num[model_index][color_index]-=product_val;
		}
		else if(furniture_index==2) {
			node.furniture3_product_num[model_index][color_index]-=product_val;
		}
		else if(furniture_index==3) {
			node.furniture4_product_num[model_index][color_index]-=product_val;
		}
		else if(furniture_index==4) {
			node.furniture5_product_num[model_index][color_index]-=product_val;
		}
	}
	

	
}
