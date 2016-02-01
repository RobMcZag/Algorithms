package com.robertozagni.algoritmi.list;

public interface Stack<E> extends Iterable<E> {

  /**
   * Inserts the value on top of the stack.
   * 
   * @param value the value to be inserted.
   */
  void push(E value);

  /**
   * Returns the value on top of the stack and removes it from the stack.
   */
  E pop();

  /**
   * Return the number of values in the stack.
   */
  long size();

  /**
   * Return <code>true</code> if the Stack is empty, <code>false</code> otherwise.
   */
  boolean isEmpty();
}
