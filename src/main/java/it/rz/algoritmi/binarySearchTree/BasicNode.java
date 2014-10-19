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
			addNull();
		} else {
			switch (this.value.compareTo(node.getValue())) {
			case 1:
				if (this.left == null) {
					this.left = node;
				} else {
					return this.left.add(node);
				}
				break;
			case 0:
				return false;	// Equal to current value, nothing added
			case -1:
				if (this.right == null) {
					this.right = node;
				} else {
					return this.right.add(node);
				}
				break;
			}
		}
		return true;
	}
	


	/**
	 * Handle insertion of <null> into the Tree.
	 * At this time <null> is not supported, so we will throw an unchecked exception.
	 * @throws NullPointerException
	 */
	private void addNull() {
		throw new NullPointerException("NULL nodes are not supported.");
	}
}
