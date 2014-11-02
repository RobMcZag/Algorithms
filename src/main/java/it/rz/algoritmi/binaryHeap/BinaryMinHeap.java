package it.rz.algoritmi.binaryHeap;

import java.lang.reflect.Array;

public class BinaryMinHeap<V> {
	
	/*
	 * Number of nodes is N = sum(2^l) for l from 0 to the number of levels-1.
	 * E.g. for 4 levels => N = 2^0 + 2^1 + 2^2 + 2^3 = 1 + 2 + 4 + 8 = 15 
	 * Is that N = 2^l -1     ===>    l = log2(N+1) 
	 */
	protected static final int NUMERO_LIVELLI_INIZIALI = 4;
	protected static final int NUMERO_NODI_INIZIALI = (2^NUMERO_LIVELLI_INIZIALI)-1;
	
	private final V[] data;
	private int heapNumberOfNodes = 0;

	@SuppressWarnings("unchecked")
	public BinaryMinHeap(Class<V> clazz) {
		this.data = (V[]) Array.newInstance(clazz, NUMERO_NODI_INIZIALI);
	}


	public int getLeftIndex(int currentIndex) {
		if (currentIndex < 0 ) {
			throw new BinaryMinHeapException("The currentIndex can not be negative. Passed index was:" + currentIndex); 
		}
		return (2 * currentIndex) + 1;
	}

	public int getRightIndex(int currentIndex) {
		if (currentIndex < 0 ) {
			throw new BinaryMinHeapException("The currentIndex can not be negative. Passed index was:" + currentIndex); 
		}
		return (2 * currentIndex) + 2;
	}

	public int getParentIndex(int currentIndex) {
		if (currentIndex <= 0 ) {
			throw new BinaryMinHeapException("The currentIndex can not be zero or negative. Passed index was:" + currentIndex); 
		}
		return (currentIndex -1) / 2;
	}

}
