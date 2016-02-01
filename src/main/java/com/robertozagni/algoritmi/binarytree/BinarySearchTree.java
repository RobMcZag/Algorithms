/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.binarytree;

import java.util.List;

/**
 * A binary tree where nodes are kept ordered on the values stored in the nodes.
 * 
 * @author roberto.zagni
 *
 * @param <V> a type that implements the comparable interface to be sorted.
 */
public class BinarySearchTree<V extends Comparable<V>> {

  private Node<V> root = null;

  public BinarySearchTree() {
    this.root = null;
  }

  /**
   * Returns true if and only if this tree contains at least one element e such that (o==null ? e==null :o.equals(e)).
   */
  public boolean contains(V value) {
    if (root == null) {
      return (value == null);
    } else {
      return root.contains(value);
    }
  }

  /**
   * Returns the number of elements in this collection.
   */
  public int size() {
    return (root == null) ? 0 : root.size();
  }

  /**
   * Returns true if this collection contains no elements.
   */
  public boolean isEmpty() {
    return (root == null);
  }

  /**
   * Removes all of the elements from this collection.
   */
  public void clear() {
    this.root = null;
  }

  /**
   * Ensures that this tree contains the specified element.
   */
  public boolean add(V value) {
    if (root == null) {
      root = new BasicNode<V>(value);
      return true;
    } else {
      return root.add(new BasicNode<V>(value));
    }
  }

  /**
   * Ensures that this tree deos not contains the specified element.
   */
  public boolean remove(V value) {
    if (root == null) {
      return false;
    } else {
      this.root = this.root.remove(value);
      return true;
    }
  }

  /**
   * List the values contained in this tree in the order set by the compareTo() method on the Values V
   * 
   * @return an ordered List of values
   */
  public List<V> listValues() {
    return this.root.listValues();
  }

  /**
   * Compares the specified object with this tree for equality.
   */
  public boolean equals(Object obj) {
    if (obj instanceof BinarySearchTree<?>) {
      @SuppressWarnings("unchecked")
      BinarySearchTree<V> tree = (BinarySearchTree<V>) obj;
      return listValues().equals(tree.listValues());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return listValues().hashCode();
  }

}
