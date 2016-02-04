package com.robertozagni.algoritmi.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private static class RandomizedQueueIterator<Item> implements Iterator<Item> {

    private int pos = 0;
    private final int N;
    private final Item[] itvals;

    @SuppressWarnings("unchecked")
    RandomizedQueueIterator(Item[] v, int n) {
      N = n;
      itvals = (Item[]) new Object[N];
      System.arraycopy(v, 0, itvals, 0, N);
      StdRandom.shuffle(itvals);
    }

    @Override
    public boolean hasNext() {
      return pos < N;
    }

    @Override
    public Item next() {
      if (pos >= N) {
        throw new NoSuchElementException("There are no more elements in this iterator.");
      }
      return itvals[pos++];
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("The remove operation is not supported by this iterator.");
    }

  }

  private static final int MIN_SIZE = 4;

  private Item[] vals = null;

  private int count = 0;

  /**
   * Construct an empty randomized queue.
   */
  public RandomizedQueue() {
    @SuppressWarnings("unchecked")
    Item[] v = (Item[]) new Object[MIN_SIZE];
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
  public void enqueue(Item item) {
    if (item == null) {
      throw new NullPointerException("This queue does not support null values.");
    }
    vals[count++] = item;
    if (count == vals.length) {
      resize(count * 2);
    }
  }

  private void resize(int n) {
    // System.out.format("Count is %4d => from %4d to %4d.%n", count, vals.length, n);
    @SuppressWarnings("unchecked")
    Item[] dest = (Item[]) new Object[n];
    System.arraycopy(vals, 0, dest, 0, count);
    vals = dest;
  }

  /**
   * Remove and return a random item.
   */
  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("This queue is empty.");
    }
    int pos = StdRandom.uniform(count--);
    Item val = vals[pos];
    vals[pos] = vals[count];
    vals[count] = null;
    if (count >= MIN_SIZE / 2 && count == vals.length / 4) {
      resize(count * 2);
    }
    return val;
  }

  /**
   * Return (but do not remove) a random item.
   */
  public Item sample() {
    if (isEmpty()) {
      throw new NoSuchElementException("This queue is empty.");
    }
    int pos = StdRandom.uniform(count);
    return vals[pos];
  }

  /**
   * Return an independent iterator over items in random order.
   */
  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator<Item>(vals, count);
  }

  /**
   * Unit testing
   */
  public static void main(String[] args) {
  }

}
