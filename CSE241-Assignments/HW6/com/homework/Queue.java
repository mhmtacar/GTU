package com.homework;

public interface Queue<E> extends Collection<E> {


  E element();

  void offer(E e);

  E poll();
}

