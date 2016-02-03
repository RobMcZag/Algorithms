package com.robertozagni.algoritmi.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class DequeTest {

  private static final int SEED = 123456;
  private Random rnd = new Random(SEED);

  private Deque<Integer> intDeque;

  @Before
  public void setUp() {
    intDeque = new DoubleLinkedListDeque<Integer>();
  }

  @Test
  public void canCreateAnObject() throws Exception {
    Deque<Integer> deque = new DoubleLinkedListDeque<Integer>();
    assertNotNull(deque);
  }

  @Test(expected = NullPointerException.class)
  public void noNullAddFirst() throws Exception {
    intDeque.addFirst(null);
  }

  @Test(expected = NullPointerException.class)
  public void noNullAddLast() throws Exception {
    intDeque.addLast(null);
  }

  @Test(expected = NoSuchElementException.class)
  public void noMoreFromFirst() throws Exception {
    intDeque.removeFirst();
  }

  @Test(expected = NoSuchElementException.class)
  public void noMoreFromLast() throws Exception {
    intDeque.removeLast();
  }

  @Test(expected = NoSuchElementException.class)
  public void nothingFromIteratorOnEmptyDeque() throws Exception {
    Iterator<Integer> it = intDeque.iterator();
    assertFalse(it.hasNext());
    it.next();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void noRemoveMethodONIterator() throws Exception {
    Iterator<Integer> it = intDeque.iterator();
    it.remove();
  }

  @Test
  public void testIsEmpty() {

    assertTrue(intDeque.isEmpty());
    intDeque.addFirst(10);
    assertFalse(intDeque.isEmpty());
    intDeque.removeFirst();
    assertTrue(intDeque.isEmpty());
    intDeque.addLast(10);
    assertFalse(intDeque.isEmpty());
    intDeque.removeLast();
    assertTrue(intDeque.isEmpty());

  }

  @Test
  public void testSize() {
    assertEquals(0, intDeque.size());

    intDeque.addFirst(10);
    assertEquals(1, intDeque.size());

    intDeque.addLast(-10);
    assertEquals(2, intDeque.size());

    for (int i = 1; i <= 100; i++) {
      intDeque.addFirst(i);
      intDeque.addLast(-i);
      assertEquals(2 + 2 * i, intDeque.size());
    }

  }

  @Test
  public void testAddFirstRemoveFirst() {
    final int numtests = 100;
    int[] vals = new int[numtests];
    for (int i = 0; i < numtests; i++) {
      vals[i] = rnd.nextInt();
      intDeque.addFirst(vals[i]);
    }
    for (int i = numtests - 1; i <= 0; i++) {
      assertEquals(vals[i], intDeque.removeFirst().intValue());
    }
  }

  @Test
  public void testAddFirstRemoveLast() {
    final int numtests = 100;
    int[] vals = new int[numtests];
    for (int i = 0; i < numtests; i++) {
      vals[i] = rnd.nextInt();
      intDeque.addFirst(vals[i]);
    }
    for (int i = 0; i < numtests; i++) {
      assertEquals(vals[i], intDeque.removeLast().intValue());
    }
  }

  @Test
  public void testAddLastRemoveFirst() {
    final int numtests = 100;
    int[] vals = new int[numtests];
    for (int i = 0; i < numtests; i++) {
      vals[i] = rnd.nextInt();
      intDeque.addLast(vals[i]);
    }
    for (int i = 0; i < numtests; i++) {
      assertEquals(vals[i], intDeque.removeFirst().intValue());
    }
  }

  @Test
  public void testAddLastRemoveLast() {
    final int numtests = 100;
    int[] vals = new int[numtests];
    for (int i = 0; i < numtests; i++) {
      vals[i] = rnd.nextInt();
      intDeque.addLast(vals[i]);
    }
    for (int i = numtests - 1; i <= 0; i++) {
      assertEquals(vals[i], intDeque.removeFirst().intValue());
    }
  }

  @Test
  public void testFillEmptyTreeTimes() {
    final int loops = 10;
    final int numtests = 100;
    int[] vals = new int[numtests];
    for (int loop = 0; loop < loops; loop++) {
      for (int i = 0; i < numtests; i++) {
        vals[i] = rnd.nextInt();
        intDeque.addLast(vals[i]);
      }
      for (int i = 0; i < numtests; i++) {
        assertEquals(vals[i], intDeque.removeFirst().intValue());
      }
    }

  }

  @Test
  public void testIterator() {
    final int numtests = 10;
    int[] vals = new int[numtests];
    for (int i = 0; i < numtests; i++) {
      vals[i] = rnd.nextInt();
      intDeque.addFirst(vals[i]);
    }
    int i = numtests - 1;
    for (int val : intDeque) {
      int j = numtests - 1;
      assertEquals(vals[i--], val);
      for (int val2 : intDeque) {
        assertEquals(vals[j--], val2);
      }
    }
  }

}
