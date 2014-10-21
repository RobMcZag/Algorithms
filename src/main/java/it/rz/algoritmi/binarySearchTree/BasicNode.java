package it.rz.algoritmi.binarySearchTree;

/**
 * @author Roberto
 *
 * @param <V>
 */
public class BasicNode<V extends Comparable<V>> implements Node<V> {

	private V value = null;
	protected Node<V> left = null;
	protected Node<V> right = null;
	
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
	 * @param node The node to add starting from this node
	 * @return true if we add the passed node, false if we do not add the node as it already exists
	 * @throws NullPointerException if the passed node to add is null
	 */
	public boolean add(V value) {
		return add(new BasicNode<V>(value));
	}
	
	public boolean add(Node<V> node) {
		if (node == null) {
			addNullNode();
		} else {
			int comparison = this.value.compareTo(node.getValue());
			if (comparison == 0) {
				return false;	// Equal to current value, nothing added
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
	 * Handle insertion of <null> into the Tree.
	 * At this time <null> is not supported, so we will throw an unchecked exception.
	 * @throws NullPointerException
	 */
	private void addNullNode() {
		throw new NullPointerException("NULL nodes are not supported.");
	}
	
	
	public boolean contains(Node<V> node) {
		return contains(node.getValue());
	}
	
	public boolean contains(V value) {
		if (value == null) {
			return (this.value == null);
		} else {
			int comparison = value.compareTo(this.value);
			if (comparison == 0) {
				return true;	// Equal to current value, found
			} else if (comparison < 0) {
				if (this.left == null) {
					return false;
				} else {
					return this.left.contains(value);
				}
			} else {
				if (this.right == null) {
					return false;
				} else {
					return this.right.contains(value);
				}
			} // if comparison
		}
	}

	
	public Node<V> search(Node<V> node) {
		return search(node.getValue());
	}
	
	public Node<V> search(V value) {
		if ((value == null) && (this.value == null)) {
			return this;
		} else {
			int comparison = value.compareTo(this.value);
			if (comparison == 0) {
				return this;	// Equal to current value, found
			} else if (comparison < 0) {
				if (this.left == null) {
					return null;
				} else {
					return this.left.search(value);
				}
			} else {
				if (this.right == null) {
					return null;
				} else {
					return this.right.search(value);
				}
			} // if comparison
		}
	}
	
	public Node<V> remove(Node<V> node) {
		return remove(node.getValue());
	}

	public Node<V> remove(V value) {
		if ( ((value == null) && (this.value == null)) 
			 || (value.compareTo(this.value) == 0) ) {
			if (this.left == null) {
				return this.right;
			}
			if (this.right == null) {
				return this.left;
			}
			this.right.add(this.left);
			return this.right;
		} else {
			if (value.compareTo(this.value) < 0) {
				if (this.left == null) {
					return this;
				} else {
					this.left = this.left.remove(value);
					return this;
				}
			} else {
				if (this.right == null) {
					return this;
				} else {
					this.right = this.right.remove(value);
					return this;
				}
			} // if comparison
		}
		
	}

}
