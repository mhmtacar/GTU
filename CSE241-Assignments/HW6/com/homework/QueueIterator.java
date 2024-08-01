package com.homework;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class QueueIterator<E> implements Iterator<E> {
  E[] e;
  static int cursor = 0;
  static int lastRet = -1;
  static int size = 0;

  QueueIterator(E[] e) {
    this.e = e;
  }

  /*This function returns true if the iteration has more elements.*/
  @Override
  public boolean hasNext() {
    size = 0;
    for (int i = 0; i < e.length; i++) {
      if (e[i] != null) {
        size++;
      }
    }
    return cursor != size;
  }

  @Override
  public void reset() {
    cursor = 0;
    lastRet = -1;
    size = 0;
  }

  /*This function returns the next element in the iteration and advances the iterator.*/
  @Override
  public E next() {
    int i = cursor;
    if (i >= size) {
      throw new NoSuchElementException();
    }
    Object[] elementData = e;
    if (i >= elementData.length) {
      throw new ConcurrentModificationException();
    }
    cursor = i + 1;
    return (E) elementData[lastRet = i];
  }

  /*This function removes from the underlying collection the last element returned by this iterator. This method does not work for Queues, it throws an exception.*/
  @Override
  public void remove() {
    throw new UnsupportedOperationException("remove");
  }
}
