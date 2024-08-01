package com.homework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class AVLTree<E extends Comparable<E>> 
 extends BinarySearchTreeWithRotate<E> implements NavigableSet<E>{
 // Insert nested class AVLNode<E> here.
	/** Class to represent an AVL Node. It extends the
	 BinaryTree.Node by adding the balance field. 
	*/ 
	private static class AVLNode<E> extends Node<E> {
	 /** Constant to indicate left-heavy */
	 public static final int LEFT_HEAVY = -1;
	 /** Constant to indicate balanced */
	 public static final int BALANCED = 0;
	 /** Constant to indicate right-heavy */
	 public static final int RIGHT_HEAVY = 1;
	 /** balance is right subtree height - left subtree height */
	 private int balance;
	 
	//Methods
	/** Construct a node with the given item as the data field.
	@param item The data field
	*/
	public AVLNode(E item) {
	super(item);
	balance = BALANCED;
	}
	/** Return a string representation of this object.
	The balance value is appended to the contents.
	@return String representation of this object
	*/
	@Override
	public String toString() {
	return balance + ": " + super.toString();
	}
	}
	
 // Data Fields
 /** Flag to indicate that height of tree has increased. */
 private boolean increase;
 private SortedSet<E> ts;
 private ArrayList<E> arr_list=new ArrayList<E>();
 

private AVLNode<E> add(AVLNode<E> localRoot, E item) {
	if (localRoot == null) {
		 addReturn = true;
		 increase = true;
		 return new AVLNode<E>(item);
	}
	if (item.compareTo(localRoot.data) == 0) {
		 // Item is already in the tree.
		 increase = false;
		 addReturn = false;
		 return localRoot;
	}
	else if (item.compareTo(localRoot.data) < 0) {
		 // item < data
		 localRoot.left = add((AVLNode<E>) localRoot.left, item);
		 if (increase) {
			 decrementBalance(localRoot);
			 if (localRoot.balance < AVLNode.LEFT_HEAVY) {
			 increase = false;
			 return rebalanceLeft(localRoot);
			 }
		 }
		 return localRoot;
	}
	else {
		localRoot.right = add((AVLNode<E>) localRoot.right, item);
		if(increase){
			incrementBalance(localRoot);
			if(localRoot.balance > AVLNode.RIGHT_HEAVY){
				increase = false;
				return rebalanceRight(localRoot);
			}
		}
		return localRoot; // Re-balance not needed
	}
	
	}


/** Method to rebalance left.
@pre localRoot is the root of an AVL subtree that is critically left-heavy.
@post Balance is restored.
@param localRoot Root of the AVL subtree that needs rebalancing
@return a new localRoot
*/
private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
// Obtain reference to left child.
AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
// See whether left-right heavy.
if (leftChild.balance > AVLNode.BALANCED) {
// Obtain reference to left-right child.
AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
/** Adjust the balances to be their new values after
the rotations are performed.
*/
if (leftRightChild.balance < AVLNode.BALANCED) {
leftChild.balance = AVLNode.BALANCED;
leftRightChild.balance = AVLNode.BALANCED;
localRoot.balance = AVLNode.RIGHT_HEAVY;
} else { 
leftChild.balance = AVLNode.LEFT_HEAVY;
leftRightChild.balance = AVLNode.BALANCED;
localRoot.balance = AVLNode.BALANCED;
}
// Perform left rotation.
localRoot.left = rotateLeft(leftChild);
} else { 
/** In this case the leftChild (the new root) and the root
(new right child) will both be balanced after the rotation.
*/
leftChild.balance = AVLNode.BALANCED;
localRoot.balance = AVLNode.BALANCED;
}
// Now rotate the local root right.
return (AVLNode<E>) rotateRight(localRoot);
}

private AVLNode<E> rebalanceRight(AVLNode<E> localRoot){
	//Obtain reference to right child
	AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;
	//see whether right-left heavy
	if(rightChild.balance < AVLNode.BALANCED){
		//Obtain reference to right-left child.
		AVLNode<E> RightLeftChild = (AVLNode<E>) rightChild.left;
		/*
		 * Adjust the balances to be their new values after the rotations are performed
		 */
		if(RightLeftChild.balance < AVLNode.BALANCED){
			rightChild.balance = AVLNode.RIGHT_HEAVY;
			RightLeftChild.balance = AVLNode.BALANCED;
			localRoot.balance = AVLNode.BALANCED;
		} else if (RightLeftChild.balance > AVLNode.BALANCED){
			rightChild.balance = AVLNode.BALANCED;
			RightLeftChild.balance = AVLNode.BALANCED;
			localRoot.balance = AVLNode.LEFT_HEAVY;
		} else {
			rightChild.balance = AVLNode.BALANCED;
			localRoot.balance = AVLNode.BALANCED;
		}
		//Perform right rotation
		localRoot.right = rotateRight(rightChild);
	} else {	
		rightChild.balance = AVLNode.BALANCED;
		localRoot.balance = AVLNode.BALANCED;
	}
	//Now rotate the local root left
	return (AVLNode<E>) rotateLeft(localRoot);
}

private void incrementBalance(AVLNode<E> node){
	// Increment the balance.
	node.balance++;
	if(node.balance == AVLNode.BALANCED){
		//If now balanced, overall height has not increased
		increase = false;
	}
}

private void decrementBalance(AVLNode<E> node) {
	 // Decrement the balance.
	 node.balance--;
	 if (node.balance == AVLNode.BALANCED) {
	 /** If now balanced, overall height has not increased. */
	 increase = false;
	 }
	}

private void traverse(Node<E> node,E toElement){

	if(node==null) {
		return;
	}
	
	if(node.data.compareTo(toElement)<0) {
		ts.add(node.data);
	}
	
	traverse(node.left,toElement);
	traverse(node.right,toElement);
}

private void traverse2(Node<E> node,E fromElement){

	if(node==null) {
		return;
	}
	
	if(node.data.compareTo(fromElement)>0) {
		ts.add(node.data);
	}
	
	traverse2(node.left,fromElement);
	traverse2(node.right,fromElement);
}

@Override
public boolean add(E item) {
	increase = false;
	root = add((AVLNode<E>) root, item);
	arr_list.add(item);
	return addReturn;
}

@Override
public Iterator<E> iterator() {
	return arr_list.iterator();
}

@Override
public SortedSet<E> headSet(E toElement) {
	ts=new TreeSet<E>();
	traverse(root,toElement);
	return ts;
}

@Override
public SortedSet<E> tailSet(E fromElement) {
	ts=new TreeSet<E>();
	traverse2(root,fromElement);
	return ts;
}

@Override
public Comparator<? super E> comparator() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public E first() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public E last() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean addAll(Collection<? extends E> c) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public void clear() {
	// TODO Auto-generated method stub
	
}

@Override
public boolean contains(Object o) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean containsAll(Collection<?> c) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean isEmpty() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean remove(Object o) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean removeAll(Collection<?> c) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean retainAll(Collection<?> c) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public int size() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public Object[] toArray() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public <T> T[] toArray(T[] a) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public E ceiling(E e) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Iterator<E> descendingIterator() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public NavigableSet<E> descendingSet() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public E floor(E e) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public NavigableSet<E> headSet(E toElement, boolean inclusive) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public E higher(E e) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public E lower(E e) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public E pollFirst() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public E pollLast() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public SortedSet<E> subSet(E fromElement, E toElement) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
	// TODO Auto-generated method stub
	return null;
}

}
