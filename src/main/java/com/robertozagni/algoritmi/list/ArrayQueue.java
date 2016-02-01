package com.robertozagni.algoritmi.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {

  public class ArrayQueueIterator implements Iterator<E> {

    int pos = first;

    @Override
    public boolean hasNext() {
      return pos < last;
    }

    @Override
    public E next() {
      return values[index(pos++)];
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Remove operation is not supported by this iterator.");
    }

  }

  private static final int MIN_SIZE = 4;

  /** The position of the first item, the one that will be returned next. */
  private int first = 0;

  /** The next position to be used in the array, where the next item will be inserted. */
  private int last = 0;

  /** The number of items in the queue. */
  private int count = 0;

  /** The array containing the elements in the queue. Will be resized up and down upon necessity. */
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

    if (idxF == 0 || idxF < idxL) {
      System.arraycopy(values, idxF, dest, 0, count);
    } else {
      System.arraycopy(values, idxF, dest, 0, values.length - idxF);
      System.arraycopy(values, 0, dest, values.length - idxF, idxL);
    }

    values = dest;
    first = 0;
    last = count;
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
  public Iterator<E> iterator() {
    return new ArrayQueueIterator();
  }

  @Override
  public int size() {
    return count;
  }

}
