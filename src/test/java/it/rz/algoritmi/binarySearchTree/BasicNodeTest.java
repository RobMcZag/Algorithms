package it.rz.algoritmi.binarySearchTree;

import static org.junit.Assert.*;

import org.junit.Test;

public class BasicNodeTest {

	public BasicNodeTest() {
	}

	@Test(expected = NullPointerException.class)
	public void shouldNOTBeAbleToBuildANullBasicNode() {
		BasicNode<Integer> node = new BasicNode<Integer>(null);
	}

	@Test
	public void shouldBeAbleToBuildABasicNode() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		
		assertNotNull(node);
		assertEquals(5, node.getValue().intValue());
	}

	@Test
	public void shouldAddValueToLeftBranch() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		
		assertNull(node.left);
		assertNull(node.right);
		boolean result = node.add(2);
		assertEquals(true, result);
		assertNotNull(node.left);
		assertNull(node.right);
		assertEquals(2, node.left.getValue().intValue());
	}
	
	@Test
	public void shouldAddToLeftBranch() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		BasicNode<Integer> newNode = new BasicNode<Integer>(2);
		
		assertNull(node.left);
		assertNull(node.right);
		boolean result = node.add(newNode);
		assertEquals(true, result);
		assertNotNull(node.left);
		assertNull(node.right);
		assertEquals(2, node.left.getValue().intValue());
	}
		
	@Test
	public void shouldAddValueToRightBranch() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		
		assertNull(node.left);
		assertNull(node.right);
		boolean result = node.add(9);
		assertEquals(true, result);
		assertNull(node.left);
		assertNotNull(node.right);
		assertEquals(9, node.right.getValue().intValue());
	}
	
	@Test
	public void shouldAddToRightBranch() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		BasicNode<Integer> newNode = new BasicNode<Integer>(9);
		
		assertNull(node.left);
		assertNull(node.right);
		boolean result = node.add(newNode);
		assertNull(node.left);
		assertNotNull(node.right);
		assertEquals(true, result);
		assertEquals(9, node.right.getValue().intValue());
	}

	@Test
	public void shouldNotAddExistingValue() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		
		assertNull(node.left);
		assertNull(node.right);
		boolean result = node.add(5);
		assertEquals(false, result);
		assertNull(node.left);
		assertNull(node.right);
	}
	
	@Test
	public void shouldNotAddEqualValue() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		BasicNode<Integer> newNode = new BasicNode<Integer>(5);
		
		assertNull(node.left);
		assertNull(node.right);
		boolean result = node.add(newNode);
		assertEquals(false, result);
		assertNull(node.right);
		assertNull(node.left);
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldNotAddNullValue() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		
		boolean result = node.add((Integer)null);
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldNotAddNullNOde() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		BasicNode<Integer> newNode = null;
		
		boolean result = node.add(newNode);
	}
	
}
