package com.homework;

public interface Iterator<E> {

  boolean hasNext();

  void reset();

  E next();

  void remove();
}
