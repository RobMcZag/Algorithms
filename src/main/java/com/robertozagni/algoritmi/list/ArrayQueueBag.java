package com.robertozagni.algoritmi.list;

import java.util.Iterator;

public class ArrayQueueBag<E> extends ArrayQueue<E> implements Bag<E> {

  public ArrayQueueBag() {
    super();
  }

  @Override
  public void add(E element) {
    super.enqueue(element);
  }

  @Override
  public int size() {
    return super.size();
  }

  @Override
  public Iterator<E> iterator() {
    return super.iterator();
  }

}
