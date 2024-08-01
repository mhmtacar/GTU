package com.homework;
public class ArrayList<E> implements List<E> {

  private static final int DEFAULT_CAPACITY = 50;
  E[] elements = (E[]) new Object[DEFAULT_CAPACITY];

  /*This function returns an iterator over the collection.*/
  @Override
  public ListSetIterator<E> iterator() {
    return new ListSetIterator<>(elements);
  }

  /*This function ensures that this collection contains the specified element.*/
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
    ListSetIterator listSetIterator = (ListSetIterator) c.iterator();
    listSetIterator.reset();
    while (listSetIterator.hasNext()) {
      E e = (E) listSetIterator.next();
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
    ListSetIterator listSetIterator = (ListSetIterator) c.iterator();
    listSetIterator.reset();
    while (listSetIterator.hasNext()) {
      E e = (E) listSetIterator.next();
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
    ListSetIterator listSetIterator = (ListSetIterator) c.iterator();
    listSetIterator.reset();
    while (listSetIterator.hasNext()) {
      E e = (E) listSetIterator.next();
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
