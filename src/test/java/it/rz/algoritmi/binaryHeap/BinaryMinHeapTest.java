package it.rz.algoritmi.binaryHeap;

import static org.junit.Assert.*;
import it.rz.algoritmi.binaryHeap.BinaryHeapException;

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
	@Test(expected = BinaryHeapException.class)
	public void shouldCalculateParentOfRoot() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		
		assertEquals(-12345, bmh.getParentIndex(0));
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


	// isEmpty
	@Test
	public void isEmptyshouldBeTrueForANewlyCreatedBinaryMinHeap() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		
		assertTrue(bmh.isEmpty());
	}

	@Test
	public void addShouldMakeHeapNotEmpty() {
//		public void isEmptyshouldBeFalseForABinaryMinHeapWithContent() {
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
	@Test(expected=BinaryHeapException.class)
	public void getMinimumShouldFailForANewlyCreatedBinaryMinHeap() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		
		assertEquals(0, bmh.getMinimum().intValue());
	}

	@Test
	public void firstAddShouldBeHeapMinimum() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		
		bmh.add(5);
		assertEquals(5, bmh.getMinimum().intValue());
	}
	
	@Test
	public void getMinimumShouldReturnCorrectMinimumForABinaryMinHeapWithContent() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		
		bmh.add(5);
		bmh.add(3);
		assertEquals(3, bmh.getMinimum().intValue());
	}
		
		/**
		 * Answering the data for an index out of bounds is not an easy answer.
		 * Possible answers are:
		 * 1. you are out of bounds => throw exception;
		 * 2. I have no value for that index => return null, like accessing an existing, but empty position;
		 * 
		 * I adopted solution 1 that I like as uses language constructs to handle corner cases;
		 * also the 2nd solution has some appeal, but I prefer the solution from standard Java libs.
		 */
	@Test(expected=BinaryHeapOutOfBoundsException.class)
	public void getDataShouldThrowExceptionForNegativeIndices() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		bmh.getData(-1);
	}

	@Test(expected=BinaryHeapOutOfBoundsException.class)
	public void getDataShouldThrowExceptionForBigIndices() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		bmh.getData(BinaryMinHeap.NUMERO_NODI_INIZIALI * 2);
	}

	@Test(expected=BinaryHeapOutOfBoundsException.class)
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
		assertTrue(bmh.needSwitch(3,  5));
		assertFalse(bmh.needSwitch(5,  3));
		assertFalse(bmh.needSwitch(5,  5));
	}

	
	@Test
	public void siftShouldReverseUnorderedNodes() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		bmh.data[0] = 5;
		bmh.data[1] = 3;
		bmh.sift(1);
		assertEquals(3, bmh.data[0].intValue());
		assertEquals(5, bmh.data[1].intValue());
		
	}
	
	@Test
	public void siftShouldNotReverseOrderedNodes() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		bmh.data[0] = 3;
		bmh.data[1] = 5;
		bmh.sift(1);
		assertEquals(3, bmh.data[0].intValue());
		assertEquals(5, bmh.data[1].intValue());
	}
	
	@Test
	public void siftShouldNotReverseSameValueNodes() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		bmh.data[0] = 5;
		bmh.data[1] = 5;
		bmh.sift(1);
		assertEquals(5, bmh.data[0].intValue());
		assertEquals(5, bmh.data[1].intValue());
	}
	
	@Test(expected=BinaryHeapException.class)
	public void shouldNotAddNullValue() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		bmh.add(null);
	}
	
	@Test
	public void addShouldLeaveUpdatedHeadValue() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		
		int count = 0;
		for (int value = (BinaryMinHeap.NUMERO_NODI_INIZIALI+1) ; value > - (BinaryMinHeap.NUMERO_NODI_INIZIALI+1) ; value--) {
//			System.out.println(bmh.toString());
			bmh.add(value);
			assertEquals(value, bmh.getMinimum().intValue());
			assertEquals(++count, bmh.getNumberOfNodes());
		}
//		assertEquals("6 nodes : { -2, 0, -1, 3, 1, 2, null, null}", bmh.toString());
//		System.out.println(bmh.toString());
		
	}

	/*

	@Ignore @Test
	public void should() {
			// TODO write test
			fail("HEi! Implement me!");
	}

	@Ignore @Test
	public void should() {
			// TODO write test
			fail("HEi! Implement me!");
	}
	*/

}
