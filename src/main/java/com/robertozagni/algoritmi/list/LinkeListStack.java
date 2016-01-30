package com.robertozagni.algoritmi.list;

/**
 * Stack implementation using linked list.
 * 
 * @author roberto.zagni
 *
 * @param <E>
 */
public class LinkeListStack<E> implements Stack<E> {

  private class Node {
    E value = null;
    Node next = null;

    /**
     * New initialized node.
     * 
     * @param value The value contained by the node.
     * @param next The reference to the next node.
     */
    Node(E value, Node next) {
      this.value = value;
      this.next = next;
    }

  }

  /* *** Linked List *** */

  Node first = null;
  long count = 0;

  /**
   * Return <code>true</code> if the Stack is empty, <code>false</code> otherwise.
   */
  public boolean isEmpty() {
    return first == null;
  }

  /**
   * Inserts the value at the beginning of the list.
   * 
   * @param value the value to be inserted.
   */
  public void push(E value) {
    first = new Node(value, first);
    count++;
  }

  /**
   * Returns the first value of the list and removes it from the list.
   */
  public E pop() {
    E val = first.value;
    first = first.next;
    count--;
    return val;
  }

  /**
   * Return the number of values in the list.
   */
  public long size() {
    return count;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
