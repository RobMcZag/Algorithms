package com.robertozagni.algoritmi.binarySearchTree;

import java.util.List;

public class BinarySearchTree<V extends Comparable<V>> {
	
	private Node<V> root = null;

	public BinarySearchTree() {
		this.root = null;
	}

	/**
	 * boolean	contains(Object o) method says: 
	 * "returns true if and only if this collection contains at least one element e such that (o==null ? e==null : o.equals(e))."
	 */
	public boolean contains(V value) {
		if (root == null) {
			return (value == null);
		} else {
			return root.contains(value);			
		}
	}
	
	/**
	 * int	size()
	 * Returns the number of elements in this collection.
	 */
	public int	size() {
		return  (root == null) ? 0:	root.size();
	}
	
	/**
	 * Returns true if this collection contains no elements.
	 */
	public boolean	isEmpty() {
		return (root == null);
	}
	
	/**
	 * void	clear()
	 * Removes all of the elements from this collection (optional operation).
	 */
	public void clear() {
		this.root = null;
	}
	
	/**
	 * boolean	add(E e)
	 * Ensures that this collection contains the specified element (optional operation).
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
	 * boolean	remove(E e)
	 * Ensures that this collection contains the specified element (optional operation).
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
	 * List the values contained in this tree in the order set by the cmpareTo() method on the Values V
	 * @return an ordered List of values 
	 */
	public List<V> listValues() {
		return this.root.listValues();
	}
	
	/**
	 * boolean	equals(Object o)
	 * Compares the specified object with this collection for equality.
	 */
	public boolean equals(BinarySearchTree<V> tree) {
		return listValues().equals(tree.listValues());
	}
	
}
