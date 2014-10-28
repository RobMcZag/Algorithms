package it.rz.algoritmi.binarySearchTree;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class BinarySearchTreeTest {

	public BinarySearchTreeTest() {
	}

	@Test
	public void shouldBuildAnEmptyBinarySearchTree() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		
		assertNotNull(tree);
		assertTrue(tree.isEmpty());
	}

	@Test
	public void aNewTreeShouldBeEmpty() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		
		assertNotNull(tree);
		assertTrue(tree.isEmpty());
	}

	@Test
	public void aTreeWithAnodeShouldNotBeEmpty() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();

		boolean result = tree.add(5);
		assertNotNull(tree);
		assertTrue(result);
		assertFalse(tree.isEmpty());
	}
	
	@Test(expected = NullPointerException.class)
	public void ShouldNotAddNull() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();

		assertNotNull(tree);
		boolean result = tree.add(null);
	}
	
	@Test
	public void ShouldAddDifferentValues() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		assertNotNull(tree);

		boolean result = tree.add(5);
		assertTrue(result);
		
		result= tree.add(-5);
		assertTrue(result);
		
		result= tree.add(1);
		assertTrue(result);
		
		result= tree.add(0);
		assertTrue(result);
		
		result= tree.add(-1);
		assertTrue(result);
	}

	@Test
	public void ShouldNotAddTheSameValueTwice() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		assertNotNull(tree);

		boolean result = tree.add(5);
		assertTrue(result);
		
		result= tree.add(5);
		assertFalse(result);
	}

	@Test
	public void ShouldContainAnAddedValue() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		assertNotNull(tree);
		boolean result = tree.add(5);
		assertTrue(result);
		
		assertTrue(tree.contains(5));
	}
	
	@Test
	public void ShouldNotContainAnUnaddedValue() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		assertNotNull(tree);
		boolean result = tree.add(5);
		assertTrue(result);
		
		assertFalse(tree.contains(7));
		assertFalse(tree.contains(2));
		assertFalse(tree.contains(0));
		assertFalse(tree.contains(-2));
	}
	
	@Test
	public void ShouldBeOKRemovingAnUnaddedValue() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		boolean result = tree.add(5);
		
		result = tree.remove(2);
		assertTrue(result);
		assertFalse(tree.contains(2));
		assertTrue(tree.contains(5));
	}

	@Test
	public void ShouldBeOKRemovingAnAddedValue() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		boolean result = tree.add(5);
		
		result = tree.remove(5);
		assertTrue(result);
		assertFalse(tree.contains(5));
	}

	@Test
	public void ShouldBeOKRemovingAnAddedValueInInternalNode() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.add(5);
		tree.add(9);
		tree.add(7);
		tree.add(8);
		
		assertTrue(tree.contains(7));
		boolean result = tree.remove(7);
		assertTrue(result);
		assertFalse(tree.contains(7));
	}

	@Test
	public void ShouldBeOKRemovingNull() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.add(5);
		tree.add(9);
		tree.add(7);
		tree.add(8);
		
		boolean result = tree.remove(null);
		assertTrue(result);
		assertFalse(tree.contains(null));
	}

}
