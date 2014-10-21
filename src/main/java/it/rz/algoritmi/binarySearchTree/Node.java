package it.rz.algoritmi.binarySearchTree;

public interface Node<V> {

	public V getValue();
	
	public boolean add(Node<V> node);
	
	public boolean contains(Node<V> node);
	public boolean contains(V value);
	
	public Node<V> search(Node<V> node);
	public Node<V> search(V value);
}
