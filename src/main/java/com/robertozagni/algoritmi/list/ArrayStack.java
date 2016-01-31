package com.robertozagni.algoritmi.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E> {

  private class ArrayStackIterator implements Iterator<E> {

    private int pos = N - 1;

    @Override
    public boolean hasNext() {
      return pos >= 0;
    }

    @Override
    public E next() {
      return values[pos--];
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Remove operation is not supported by this iterator.");
    }

  }

  private static final int MIN_SIZE = 4;

  /**
   * The number of elements into the Stack.
   */
  private int N = 0;

  /**
   * The array holding the values inserted into the array.
   */
  private E[] values = null;

  public ArrayStack() {

    @SuppressWarnings("unchecked")
    E[] v = (E[]) new Object[MIN_SIZE]; // Tmp local var to allow suppression inside method.
    values = v;
  }

  @Override
  public void push(E value) {
    values[N++] = value;

    if (N == values.length) {
      resize(N * 2);
    }

  }

  private synchronized void resize(int size) {
    @SuppressWarnings("unchecked")
    E[] newvals = (E[]) new Object[size];
    System.arraycopy(values, 0, newvals, 0, N);
    values = newvals;
  }

  @Override
  public E pop() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack is empty!");
    }
    E val = values[--N];
    values[N] = null;

    if (N > MIN_SIZE / 2 && values.length > 4 * N) {
      resize(values.length / 2);
    }

    return val;
  }

  @Override
  public long size() {
    return N;
  }

  @Override
  public boolean isEmpty() {
    return N == 0;
  }

  @Override
  public Iterator<E> iterator() {
    return new ArrayStackIterator();
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
