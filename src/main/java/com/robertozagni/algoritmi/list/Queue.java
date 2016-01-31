package com.robertozagni.algoritmi.list;

public interface Queue<E> {

  /**
   * Insert a new element onto queue
   * 
   * @param element
   */
  void enqueue(E element);

  /**
   * Remove and return the element least recently added.
   * 
   * @return the "oldest" element in the queue.
   */
  E dequeue();

  /**
   * Tests if the queue is empty?
   * 
   * @return <code>true</code> if the queue is empty, <code>false</code> otherwise.
   */
  boolean isEmpty();

  /**
   * Returns the number of elements on the queue
   */
  int size();
}
