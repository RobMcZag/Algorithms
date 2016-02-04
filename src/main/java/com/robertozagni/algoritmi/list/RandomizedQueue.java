package com.robertozagni.algoritmi.list;

import java.util.Iterator;

public class RandomizedQueue<E> {

  private static final int MIN_SIZE = 8;

  private E[] vals = null;

  private int first = MIN_SIZE / 2;
  private int last = first;
  private int count = 0;

  /**
   * Construct an empty randomized queue.
   */
  public RandomizedQueue() {
    @SuppressWarnings("unchecked")
    E[] v = (E[]) new Object[MIN_SIZE];
    vals = v;
  }

  /**
   * Tests if the queue is empty.
   */
  public boolean isEmpty() {
    return count == 0;
  }

  /**
   * Return the number of items on the queue.
   */
  public int size() {
    return count;
  }

  /**
   * Add the item to the queue.
   * 
   * @param item the item to be added.
   */
  public void enqueue(E item) {
    // TODO - Implement
    count++;
  }

  /**
   * Remove and return a random item.
   */
  public E dequeue() {
    // TODO - Implement
    count--;
    return null;
  }

  /**
   * Return (but do not remove) a random item.
   */
  public E sample() {
    // TODO - Implement

    return null;
  }

  /**
   * Return an independent iterator over items in random order.
   */
  public Iterator<E> iterator() {
    // TODO - Implement

    return null;
  }

  /**
   * Unit testing
   */
  public static void main(String[] args) {
  }

}
