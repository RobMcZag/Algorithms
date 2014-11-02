package it.rz.algoritmi.binaryHeap;

import java.lang.reflect.Array;

/**
 * An BINARY HEAP is a complete binary tree where every node is lesser/greater than or equal to his children.
 * 
 * @author Roberto
 *
 * @param <V> the type of the values to store in the Heap
 */
public class BinaryMinHeap<V extends Comparable<V>> {
	
	/*
	 * Number of nodes is N = sum(2^l) for l from 0 to the number of levels-1.
	 * E.g. for 4 levels => N = 2^0 + 2^1 + 2^2 + 2^3 = 1 + 2 + 4 + 8 = 15 
	 * Is that N = 2^l -1     ===>    l = log2(N+1) 
	 */
	protected static final int NUMERO_LIVELLI_INIZIALI = 2;
	protected static final int NUMERO_NODI_INIZIALI =  (1 << NUMERO_LIVELLI_INIZIALI)-1;
			// OK for NUMERO_LIVELLI_INIZIALI <= 31 | NUMERO_NODI_INIZIALI ==> 2.147.483.647
			// for NUMERO_LIVELLI_INIZIALI > 31 ==> NUMERO_NODI_INIZIALI = (int) Math.pow(2, NUMERO_LIVELLI_INIZIALI);
	protected static final int MAX_NODI_AGGIUNTI =  (1 << (NUMERO_LIVELLI_INIZIALI * 2) );
	
	final V[] data;
	int numberOfNodes = 0;

	/**
	 * @return the numberOfNodes
	 */
	protected int getNumberOfNodes() {
		return numberOfNodes;
	}
	
	
	/**
	 * Gives the value stored in the requested position in the data structure underlying this heap.
	 * @return the data at a certain position
	 */
	protected V getData(int position) {
		if(position < 0 || position >= numberOfNodes) {
			throw new BinaryHeapOutOfBoundsException("Requested position ("+position+") is out of bounds as current Heap contains " +numberOfNodes+ " nodes.");
		}
		return data[position];
	}


	@SuppressWarnings("unchecked")
	public BinaryMinHeap(Class<V> clazz) {
		this.data = (V[]) Array.newInstance(clazz, NUMERO_NODI_INIZIALI);
	}

	protected int getLeftIndex(int currentIndex) {
		if (currentIndex < 0 ) {
			throw new BinaryHeapException("The currentIndex can not be negative. Passed index was:" + currentIndex); 
		}
		return (2 * currentIndex) + 1;
	}

	protected int getRightIndex(int currentIndex) {
		if (currentIndex < 0 ) {
			throw new BinaryHeapException("The currentIndex can not be negative. Passed index was:" + currentIndex); 
		}
		return (2 * currentIndex) + 2;
	}

	protected int getParentIndex(int currentIndex) {
		if (currentIndex <= 0 ) {
			throw new BinaryHeapException("The currentIndex can not be zero or negative. Passed index was:" + currentIndex); 
		}
		return (currentIndex -1) / 2;
	}

	public boolean isEmpty() {
		return (this.numberOfNodes == 0);
	}

	public V getMinimum() {
		if (isEmpty()) {
			throw new BinaryHeapException("There is no minimum in an Empty Heap.");
		}
		return data[0];
	}

	public void add(V value) {
		// TODO check if value is null
		
		// TODO check if data has room for new value & grow it if it does not
		
		// insert the new data & update node count
		data[numberOfNodes++]=value;
		
		// TODO sift the new data in the right position
		sift(numberOfNodes - 1);
	}

	protected void sift(int index) {
		if (isRootNode(index)) {
			return;
		}
		int parent = getParentIndex(index);
		if(needSwitch(this.data[index], this.data[parent])) {
			V temp = this.data[index];
			this.data[index] = this.data[parent];
			this.data[parent] = temp;
			
			sift(parent);
		}
	}


	protected boolean needSwitch(V nodeValue, V parentValue) {
		return (nodeValue.compareTo(parentValue) < 0);
	}

	private boolean isRootNode(int index) {
		return (index == 0);
	}
	
	
}
