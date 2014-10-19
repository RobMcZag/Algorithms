package it.rz.algoritmi.binarySearchTree;

public class BinarySearchTree<V extends Comparable<V>> {
	
	private Node<V> root = null;

	public BinarySearchTree() {
		this.root = null;
	}

	/**
	 * boolean	contains(Object o) method says: 
	 * "returns true if and only if this collection contains at least one element e such that (o==null ? e==null : o.equals(e))."
	 */
	
	/**
	 * int	size()
	 * Returns the number of elements in this collection.
	 */
	
	/**
	 * Returns true if this collection contains no elements.
	 */
	boolean	isEmpty() {
		return (root == null);
	}
	
	/**
	 * void	clear()
	 * Removes all of the elements from this collection (optional operation).
	 */
	
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
	 * boolean	equals(Object o)
	 * Compares the specified object with this collection for equality.
	 */
	
	/**
	 * 
	 */
}
