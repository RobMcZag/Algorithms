/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.binaryHeap;

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
     * Number of nodes is N = sum(2^l) for l from 0 to the number of levels-1. E.g. for 4 levels => N = 2^0 + 2^1 + 2^2
     * + 2^3 = 1 + 2 + 4 + 8 = 15 Is that N = 2^l -1 ===> l = log2(N+1)
     */
    protected static final int NUMERO_LIVELLI_INIZIALI = 4;
    protected static final int NUMERO_NODI_INIZIALI = (1 << NUMERO_LIVELLI_INIZIALI) - 1;
    // OK for NUMERO_LIVELLI_INIZIALI <= 31 | NUMERO_NODI_INIZIALI ==> 2.147.483.647
    // for NUMERO_LIVELLI_INIZIALI > 31 ==> NUMERO_NODI_INIZIALI = (int) Math.pow(2, NUMERO_LIVELLI_INIZIALI);
    protected static final int MAX_NODI_AGGIUNTI = (1 << (NUMERO_LIVELLI_INIZIALI * 2));

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
     * 
     * @return the data at a certain position
     */
    protected V getData(int position) {
        if (position < 0 || position >= numberOfNodes) {
            throw new BinaryHeapOutOfBoundsException("Requested position (" + position
                    + ") is out of bounds as current Heap contains " + numberOfNodes + " nodes.");
        }
        return data[position];
    }

    @SuppressWarnings("unchecked")
    public BinaryMinHeap(Class<V> clazz) {
        this.data = (V[]) Array.newInstance(clazz, NUMERO_NODI_INIZIALI);
    }

    /**
     * Find the index of the left child of the node referenced by the passed index
     * 
     * @param currentIndex the index of the node we want the left child for;
     * @return the index of the left child of the referenced node.
     */
    protected int getLeftIndex(int currentIndex) {
        if (currentIndex < 0) {
            throw new BinaryHeapException("The currentIndex can not be negative. Passed index was:" + currentIndex);
        }
        return (2 * currentIndex) + 1;
    }

    /**
     * Find the index of the right child of the node referenced by the passed index
     * 
     * @param currentIndex the index of the node we want the right child for;
     * @return the index of the right child of the referenced node.
     */
    protected int getRightIndex(int currentIndex) {
        if (currentIndex < 0) {
            throw new BinaryHeapException("The currentIndex can not be negative. Passed index was:" + currentIndex);
        }
        return (2 * currentIndex) + 2;
    }

    /**
     * Finds the index of the parent of the node identified by some index
     * 
     * @param currentIndex the index of the node we want the parent for
     * @return the parent for the node with the passed index
     */
    protected int getParentIndex(int currentIndex) {
        if (currentIndex <= 0) {
            throw new BinaryHeapException(
                    "The currentIndex can not be zero or negative. Passed index was:" + currentIndex);
        }
        return (currentIndex - 1) / 2;
    }

    /**
     * Tells if this heap is empty or contains at least one value.
     * 
     * @return True if this heap is empty.
     */
    public boolean isEmpty() {
        return (this.numberOfNodes == 0);
    }

    /**
     * Returns the head of this heap.
     * 
     * @return the head of this heap.
     */
    public V getHead() {
        if (isEmpty()) {
            throw new BinaryHeapException("There is no head in an Empty Heap.");
        }
        return data[0];
    }

    /**
     * Adds a value to this heap.
     * 
     * @param value The value to add to this heap.
     * @throws BinaryHeapException if the passed value is null.
     */
    public void add(V value) {
        if (value == null) {
            throw new BinaryHeapException("Can not add a null value");
        }

        int position;
        synchronized (data) {
            if (numberOfNodes >= this.data.length) {
                extendDataArray();
            }
            position = numberOfNodes++;
        }

        this.data[position] = value;
        siftIn(position);
    }

    private void extendDataArray() {
        @SuppressWarnings("unchecked")
        V[] newData = (V[]) Array.newInstance(this.data[0].getClass(),
                Math.min((((this.data.length + 1) * 2) - 1), MAX_NODI_AGGIUNTI));
        System.arraycopy(this.data, 0, newData, 0, this.data.length);
        this.data = newData;
    }

    /**
     * Reorders the Heap nodes moving the referenced node towards the head of the heap, if needed.
     * 
     * @param index the index of the node to move towards the head of the heap
     */
    protected void siftIn(int index) {
        if (isRootNode(index)) {
            return;
        }
        int parent = getParentIndex(index);
        if (needSwitchIn(index, parent)) {
            swapValues(index, parent);
            siftIn(parent);
        }
    }

    /**
     * Swaps the values of the two nodes referenced by the two indexes passed
     * 
     * @param indexN1 index of first node
     * @param indexN2 index of second node
     */
    protected void swapValues(int index, int indexN2) {
        V temp = this.data[index];
        this.data[index] = this.data[indexN2];
        this.data[indexN2] = temp;
    }

    /**
     * Checks if the current node has to be switched with the node to compare when moving towards the head of the heap.
     * This is one of the methods to override to create an heap with a different ordering.
     * 
     * @param nodeIndex The index of the "current" node
     * @param compareIdx The index of the node to compare
     * @return true if the two nodes have to be switched to fulfill this heap ordering
     */
    protected boolean needSwitchIn(int nodeIndex, int compareIdx) {
        return (this.data[nodeIndex].compareTo(this.data[compareIdx]) < 0);
    }

    private boolean isRootNode(int index) {
        return (index == 0);
    }

    /**
     * Creates a string describing the content of this heap.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.numberOfNodes + " nodes : { ");
        for (int i = 0; i < this.data.length; i++) {
            sb.append(this.data[i]).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length(), "}");
        return sb.toString();
    }

    /**
     * Removes the Head of the Heap and returns it
     * 
     * @return the Head of the Heap
     * @throws BinaryHeapException if the Heap is Empty
     */
    public V removeHead() {
        if (isEmpty()) {
            throw new BinaryHeapException("Can not remove Head from an Empty Heap");
        }

        V head = this.data[0];
        this.data[0] = this.data[--numberOfNodes];
        this.data[numberOfNodes] = null;

        if (!isEmpty()) {
            siftOut(0);
        }

        return head;
    }

    /**
     * Reorders the Heap nodes moving the referenced node away from the head of the heap, if needed.
     * 
     * @param index the index of the node to move away from the head of the heap
     */
    protected void siftOut(int index) {
        int candidateChildIdx = getSwapCandidateChildIndex(index);

        if (needSwitchOut(index, candidateChildIdx)) {
            swapValues(index, candidateChildIdx);
            siftOut(candidateChildIdx);
        }
    }

    /**
     * Checks if the current node has to be switched with the node to compare when moving away from the head of the
     * heap. This is one of the methods to override to create an heap with a different ordering.
     * 
     * @param nodeIndex The index of the "current" node
     * @param compareIdx The index of the node to compare
     * @return true if the two nodes have to be switched to fulfill this heap ordering
     */
    protected boolean needSwitchOut(int nodeIndex, int compareIdx) {
        return (this.data[nodeIndex].compareTo(this.data[compareIdx]) > 0);
    }

    /**
     * Find the index of the child of the referenced node to evaluate for the switch with the parent in a siftOut
     * operation. This is one of the methods to override to create an heap with a different ordering.
     * 
     * @param index the index of the parent node
     * @return the index of the child to evaluate for the switch with the parent
     */
    protected int getSwapCandidateChildIndex(int index) {
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
