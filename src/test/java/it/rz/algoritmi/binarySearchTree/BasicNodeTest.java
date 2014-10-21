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

	/*
	@Ignore @Test
	public void whyDate() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		Date d = df.parse("2009-11-10 23:00:00 UTC");
		System.out.format("Date millis are: %,d | hex: %x", d.getTime(), d.getTime());
	}
	*/
}
