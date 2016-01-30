package com.robertozagni.algoritmi.list;

import java.lang.reflect.Array;
import java.util.EmptyStackException;

public class ArrayStack<E> implements Stack<E> {

  /**
   * The number of elements into the Stack.
   */
  private int N = 0;

  // /**
  // * The runtime class of the values.
  // */
  // private Class<T> clazz;

  /**
   * The array holding the values inserted into the array.
   */
  private E[] vals = null;

  public ArrayStack(Class<E> clazz) {
    // this.clazz = clazz;

    @SuppressWarnings("unchecked")
    E[] v = (E[]) Array.newInstance(clazz, 4); // Tmp local var to allow suppression inside method.
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
    E[] newvals = (E[]) Array.newInstance(vals.getClass().getComponentType(), size);
    System.arraycopy(vals, 0, newvals, 0, N);
    vals = newvals;
  }

  @Override
  public E pop() {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    E val = vals[--N];
    vals[N] = null;

    if (N > 4 && vals.length > 4 * N) {
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
