/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.binarySearchTree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
        tree.add(null);
    }

    @Test
    public void ShouldAddDifferentValues() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        assertNotNull(tree);

        boolean result = tree.add(5);
        assertTrue(result);

        result = tree.add(-5);
        assertTrue(result);

        result = tree.add(1);
        assertTrue(result);

        result = tree.add(0);
        assertTrue(result);

        result = tree.add(-1);
        assertTrue(result);
    }

    @Test
    public void ShouldNotAddTheSameValueTwice() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        assertNotNull(tree);

        boolean result = tree.add(5);
        assertTrue(result);

        result = tree.add(5);
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

    @Test
    public void SizeOfEmptyTreeIsZero() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();

        assertEquals(0, tree.size());
    }

    @Test
    public void SizeOfSingleNodeTreeIsOne() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.add(500);

        assertEquals(1, tree.size());
    }

    @Test
    public void SizeOfTreeWithFiveConnectedNodesIsFive() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.add(50);
        tree.add(200);
        tree.add(550);
        tree.add(150);
        tree.add(950);

        assertEquals(5, tree.size());
    }

    @Test
    public void DupesShouldNotCountTowardSize() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.add(50);
        tree.add(200);
        tree.add(200);
        tree.add(150);
        tree.add(150);

        assertEquals(3, tree.size());
    }

    @Test
    public void shouldListNodeValue() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.add(50);
        tree.add(200);
        tree.add(100);

        List<Integer> list = tree.listValues();
        assertEquals(50, list.get(0).intValue());
        assertEquals(100, list.get(1).intValue());
        assertEquals(200, list.get(2).intValue());
    }

    @Test
    public void shouldListNegativeValues() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.add(0);
        tree.add(-200);
        tree.add(100);
        tree.add(-50);
        tree.add(50);

        List<Integer> list = tree.listValues();
        assertEquals(-200, list.get(0).intValue());
        assertEquals(-50, list.get(1).intValue());
        assertEquals(0, list.get(2).intValue());
        assertEquals(50, list.get(3).intValue());
        assertEquals(100, list.get(4).intValue());
    }

    @Test
    public void shouldListManyValues() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.add(50);
        tree.add(200);
        tree.add(550);
        tree.add(150);
        tree.add(250);
        tree.add(300);
        tree.add(100);
        tree.add(400);
        tree.add(350);
        tree.add(500);

        List<Integer> list = tree.listValues();
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

    @Test
    public void shouldBeEqualIfContainsTheSameValues() {
        BinarySearchTree<Integer> tree1 = new BinarySearchTree<Integer>();
        tree1.add(0);
        tree1.add(-200);
        tree1.add(100);
        tree1.add(-50);
        tree1.add(50);

        BinarySearchTree<Integer> tree2 = new BinarySearchTree<Integer>();
        tree2.add(100);
        tree2.add(-200);
        tree2.add(50);
        tree2.add(0);
        tree2.add(-50);

        assertTrue(tree1.equals(tree2));
    }

}
