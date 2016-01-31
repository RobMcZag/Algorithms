package com.robertozagni.algoritmi.list;

import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements Queue<E> {

  private Node first = null;
  private Node last = null;
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

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
