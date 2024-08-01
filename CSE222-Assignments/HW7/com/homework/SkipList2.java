package com.homework;

import java.util.Arrays;
import java.util.Random;

public class SkipList2<E extends Comparable<E>> {
	
	/** Static class to contain the data and the links */
	 static class SLNode<E> {
	 SLNode<E>[] links; 
	 E data;
	 /** Create a node of level m */
	 SLNode (int m, E data) {
	 links = (SLNode<E>[]) new SLNode[m]; // create links
	 this.data = data; // store item
	 }
	 }
	 
	 static final double LOG2 = Math.log(2.0);
	 private int maxLevel;
	 private SLNode<E> head;
	 private int maxCap;
	 private int size;
	 final static Random rand=new Random();
	 
	 public SkipList2(){
		maxLevel=1;
		head=new SLNode(maxLevel,null);
		maxCap = computeMaxCap(maxLevel);;
		size=0;
	 }
	 
	 private int computeMaxCap(int maxLevel) {
		 return (int)(Math.pow(2,maxLevel)-1);
	  }
	 
	 private int logRandom() {
		 int r = rand.nextInt(maxCap);
		 int k = (int) (Math.log(r + 1) / LOG2);
		 if (k > maxLevel - 1) {
		 k = maxLevel - 1;
		 }
		 return maxLevel - k;
		}
	 
	 @SuppressWarnings("unchecked")
	 /** Search for an item in the list
	  @param item The item being sought
	  @return A SLNode array which references the predecessors
	  of the target at each level.
	 */
	 private SLNode<E>[] search (E target) {
	  SLNode<E>[] pred = (SLNode<E>[]) new SLNode[maxLevel];
	  SLNode<E> current = head;
	  for (int i = current.links.length-1; i >= 0; i--) {
	  while (current.links[i] != null
	  && current.links[i].data.compareTo(target) < 0) {
	  current = current.links[i];
	  }
	  pred[i] = current;
	  }
	  return pred;
	 }
	 /** Find an object in the skip-list
	  @param target The item being sought
	  @return A reference to the object in the skip-list that matches
	  the target. If not found, null is returned.
	 */
	 public E find(E target) {
	  SLNode<E>[] pred = search(target);
	  if (pred[0].links[0] != null && 
	  pred[0].links[0].data.compareTo(target) == 0) {
	  return pred[0].links[0].data;
	  } else {
	  return null;
	  }
	 }
	 
	 public boolean add(E item) {

	        SLNode<E>[] pred = search(item);
	        
	        if (pred[0].links[0] != null && pred[0].links[0].data.compareTo(item) == 0) {
	            return false;
	        }
	        
	        size++;
	       
	        if (size > maxCap) {
	            maxLevel++;
	            maxCap = computeMaxCap(maxLevel);
	            head.links = Arrays.copyOf(head.links, maxLevel);
	            pred = Arrays.copyOf(pred, maxLevel);
	            pred[maxLevel - 1] = head;
	        }
	        
	        SLNode<E> itemNode = new SLNode<E>(logRandom(), item);
	        
	        for (int i = 0; i < itemNode.links.length; i++) {
	            itemNode.links[i] = pred[i].links[i];
	            pred[i].links[i] = itemNode;
	        }

	        return true;

	    }
	 
}




