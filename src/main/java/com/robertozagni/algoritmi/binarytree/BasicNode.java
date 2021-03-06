/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple node of a BinarySearchTree holding a value of type V and being able to connect to other nodes. Nodes are
 * stored "left" if their value is lass than this node's value or right if it's higher.
 * 
 * @author Roberto Zagni
 *
 * @param <V> The value type contained in a node.
 */
public class BasicNode<V extends Comparable<V>> implements Node<V> {

  protected Node<V> left = null;
  protected Node<V> right = null;
  private V value = null;

  public BasicNode(V value) {
    if (value == null) {
      throw new NullPointerException("NULL values are not supported.");
    }
    this.value = value;
  }

  @Override
  public V getValue() {
    return this.value;
  }

  /**
   * @return the left
   */
  public Node<V> getLeft() {
    return left;
  }

  /**
   * @return the right
   */
  public Node<V> getRight() {
    return right;
  }

  /**
   * adds the passed node to this node
   * 
   * @param node The node to add starting from this node
   * @return true if we add the passed node, false if we do not add the node as it already exists
   * @throws NullPointerException if the passed node to add is null
   */
  public boolean add(V val) {
    return add(new BasicNode<V>(val));
  }

  public boolean add(Node<V> node) {
    if (node == null) {
      addNullNode();
    } else {
      int comparison = this.value.compareTo(node.getValue());
      if (comparison == 0) {
        return false; // Equal to current value, nothing added
      } else if (comparison > 0) {
        if (this.left == null) {
          this.left = node;
        } else {
          return this.left.add(node);
        }
      } else {
        if (this.right == null) {
          this.right = node;
        } else {
          return this.right.add(node);
        }
      } // if comparison
    }
    return true;
  }

  /**
   * Handle insertion of <null> into the Tree. At this time <null> is not supported, so we will throw an unchecked
   * exception.
   * 
   * @throws NullPointerException
   */
  private void addNullNode() {
    throw new NullPointerException("NULL nodes are not supported.");
  }

  public boolean contains(Node<V> node) {
    return contains(node.getValue());
  }

  public boolean contains(V val) {
    if (val == null) {
      return (this.value == null);
    } else {
      int comparison = val.compareTo(this.value);
      if (comparison == 0) {
        return true; // Equal to current value, found
      } else if (comparison < 0) {
        if (this.left == null) {
          return false;
        } else {
          return this.left.contains(val);
        }
      } else {
        if (this.right == null) {
          return false;
        } else {
          return this.right.contains(val);
        }
      } // if comparison
    }
  }

  public Node<V> search(Node<V> node) {
    return search(node.getValue());
  }

  public Node<V> search(V val) {
    if ((val == null) && (this.value == null)) {
      return this;
    } else {
      int comparison = val.compareTo(this.value);
      if (comparison == 0) {
        return this; // Equal to current value, found
      } else if (comparison < 0) {
        if (this.left == null) {
          return null;
        } else {
          return this.left.search(val);
        }
      } else {
        if (this.right == null) {
          return null;
        } else {
          return this.right.search(val);
        }
      } // if comparison
    }
  }

  public Node<V> remove(Node<V> node) {
    return remove(node.getValue());
  }

  /**
   * Removes the node for the passed value and returns the remaining node chain starting from this node.
   * 
   * @param val the value to remove from the tree referenced by this node
   * @return the tree referenced by this node after removing the required value, if present.
   */
  public Node<V> remove(V val) {
    if ((val == null) ? (this.value == null) : (val.compareTo(this.value) == 0)) {

      if (this.left == null) {
        return this.right;
      }
      if (this.right == null) {
        return this.left;
      }

      this.right.add(this.left);
      return this.right;

    } else {

      if ((val == null) || (val.compareTo(this.value) < 0)) {
        if (this.left != null) {
          this.left = this.left.remove(val);
        }
      } else {
        if (this.right != null) {
          this.right = this.right.remove(val);
        }
      }

      return this;
    }

  } /* public Node<V> remove(V value) */

  /**
   * List values accessible from this node in the order set by the cmpareTo() method
   * 
   * @return an ordered List of values
   */
  public List<V> listValues() {
    List<V> list = new ArrayList<V>();
    if (this.left != null) {
      list.addAll(this.left.listValues());
    }

    list.add(getValue());

    if (this.right != null) {
      list.addAll(this.right.listValues());
    }

    return list;
  }

  /**
   * @return the number of elements connected to this node.
   */
  public int size() {
    return this.listValues().size();

  }

  @Override
  public int hashCode() {
    return listValues().hashCode();
  }

  /**
   * boolean equals(Object o) Compares the specified object with this for equality.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Node<?>) {
      @SuppressWarnings("unchecked")
      Node<V> node = (Node<V>) obj;
      return listValues().equals(node.listValues());
    }
    return false;
  }

} /* end of class */
