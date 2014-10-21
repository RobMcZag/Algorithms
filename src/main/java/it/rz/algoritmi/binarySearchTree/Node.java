package it.rz.algoritmi.binarySearchTree;

public interface Node<V> {

	public V getValue();

	public Node<V> getLeft();
	public Node<V> getRight();
	
	public boolean add(Node<V> node);
	public boolean add(V value);
	
	public boolean contains(Node<V> node);
	public boolean contains(V value);
	
	public Node<V> search(Node<V> node);
	public Node<V> search(V value);
	
	public Node<V> remove(Node<V> node);
	public Node<V> remove(V value);

}
