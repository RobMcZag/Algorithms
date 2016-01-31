package com.robertozagni.algoritmi.list;

import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E> {

  private static final int MIN_SIZE = 4;

  /**
   * The number of elements into the Stack.
   */
  private int N = 0;

  /**
   * The array holding the values inserted into the array.
   */
  private E[] vals = null;

  public ArrayStack() {

    @SuppressWarnings("unchecked")
    E[] v = (E[]) new Object[MIN_SIZE]; // Tmp local var to allow suppression inside method.
    vals = v;
  }

  @Override
  public void push(E value) {
    vals[N++] = value;

    if (N == vals.length) {
      resize(N * 2);
    }

  }

  private synchronized void resize(int size) {
    @SuppressWarnings("unchecked")
    E[] newvals = (E[]) new Object[size];
    System.arraycopy(vals, 0, newvals, 0, N);
    vals = newvals;
  }

  @Override
  public E pop() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack is empty!");
    }
    E val = vals[--N];
    vals[N] = null;

    if (N > MIN_SIZE / 2 && vals.length > 4 * N) {
      resize(N / 2);
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

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
