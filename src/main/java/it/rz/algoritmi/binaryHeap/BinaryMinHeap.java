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
	protected static final int NUMERO_LIVELLI_INIZIALI = 4;
	protected static final int NUMERO_NODI_INIZIALI =  (1 << NUMERO_LIVELLI_INIZIALI)-1;
			// OK for NUMERO_LIVELLI_INIZIALI <= 31 | NUMERO_NODI_INIZIALI ==> 2.147.483.647
			// for NUMERO_LIVELLI_INIZIALI > 31 ==> NUMERO_NODI_INIZIALI = (int) Math.pow(2, NUMERO_LIVELLI_INIZIALI);
	protected static final int MAX_NODI_AGGIUNTI =  (1 << (NUMERO_LIVELLI_INIZIALI * 2) );
	
	V[] data;
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

	public V getHead() {
		if (isEmpty()) {
			throw new BinaryHeapException("There is no head in an Empty Heap.");
		}
		return data[0];
	}

	public void add(V value) {
		if (value == null) {
			throw new BinaryHeapException("Can not add a null value");
		}
		
		if (numberOfNodes >= this.data.length) {
			extendDataArray();
		}
		
		this.data[numberOfNodes++]=value;
		
		siftIn(numberOfNodes - 1);
	}

	private void extendDataArray() {
		@SuppressWarnings("unchecked")
		V[] newData = (V[]) Array.newInstance(this.data[0].getClass(), Math.min((((this.data.length + 1) * 2 ) - 1), MAX_NODI_AGGIUNTI));
 		System.arraycopy(this.data, 0, newData, 0, this.data.length);
 		this.data = newData;
	}


	protected void siftIn(int index) {
		if (isRootNode(index)) {
			return;
		}
		int parent = getParentIndex(index);
		if(needSwitch(this.data[index], this.data[parent])) {
			V temp = this.data[index];
			this.data[index] = this.data[parent];
			this.data[parent] = temp;
			
			siftIn(parent);
		}
	}


	protected boolean needSwitch(V nodeValue, V parentValue) {
		return (nodeValue.compareTo(parentValue) < 0);
	}

	private boolean isRootNode(int index) {
		return (index == 0);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.numberOfNodes + " nodes : { ");
		for (int i = 0; i < this.data.length; i++) {
			sb.append(this.data[i]).append(", ");
		}
		sb.replace(sb.length()-2, sb.length(), "}");
		return sb.toString();
	}


	public V removeHead() {
		if (isEmpty()) {
			throw new BinaryHeapException("Can not remove Head from an Empty Heap");
		}
		
		V head = this.data[0];
		this.data[0] = this.data[--numberOfNodes];
		this.data[numberOfNodes] = null;
		
		if (! isEmpty()) {
			siftOut(0);
		}
		
		return head;
	}


	protected void siftOut(int index) {
		int minIdx = getMinChildIndex(index);
		
		if (this.data[index].compareTo(this.data[minIdx]) > 0 ) {
			V temp = this.data[index];
			this.data[index] = this.data[minIdx];
			this.data[minIdx] = temp;
			siftOut(minIdx);
		}
		
	}


	protected int getMinChildIndex(int index) {
		int leftIdx = getLeftIndex(index);
		if (leftIdx >= numberOfNodes) {
			return index;
		}
		int rightIdx = getRightIndex(index);
		V leftValue = this.data[leftIdx];
		V rightValue = this.data[rightIdx];
		
		if (rightValue == null || leftValue.compareTo(rightValue) <= 0) {
			return leftIdx;
		} else {
			return rightIdx;
		}
	}
	
	
	
}
