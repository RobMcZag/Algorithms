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
	
	private final V[] data;
	private int numberOfNodes = 0;

	@SuppressWarnings("unchecked")
	public BinaryMinHeap(Class<V> clazz) {
		this.data = (V[]) Array.newInstance(clazz, NUMERO_NODI_INIZIALI);
	}

	protected int getLeftIndex(int currentIndex) {
		if (currentIndex < 0 ) {
			throw new BinaryMinHeapException("The currentIndex can not be negative. Passed index was:" + currentIndex); 
		}
		return (2 * currentIndex) + 1;
	}

	protected int getRightIndex(int currentIndex) {
		if (currentIndex < 0 ) {
			throw new BinaryMinHeapException("The currentIndex can not be negative. Passed index was:" + currentIndex); 
		}
		return (2 * currentIndex) + 2;
	}

	protected int getParentIndex(int currentIndex) {
		if (currentIndex <= 0 ) {
			throw new BinaryMinHeapException("The currentIndex can not be zero or negative. Passed index was:" + currentIndex); 
		}
		return (currentIndex -1) / 2;
	}

	public boolean isEmpty() {
		return (this.numberOfNodes == 0);
	}

	public V getMinimum() {
		if (isEmpty()) {
			throw new BinaryMinHeapException("There is no minimum in an Empty Heap.");
		}
		return data[0];
	}

	public void add(V value) {
		// check if data has room
		
		data[numberOfNodes++]=value;
	}
	
	
}
