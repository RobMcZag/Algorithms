package com.robertozagni.algoritmi.list;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {

  private static final int MIN_SIZE = 4;
  private int first = 0;
  private int last = 0;
  private int count = 0;
  private E[] values = null;

  /**
   * Create an empty queue.
   */
  public ArrayQueue() {
    @SuppressWarnings("unchecked")
    E[] v = (E[]) new Object[MIN_SIZE];
    values = v;
  }

  /**
   * Returns the index in the array corresponding to the linear position.
   * 
   * @param val the linear position to be wrapped onto the array.
   */
  private int index(int val) {
    return val % values.length;
  }

  @Override
  public void enqueue(E element) {
    values[index(last++)] = element;
    count++;
    if (count == values.length) {
      resize(count * 2);
    }
  }

  private void resize(int size) {
    @SuppressWarnings("unchecked")
    E[] dest = (E[]) new Object[size];

    int idxF = index(first);
    int idxL = index(last);
    // System.out.format("IN => idxF=%d | idxL=%d | c=%d | %s%n", idxF, idxL, count, Arrays.asList(values));

    if (idxF <= idxL) {
      System.arraycopy(values, idxF, dest, 0, count);
    } else {
      System.arraycopy(values, idxF, dest, 0, values.length - idxF);
      System.arraycopy(values, 0, dest, values.length - idxF, idxL);
    }

    values = dest;
    first = 0;
    last = count;
    // System.out.format("OUT=> first=%d | last=%d | c=%d | %s%n", first, last, count, Arrays.asList(values));
  }

  @Override
  public E dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty!");
    }
    E val = values[index(first++)];
    count--;
    if (count > MIN_SIZE / 2 && values.length >= count * 4) {
      resize(count * 2);
    }
    return val;
  }

  @Override
  public boolean isEmpty() {
    return count == 0;
  }

  @Override
  public int size() {
    return count;
  }

}
