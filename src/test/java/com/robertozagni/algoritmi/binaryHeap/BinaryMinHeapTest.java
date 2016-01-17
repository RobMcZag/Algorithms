/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.binaryHeap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BinaryMinHeapTest {

    public BinaryMinHeapTest() {
    }

    @Test
    public void shouldCreateANewBinaryMinHeap() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        assertNotNull(bmh);
    }

    @Test
    public void shouldCalculateLeftOfRoot() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        assertEquals(1, bmh.getLeftIndex(0));
    }

    @Test
    public void shouldCalculateRightOfRoot() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        assertEquals(2, bmh.getRightIndex(0));
    }

    /**
     * Answering the parent of root is not an easy answer. Possible answers are: 1. there is no parent of root => throw
     * exception; 2. parent of root is root => return root itself; 3. there is no parent of root => return -1 (or any
     * impossible index);
     * 
     * I adopted solution 1 that I like as uses language constructs to handle corner cases; also the 2nd solution has
     * some appeal, as it turns a special case into a standard one, but I am afraid this could easily cause infinite
     * loops; solution 3 to me is bad.
     */
    @Test(expected = BinaryHeapException.class)
    public void shouldCalculateParentOfRoot() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        bmh.getParentIndex(0);
    }

    /**
     * Indexes run from 0 (included) upwards, so passing a negative index should throw an exception.
     */
    @Test(expected = BinaryHeapException.class)
    public void shouldCalculateLeftOfNegativeIndexes() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        assertEquals(12345, bmh.getLeftIndex(-1));
    }

    @Test(expected = BinaryHeapException.class)
    public void shouldCalculateRightOfNegativeIndexes() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        assertEquals(-12345, bmh.getRightIndex(-5));
    }

    @Test(expected = BinaryHeapException.class)
    public void shouldCalculateParentOfNegativeIndexes() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        assertEquals(-12345, bmh.getParentIndex(-7));
    }

    @Test
    public void shouldCalculateLeftOfSomeValidIndexes() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        assertEquals(3, bmh.getLeftIndex(1));
        assertEquals(5, bmh.getLeftIndex(2));
        assertEquals(7, bmh.getLeftIndex(3));
        assertEquals(9, bmh.getLeftIndex(4));
        assertEquals(13, bmh.getLeftIndex(6));
        assertEquals(21, bmh.getLeftIndex(10));
        assertEquals(247, bmh.getLeftIndex(123));
    }

    @Test
    public void shouldCalculateRightOfSomeValidIndexes() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        assertEquals(4, bmh.getRightIndex(1));
        assertEquals(6, bmh.getRightIndex(2));
        assertEquals(8, bmh.getRightIndex(3));
        assertEquals(10, bmh.getRightIndex(4));
        assertEquals(12, bmh.getRightIndex(5));
        assertEquals(14, bmh.getRightIndex(6));
        assertEquals(248, bmh.getRightIndex(123));
    }

    @Test
    public void shouldCalculateParentOfSomeValidIndexes() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        assertEquals(0, bmh.getParentIndex(1));
        assertEquals(0, bmh.getParentIndex(2));
        assertEquals(1, bmh.getParentIndex(3));
        assertEquals(3, bmh.getParentIndex(8));
        assertEquals(5, bmh.getParentIndex(12));
        assertEquals(10, bmh.getParentIndex(21));
        assertEquals(123, bmh.getParentIndex(247));
        assertEquals(123, bmh.getParentIndex(248));
    }

    @Test
    public void isEmptyshouldBeTrueForANewlyCreatedBinaryMinHeap() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        assertTrue(bmh.isEmpty());
    }

    @Test
    public void addShouldMakeHeapNotEmpty() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        bmh.add(5);
        assertFalse(bmh.isEmpty());
    }

    @Test
    public void addShouldIncreaseNodeCount() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        bmh.add(99);
        assertEquals(1, bmh.getNumberOfNodes());

        bmh.add(-9);
        assertEquals(2, bmh.getNumberOfNodes());
    }

    // getMinimum
    @Test(expected = BinaryHeapException.class)
    public void getMinimumShouldFailForANewlyCreatedBinaryMinHeap() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        assertEquals(0, bmh.getHead().intValue());
    }

    @Test
    public void firstAddShouldBeHeapMinimum() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        bmh.add(5);
        assertEquals(5, bmh.getHead().intValue());
    }

    @Test
    public void getMinimumShouldReturnCorrectMinimumForABinaryMinHeapWithContent() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        bmh.add(5);
        bmh.add(3);
        assertEquals(3, bmh.getHead().intValue());
    }

    /**
     * Answering the data for an index out of bounds is not an easy answer. Possible answers are: 1. you are out of
     * bounds => throw exception; 2. I have no value for that index => return null, like accessing an existing, but
     * empty position;
     * 
     * I adopted solution 1 that I like as uses language constructs to handle corner cases; also the 2nd solution has
     * some appeal, but I prefer the solution from standard Java libs.
     */
    @Test(expected = BinaryHeapOutOfBoundsException.class)
    public void getDataShouldThrowExceptionForNegativeIndices() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        bmh.getData(-1);
    }

    @Test(expected = BinaryHeapOutOfBoundsException.class)
    public void getDataShouldThrowExceptionForBigIndices() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        bmh.getData(BinaryMinHeap.NUMERO_NODI_INIZIALI * 2);
    }

    @Test(expected = BinaryHeapOutOfBoundsException.class)
    public void getDataShouldThrowExceptionForEmptyHeap() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        assertNull(bmh.getData(0));
    }

    @Test
    public void getDataShouldWorkForExistingPositionInHeap() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        bmh.add(5);
        assertEquals(5, bmh.getData(0).intValue());
    }

    @Test
    public void needSwitchShouldBeTrueForUnorderedAndFalseForEqualOrOrdered() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        bmh.data[0] = 5;
        bmh.data[1] = 3;
        bmh.data[2] = 5;
        assertTrue(bmh.needSwitchIn(1, 0));
        assertFalse(bmh.needSwitchIn(0, 1));
        assertFalse(bmh.needSwitchIn(0, 2));
    }

    @Test
    public void siftInShouldReverseUnorderedNodes() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        bmh.data[0] = 5;
        bmh.data[1] = 3;
        bmh.siftIn(1);
        assertEquals(3, bmh.data[0].intValue());
        assertEquals(5, bmh.data[1].intValue());

    }

    @Test
    public void siftInShouldNotReverseOrderedNodes() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        bmh.data[0] = 3;
        bmh.data[1] = 5;
        bmh.siftIn(1);
        assertEquals(3, bmh.data[0].intValue());
        assertEquals(5, bmh.data[1].intValue());
    }

    @Test
    public void siftInShouldNotReverseSameValueNodes() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        bmh.data[0] = 5;
        bmh.data[1] = 5;
        bmh.siftIn(1);
        assertEquals(5, bmh.data[0].intValue());
        assertEquals(5, bmh.data[1].intValue());
    }

    @Test(expected = BinaryHeapException.class)
    public void shouldNotAddNullValue() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        bmh.add(null);
    }

    @Test
    public void addShouldLeaveUpdatedHeadValue() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);

        int count = 0;
        for (int value = (BinaryMinHeap.NUMERO_NODI_INIZIALI + 1); value > -(BinaryMinHeap.NUMERO_NODI_INIZIALI
                + 1); value--) {
            bmh.add(value);
            assertEquals(value, bmh.getHead().intValue());
            assertEquals(++count, bmh.getNumberOfNodes());
        }
    }

    @Test
    public void siftOutShouldReverseUnorderedNodes() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        bmh.data[0] = 8;
        bmh.data[1] = 3;
        bmh.data[2] = 6;
        bmh.data[3] = 5;
        bmh.data[4] = 9;
        bmh.numberOfNodes = 5;
        bmh.siftOut(0);
        assertEquals(3, bmh.data[0].intValue());
        assertEquals(5, bmh.data[1].intValue());
        assertEquals(6, bmh.data[2].intValue());
        assertEquals(8, bmh.data[3].intValue());
        assertEquals(9, bmh.data[4].intValue());

        assertEquals(5, bmh.getNumberOfNodes());
    }

    @Test
    public void getChildMinShouldReturnMinimumValueOfNodes() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        bmh.data[0] = 8;
        bmh.data[1] = 3;
        bmh.data[2] = 6;
        bmh.data[3] = 5;
        bmh.data[4] = 9;
        bmh.numberOfNodes = 5;
        assertEquals(1, bmh.getSwapCandidateChildIndex(0));
        assertEquals(3, bmh.getSwapCandidateChildIndex(1));
        assertEquals(2, bmh.getSwapCandidateChildIndex(2));
        assertEquals(3, bmh.getSwapCandidateChildIndex(3));
        assertEquals(4, bmh.getSwapCandidateChildIndex(4));
        assertEquals(5, bmh.getSwapCandidateChildIndex(5));

    }

    @Test(expected = BinaryHeapException.class)
    public void removeHeadOnEmptyHeapShouldThrowException() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        bmh.removeHead();
    }

    @Test
    public void removeHeadShouldReturnHeadOfHeap() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        bmh.add(5);
        bmh.add(2);
        assertEquals(bmh.getHead().intValue(), bmh.removeHead().intValue());
    }

    @Test
    public void removeHeadOnLastItemShouldLeaveAnEmptyHeap() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        bmh.add(5);
        assertEquals(bmh.getHead().intValue(), bmh.removeHead().intValue());
        assertTrue(bmh.isEmpty());
    }

    @Test
    public void removeHeadShouldReduceSizeByOne() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        bmh.add(15);
        bmh.add(23);
        bmh.add(7);
        bmh.add(99);
        assertEquals(7, bmh.removeHead().intValue());
        assertEquals(3, bmh.getNumberOfNodes());
    }

    @Test
    public void removeHeadShouldLeaveHeapConsistent() {
        BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
        bmh.add(15);
        bmh.add(15);
        bmh.add(14);
        bmh.add(99);

        assertEquals(14, bmh.removeHead().intValue());
        assertEquals(3, bmh.getNumberOfNodes());
        assertFalse(bmh.isEmpty());

        assertEquals(15, bmh.removeHead().intValue());
        assertEquals(2, bmh.getNumberOfNodes());
        assertFalse(bmh.isEmpty());

        assertEquals(15, bmh.removeHead().intValue());
        assertEquals(1, bmh.getNumberOfNodes());
        assertFalse(bmh.isEmpty());

        assertEquals(99, bmh.removeHead().intValue());
        assertEquals(0, bmh.getNumberOfNodes());
        assertTrue(bmh.isEmpty());

    }

}
