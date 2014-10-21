package it.rz.algoritmi.binarySearchTree;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Ignore;
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
	
	@Test
	public void shouldFindEqualToNode() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		
		assertNull(node.left);
		assertNull(node.right);
		boolean result = node.contains(5);
		assertNull(node.left);
		assertNull(node.right);
		assertEquals(true, result);
		assertEquals(5, node.getValue().intValue());
	}

	@Test
	public void shouldFindAnExistingValueGreaterThanRoot() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		BasicNode<Integer> newNode = new BasicNode<Integer>(9);
		boolean result = node.add(newNode);
		assertEquals(true, result);

		result = node.contains(9);
		assertNull(node.left);
		assertNotNull(node.right);
		assertEquals(true, result);
		
	}

	@Test
	public void shouldNotFindANonExistingValueGreaterThanRoot() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		BasicNode<Integer> newNode = new BasicNode<Integer>(9);
		boolean result = node.add(newNode);
		assertEquals(true, result);

		result = node.contains(7);
		assertNull(node.left);
		assertNotNull(node.right);
		assertEquals(false, result);
		
	}

	@Test
	public void shouldFindAnExistingValueLesserThanRoot() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		BasicNode<Integer> newNode = new BasicNode<Integer>(2);
		boolean result = node.add(newNode);
		assertEquals(true, result);

		result = node.contains(2);
		assertNotNull(node.left);
		assertNull(node.right);
		assertEquals(true, result);
		
	}

	@Test
	public void shouldNotFindANonExistingValueLesserThanRoot() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		BasicNode<Integer> newNode = new BasicNode<Integer>(2);
		boolean result = node.add(newNode);
		assertEquals(true, result);

		result = node.contains(3);
		assertNotNull(node.left);
		assertNull(node.right);
		assertEquals(false, result);
		
	}
	
	@Test
	public void shouldFindNodeWithEqualToNode() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		BasicNode<Integer> nodeToSearch = new BasicNode<Integer>(5);
		
		Node<Integer> resultNode = node.search(nodeToSearch);
		assertEquals(nodeToSearch.getValue(), resultNode.getValue());
	}

	@Test
	public void shouldFindNodeWithValueGreaterThanRoot() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		boolean result = node.add(new BasicNode<Integer>(9));
		assertEquals(true, result);

		BasicNode<Integer> nodeToSearch = new BasicNode<Integer>(9);
		Node<Integer> resultNode = node.search(nodeToSearch);
		assertEquals(nodeToSearch.getValue(), resultNode.getValue());
		
	}

	@Test
	public void shouldNotFindANonExistingNodeWithValueGreaterThanRoot() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		boolean result = node.add(new BasicNode<Integer>(15));
		assertEquals(true, result);

		BasicNode<Integer> nodeToSearch = new BasicNode<Integer>(9);
		Node<Integer> resultNode = node.search(nodeToSearch);
		assertNull(resultNode);
		
	}

	@Test
	public void shouldFindNodeWithValueLesserThanRoot() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		boolean result = node.add(new BasicNode<Integer>(2));
		assertEquals(true, result);

		BasicNode<Integer> nodeToSearch = new BasicNode<Integer>(2);
		Node<Integer> resultNode = node.search(nodeToSearch);
		assertEquals(nodeToSearch.getValue(), resultNode.getValue());
		
	}

	@Test
	public void shouldNotFindANonExistingNodeWithValueLesserThanRoot() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		boolean result = node.add(new BasicNode<Integer>(2));
		assertEquals(true, result);

		BasicNode<Integer> nodeToSearch = new BasicNode<Integer>(3);
		Node<Integer> resultNode = node.search(nodeToSearch);
		assertNull(resultNode);
		
	}

	@Test
	public void shouldRemoveValueFromSingleNode() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		
		Node result = node.remove(5);
		assertNull(result);
	}

	@Test
	public void shouldRemoveValueFromNodeWithLeftOnly() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		node.add(2);
		
		Node<Integer> result = node.remove(5);
		assertNotNull(result);
		assertEquals(2, result.getValue().intValue());
	}

	@Test
	public void shouldRemoveValueFromNodeWithRightOnly() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		node.add(9);
		
		Node<Integer> result = node.remove(5);
		assertNotNull(result);
		assertEquals(9, result.getValue().intValue());
	}
	
	@Test
	public void shouldRemoveValueFromNodeWithLeftAndRight() {
		BasicNode<Integer> node = new BasicNode<Integer>(5);
		node.add(9);
		node.add(2);
		
		Node<Integer> result = node.remove(5);
		assertNotNull(result);
		assertTrue(result.contains(9));
		assertTrue(result.contains(2));
	}
	
	@Test
	public void shouldNotRemoveMissingValue() {
		BasicNode<Integer> node = new BasicNode<Integer>(100);
		node.add(50);
		node.add(200);
		node.add(150);
		node.add(300);
		node.add(250);
		node.add(400);
		node.add(350);
		node.add(500);
		node.add(550);
		
		Node<Integer> result = node.remove(333);
		assertNotNull(result);
		assertTrue(result.contains(100));
		assertTrue(result.contains(50));
		assertTrue(result.contains(200));
		assertTrue(result.contains(150));
		assertTrue(result.contains(300));
		assertTrue(result.contains(250));
		assertTrue(result.contains(400));
		assertTrue(result.contains(350));
		assertTrue(result.contains(500));
		assertTrue(result.contains(550));
	}
	
	@Test
	public void shouldRemoveValueInsideManyLeaves() {
		BasicNode<Integer> node = new BasicNode<Integer>(100);
		node.add(50);
		node.add(200);
		node.add(150);
		node.add(300);
		node.add(250);
		node.add(400);
		node.add(350);
		node.add(500);
		node.add(550);
		assertTrue(node.contains(300));
		
		Node<Integer> result = node.remove(300);
		assertNotNull(result);
		assertTrue(result.contains(100));
		assertTrue(result.contains(50));
		assertTrue(result.contains(200));
		assertTrue(result.contains(150));
		assertFalse(result.contains(300));
		assertTrue(result.contains(250));
		assertTrue(result.contains(400));
		assertTrue(result.contains(350));
		assertTrue(result.contains(500));
		assertTrue(result.contains(550));
	}
	
	
}
