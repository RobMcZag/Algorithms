package com.robertozagni.algoritmi.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedListDeque<Item> implements Deque<Item> {

  private static class Node<Item> {
    Item item;
    Node<Item> prev;
    Node<Item> next;

    private Node(Item item, Node<Item> prev, Node<Item> next) {
      super();
      this.item = item;
      this.prev = prev;
      this.next = next;
    }
  }

  private static class DequeIterator<Item> implements Iterator<Item> {

    Node<Item> current;

    private DequeIterator(Node<Item> first) {
      super();
      this.current = first;
    }

    @Override
    public boolean hasNext() {
      return current.next.item != null;
    }

    @Override
    public Item next() {
      if (current.next.item == null) {
        throw new NoSuchElementException("You are already at the end of this deque, nothing more to fetch.");
      }
      Item item = current.next.item;
      current = current.next;
      return item;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Remove operation is not supported by this iterator.");
    }
  }

  /* LINKED LIST */
  private Node<Item> first = new Node<Item>(null, null, null);
  private Node<Item> last = new Node<Item>(null, null, null);
  private int count = 0;

  public DoubleLinkedListDeque() {
    first.next = last;
    last.prev = first;
  }

  @Override
  public boolean isEmpty() {
    return count == 0;
  }

  @Override
  public int size() {
    return count;
  }

  @Override
  public void addFirst(Item item) {

    if (item == null) {
      throw new NullPointerException("This deque does not support null items.");
    }

    Node<Item> node = new Node<Item>(item, first, first.next);
    first.next.prev = node;
    first.next = node;
    count++;
  }

  @Override
  public void addLast(Item item) {

    if (item == null) {
      throw new NullPointerException("This deque does not support null items.");
    }

    Node<Item> node = new Node<Item>(item, last.prev, last);
    last.prev.next = node;
    last.prev = node;
    count++;
  }

  @Override
  public Item removeFirst() {

    if (first.next.item == null) {
      throw new NoSuchElementException("This deque is empty, nothing to remove.");
    }

    Item item = first.next.item;
    first.next.next.prev = first;
    first.next = first.next.next;
    count--;
    return item;
  }

  @Override
  public Item removeLast() {

    if (last.prev.item == null) {
      throw new NoSuchElementException("This deque is empty, nothing to remove.");
    }

    Item item = last.prev.item;
    last.prev.prev.next = last;
    last.prev = last.prev.prev;
    count--;
    return item;
  }

  @Override
  public Iterator<Item> iterator() {
    return new DequeIterator<Item>(first);
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
