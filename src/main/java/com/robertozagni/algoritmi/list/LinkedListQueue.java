package com.robertozagni.algoritmi.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements Queue<E> {

  private class LinkedListQueueIterator implements Iterator<E> {

    Node current = first;

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public E next() {
      E val = current.value;
      current = current.next;
      return val;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Remove operation is not supported by this iterator.");
    }

  }

  /** HEAD - The oldest node in the queue, i.e. the next that will be returned. */
  private Node first = null;

  /** TAIL - The node last added to the queue. */
  private Node last = null;

  /** The number of nodes in the queue. */
  private int count = 0;

  private class Node {
    private E value;
    private Node next;

    Node(E value, Node next) {
      this.value = value;
      this.next = next;
    }
  }

  /**
   * Create and empty queue to hold elements of the desired type.
   */
  public LinkedListQueue() {
  }

  @Override
  public void enqueue(E element) {
    if (isEmpty()) {
      last = new Node(element, null);
      first = last;
    } else {
      last.next = new Node(element, null);
      last = last.next;
    }
    count++;
  }

  @Override
  public E dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty!");
    }
    E val = first.value;
    first = first.next;
    if (isEmpty()) {
      last = null;
    }
    count--;
    return val;
  }

  @Override
  public boolean isEmpty() {
    return first == null;
  }

  @Override
  public int size() {
    return count;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedListQueueIterator();
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
