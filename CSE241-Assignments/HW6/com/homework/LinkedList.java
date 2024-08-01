package com.homework;

public class LinkedList<E> implements List<E>, Queue<E> {

  private static final int DEFAULT_CAPACITY = 50;
  E[] elements = (E[]) new Object[DEFAULT_CAPACITY];

  /*This function retrieves, but does not remove, the head of this.*/
  @Override
  public E element() {
    return elements[0];
  }

  /*This function inserts the specified element.*/
  @Override
  public void offer(E e) {
    add(e);
  }

  /*This function retrieves and removes the head of this, or throws if this is empty.*/
  @Override
  public E poll() {
    if(isEmpty()){
      throw new IllegalArgumentException("Queue is empty.");
    }
    E pollElement = elements[0];
    remove(pollElement);
    return pollElement;
  }

  /*This function returns an iterator over the collection.*/
  @Override
  public QueueIterator<E> iterator() {
    return new QueueIterator<>(elements);
  }

  /*This function inserts the specified element into this.*/
  @Override
  public void add(E e) {
    E[] newarr = (E[]) new Object[elements.length + 1];
    int i;
    for (i = 0; i < newarr.length; i++) {
      if (elements[i] != null) {
        newarr[i] = elements[i];
      } else {
        break;
      }
    }

    newarr[i] = e;

    elements = newarr;
  }

  /*This function adds all of the elements in the specified collection to this collection.*/
  @Override
  public void addAll(Collection c) {
    QueueIterator queueIterator = (QueueIterator) c.iterator();
    queueIterator.reset();
    while (queueIterator.hasNext()) {
      E e = (E) queueIterator.next();
      add(e);
    }
  }

  /*This function removes all of the elements from this collection.*/
  @Override
  public void clear() {
    elements = (E[]) new Object[DEFAULT_CAPACITY];
  }

  /*This function returns true if this collection contains the specified element.*/
  @Override
  public boolean contains(E e) {
    for (int i = 0; i < elements.length; i++) {
      if (elements[i] != null && elements[i].equals(e)) {
        return true;
      }
    }
    return false;
  }

  /*This function returns true if this collection contains all of the elements in the specified collection.*/
  @Override
  public boolean containsAll(Collection c) {
    boolean isContain = false;
    QueueIterator queueIterator = (QueueIterator) c.iterator();
    queueIterator.reset();
    while (queueIterator.hasNext()) {
      E e = (E) queueIterator.next();
      isContain = false;
      for (int i = 0; i < elements.length; i++) {
        if (elements[i] != null && elements[i].equals(e)) {
          isContain = true;
          break;
        }
      }
      if (!isContain) {
        return isContain;
      }

    }

    return isContain;
  }

  /*This function returns true if this collection contains no elements.*/
  @Override
  public boolean isEmpty() {
    if (elements[0] == null) {
      return true;
    }
    return false;
  }

  /*This function removes a single instance of the specified element from this collection, if it is present.*/
  @Override
  public void remove(E e) {
    E[] newarr = (E[]) new Object[elements.length];
    for (int i = 0, j = 0; i < elements.length; i++) {
      if (elements[i] != null && !elements[i].equals(e)) {
        newarr[j] = elements[i];
        j++;
      }
    }
    elements = newarr;
  }

  /*This function removes all of this collection's elements that are also contained in the specified collection.*/
  @Override
  public void removeAll(Collection c) {
    QueueIterator queueIterator = (QueueIterator) c.iterator();
    queueIterator.reset();
    while (queueIterator.hasNext()) {
      E e = (E) queueIterator.next();
      remove(e);
    }
  }

  /*This function retains only the elements in this collection that are contained in the specified collection.*/
  @Override
  public void retainAll(Collection c) {
    for (int i = 0, j = 0; i < elements.length; i++) {
      if (!c.contains(elements[i])) {
        remove(elements[i]);
      }
    }
  }

  /*This function returns the number of elements in this collection.*/
  @Override
  public int size() {
    int size = 0;
    for (int i = 0; i < elements.length; i++) {
      if (elements[i] != null) {
        size++;
      } else {
        break;
      }
    }
    return size;
  }
}
