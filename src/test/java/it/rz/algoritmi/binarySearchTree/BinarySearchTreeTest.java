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
}
