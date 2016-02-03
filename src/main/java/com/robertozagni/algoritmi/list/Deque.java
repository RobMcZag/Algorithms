package com.robertozagni.algoritmi.list;

import java.util.Iterator;

public interface Deque<Item> extends Iterable<Item> {

  /**
   * Tests if the deque is empty.
   */
  boolean isEmpty();

  /**
   * Return the number of items on the deque.
   */
  int size();

  /**
   * Add the item to the front.
   * 
   * @param item the item to be added.
   */
  void addFirst(Item item);

  /**
   * Add the item to the end
   * 
   * @param item the item to be added.
   */
  void addLast(Item item);

  /**
   * Remove and return the item from the front.
   */
  Item removeFirst();

  /**
   * Remove and return the item from the end
   */
  Item removeLast();

  /**
   * Return an iterator over items in order from front to end
   */
  Iterator<Item> iterator();

}
