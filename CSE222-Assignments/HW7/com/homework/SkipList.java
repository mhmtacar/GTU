package com.homework;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NavigableSet;
import java.util.Random;
import java.util.SortedSet;


public class SkipList<E extends Comparable<E>> implements NavigableSet<E> {

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
	 private LinkedList<E>linked_list;
	 
	 public SkipList(){
		maxLevel=1;
		head=new SLNode<E>(maxLevel,null);
		maxCap = computeMaxCap(maxLevel);;
		size=0;
		linked_list=new LinkedList<E>();
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
	 
	 @Override
		public boolean add(E item) {
		 
			SLNode<E>[] pred = search(item);
			
			if (pred[0].links[0] != null && pred[0].links[0].data.compareTo(item) == 0) {
	            return false;
	        }
			
			size++;
			
			if(size > maxCap){
				maxLevel++;
				maxCap = computeMaxCap(maxLevel);
				head.links = Arrays.copyOf(head.links, maxLevel);
				pred = Arrays.copyOf(pred, maxLevel);
				pred[maxLevel - 1] = head;
			}

			SLNode<E> newNode = new SLNode<E>(logRandom(), item);

			for(int i = 0; i < newNode.links.length; i++){
				newNode.links[i] = pred[i].links[i];
				pred[i].links[i] = newNode;
			}
			
			linked_list.addFirst(item);
			return true;

		}
	 
	 @Override
		public boolean remove(Object target) {
		 
		 SLNode<E>[] pred = search((E) target);
			if(pred[0].links != null &&
					pred[0].links[0].data.compareTo((E) target) != 0){
				return false; //item is not in the list
			} else { 
				size--; //don't re-adjust maxCap and level, as we may have nodes at these levels
				SLNode<E> deleteNode = pred[0];
				for(int i = 0; i < deleteNode.links.length; i++){
					if(pred[i].links[i] != null)
						pred[i].links[i] = pred[i].links[i].links[i];
				}
				linked_list.remove(target);
				return true;
			}
			
		}
	 
	 @Override
		public Iterator<E> descendingIterator() {
			return linked_list.iterator();
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
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
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
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E ceiling(E arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<E> descendingSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E floor(E arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<E> headSet(E arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<E> headSet(E arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E higher(E arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E lower(E arg0) {
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
	public SortedSet<E> subSet(E arg0, E arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<E> subSet(E arg0, boolean arg1, E arg2, boolean arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<E> tailSet(E arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<E> tailSet(E arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
