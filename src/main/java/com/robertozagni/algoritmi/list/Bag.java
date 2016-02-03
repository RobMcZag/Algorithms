package com.robertozagni.algoritmi.list;

/**
 * A collection of objects that only allows to add elements, count and enumerate the content.
 * 
 * @author roberto.zagni
 *
 * @param <E> The type of the elements of the collection.
 */
public interface Bag<E> {

  /**
   * Insert a new element onto bag.
   * 
   * @param x the element to be added into the bag.
   */
  void add(E x);

  /**
   * Returns the number of elements in bag.
   */
  int size();

  /**
   * Returns an iterator to enumerate all items in bag.
   */
  Iterable<E> iterator();
}
