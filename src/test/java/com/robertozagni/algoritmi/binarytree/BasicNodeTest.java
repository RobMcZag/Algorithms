/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.binarytree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

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

    node.add((Integer) null);
  }

  @Test(expected = NullPointerException.class)
  public void shouldNotAddNullNOde() {
    BasicNode<Integer> node = new BasicNode<Integer>(5);
    BasicNode<Integer> newNode = null;

    node.add(newNode);
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

  @Test
  public void shouldListAllNodeValues() {
    BasicNode<Integer> node = new BasicNode<Integer>(100);

    List<Integer> list = node.listValues();
    assertEquals(100, list.get(0).intValue());
  }

  @Test
  public void shouldListNodeValue() {
    BasicNode<Integer> node = new BasicNode<Integer>(100);
    node.add(50);
    node.add(200);

    List<Integer> list = node.listValues();
    assertEquals(50, list.get(0).intValue());
    assertEquals(100, list.get(1).intValue());
    assertEquals(200, list.get(2).intValue());
  }

  @Test
  public void shouldListNegativeValues() {
    BasicNode<Integer> node = new BasicNode<Integer>(100);
    node.add(0);
    node.add(-200);
    node.add(-50);
    node.add(50);

    List<Integer> list = node.listValues();
    assertEquals(-200, list.get(0).intValue());
    assertEquals(-50, list.get(1).intValue());
    assertEquals(0, list.get(2).intValue());
    assertEquals(50, list.get(3).intValue());
    assertEquals(100, list.get(4).intValue());
  }

  @Test
  public void shouldListManyValues() {
    BasicNode<Integer> node = new BasicNode<Integer>(100);
    node.add(50);
    node.add(200);
    node.add(550);
    node.add(150);
    node.add(250);
    node.add(300);
    node.add(400);
    node.add(350);
    node.add(500);

    List<Integer> list = node.listValues();
    assertEquals(50, list.get(0).intValue());
    assertEquals(100, list.get(1).intValue());
    assertEquals(150, list.get(2).intValue());
    assertEquals(200, list.get(3).intValue());
    assertEquals(250, list.get(4).intValue());
    assertEquals(300, list.get(5).intValue());
    assertEquals(350, list.get(6).intValue());
    assertEquals(400, list.get(7).intValue());
    assertEquals(500, list.get(8).intValue());
    assertEquals(550, list.get(9).intValue());
  }

  @Test(expected = NullPointerException.class)
  public void sizeOfEmptyNodeIsZero() {
    new BasicNode<Integer>(null);
  }

  @Test
  public void sizeOfSingleNodeIsOne() {
    BasicNode<Integer> node = new BasicNode<Integer>(50);

    assertEquals(1, node.size());
  }

  @Test
  public void sizeOfFiveConnectedNodesIsFive() {
    BasicNode<Integer> node = new BasicNode<Integer>(100);
    node.add(50);
    node.add(200);
    node.add(550);
    node.add(150);

    assertEquals(5, node.size());
  }

  @Test
  public void dupesShouldNotCountTowardSize() {
    BasicNode<Integer> node = new BasicNode<Integer>(100);
    node.add(50);
    node.add(100);
    node.add(50);
    node.add(150);

    assertEquals(3, node.size());
  }

  @Test
  public void shouldBeEqualIfContainsTheSameValues() {
    BasicNode<Integer> node1 = new BasicNode<Integer>(100);
    node1.add(0);
    node1.add(-200);
    node1.add(-50);
    node1.add(50);

    BasicNode<Integer> node2 = new BasicNode<Integer>(50);
    node2.add(100);
    node2.add(-200);
    node2.add(0);
    node2.add(-50);

    assertTrue(node1.equals(node2));
  }

}
