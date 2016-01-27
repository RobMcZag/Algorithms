/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.binarytree;

import java.util.List;

public interface Node<V> {

  V getValue();

  Node<V> getLeft();

  Node<V> getRight();

  boolean add(Node<V> node);

  boolean add(V value);

  boolean contains(Node<V> node);

  boolean contains(V value);

  Node<V> search(Node<V> node);

  Node<V> search(V value);

  Node<V> remove(Node<V> node);

  Node<V> remove(V value);

  List<V> listValues();

  boolean equals(Object obj);

  int size();

}
