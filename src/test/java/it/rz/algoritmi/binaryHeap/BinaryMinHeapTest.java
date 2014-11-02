package it.rz.algoritmi.binaryHeap;

import static org.junit.Assert.*;
import it.rz.algoritmi.binaryHeap.BinaryMinHeapException;

import org.junit.Ignore;
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
	 * Answering the parent of root is not an easy answer.
	 * Possible answers are:
	 * 1. there is no parent of root => throw exception;
	 * 2. parent of root is root => return root itself;
	 * 3. there is no parent of root => return -1 (or any impossible index);
	 * 
	 * I adopted solution 1 that I like as uses language constructs to handle corner cases;
	 * also the 2nd solution has some appeal, as it turns a special case into a standard one, 
	 * but I am afraid this could easily cause infinite loops;
	 * solution 3 to me is bad.
	 */
	@Test(expected = BinaryMinHeapException.class)
	public void shouldCalculateParentOfRoot() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		
		assertEquals(-12345, bmh.getParentIndex(0));
	}

	/**
	 * Indexes run from 0 (included) upwards, so passing a negative index should throw an exception.
	 */
	@Test(expected = BinaryMinHeapException.class)
	public void shouldCalculateLeftOfNegativeIndexes() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		
		assertEquals(12345, bmh.getLeftIndex(-1));
	}
	
	@Test(expected = BinaryMinHeapException.class)
	public void shouldCalculateRightOfNegativeIndexes() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		
		assertEquals(-12345, bmh.getRightIndex(-5));
	}
	
	@Test(expected = BinaryMinHeapException.class)
	public void shouldCalculateParentOfNegativeIndexes() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		
		assertEquals(-12345, bmh.getParentIndex(-7));
	}
	
	@Test
	public void shouldCalculateLeftOfSomeValidIndexes() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		
		assertEquals(3, bmh.getLeftIndex(1));
		assertEquals(5, bmh.getLeftIndex(2));
		assertEquals(21, bmh.getLeftIndex(10));
		assertEquals(247, bmh.getLeftIndex(123));
	}

	@Test
	public void shouldCalculateRightOfSomeValidIndexes() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		
		assertEquals(4, bmh.getRightIndex(1));
		assertEquals(8, bmh.getRightIndex(3));
		assertEquals(12, bmh.getRightIndex(5));
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

	/*
	@Test
	public void should() {
	}

	@Test
	public void should() {
	}

	@Test
	public void should() {
	}
	*/

}
