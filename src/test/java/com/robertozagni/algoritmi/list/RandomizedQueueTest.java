package com.robertozagni.algoritmi.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class RandomizedQueueTest {

  private static final int NUM_TESTS = 100;

  private static final int SEED = 123456;
  private Random rnd = new Random(SEED);

  private RandomizedQueue<Integer> intRQ;

  @Before
  public void setUp() {
    intRQ = new RandomizedQueue<Integer>();
  }

  @Test(expected = NullPointerException.class)
  public final void npeOnAddNull() {
    intRQ.enqueue(null);
  }

  @Test(expected = NoSuchElementException.class)
  public final void nseeOnSampleEmpty() {
    assertTrue(intRQ.isEmpty());
    intRQ.sample();
  }

  @Test(expected = NoSuchElementException.class)
  public final void nseeOnDequeueEmpty() {
    assertTrue(intRQ.isEmpty());
    intRQ.dequeue();
  }

  @Test(expected = UnsupportedOperationException.class)
  public final void uoeOnIteratorRemove() {
    intRQ.iterator().remove();
  }

  @Test(expected = NoSuchElementException.class)
  public final void nseeOnIteratorNextWhenEmpty() {
    intRQ.iterator().next();
  }

  @Test(expected = NoSuchElementException.class)
  public final void nseeOnIteratorNextWhenFinishedElements() {
    intRQ.enqueue(rnd.nextInt());
    assertEquals(1, intRQ.size());

    Iterator<Integer> it = intRQ.iterator();
    assertTrue(it.hasNext());
    assertNotNull(it.next());

    assertFalse(it.hasNext());
    it.next();
  }

  @Test
  public void canCreateAnEmptyQueue() throws Exception {
    RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
    assertNotNull(q);
    assertTrue(q.isEmpty());
    assertEquals(0, q.size());

    Iterator<Integer> it = q.iterator();
    assertNotNull(it);
    assertFalse(it.hasNext());
  }

  @Test
  public final void testIsEmpty() {
    assertTrue(intRQ.isEmpty());

    intRQ.enqueue(rnd.nextInt());
    intRQ.enqueue(rnd.nextInt());
    intRQ.enqueue(rnd.nextInt());
    assertFalse(intRQ.isEmpty());

    intRQ.dequeue();
    intRQ.dequeue();
    intRQ.dequeue();
    assertTrue(intRQ.isEmpty());

    intRQ.enqueue(rnd.nextInt());
    assertFalse(intRQ.isEmpty());

    intRQ.dequeue();
    assertTrue(intRQ.isEmpty());

    intRQ.enqueue(rnd.nextInt());
    assertFalse(intRQ.isEmpty());

    intRQ.dequeue();
    assertTrue(intRQ.isEmpty());
  }

  @Test
  public final void testEnqueueDequeue() {
    int[] vals = randomEnqueue(NUM_TESTS);

    for (int i = 0; i < NUM_TESTS; i++) {
      final int val = intRQ.dequeue();
      final int pos = Arrays.binarySearch(vals, val);
      assertTrue(pos >= 0);
    }
    assertTrue(intRQ.isEmpty());
  }

  @Test
  public final void multipleEnqueueDequeue() {
    testEnqueueDequeue();
    testEnqueueDequeue();
    testEnqueueDequeue();
    testEnqueueDequeue();
  }

  @Test
  public final void testSample() {
    int[] vals = randomEnqueue(NUM_TESTS);

    for (int i = 0; i < NUM_TESTS; i++) {
      final int val = intRQ.sample();
      assertTrue(Arrays.binarySearch(vals, val) >= 0);
    }
    assertFalse(intRQ.isEmpty());
    assertEquals(NUM_TESTS, intRQ.size());
  }

  @Test
  public final void testIterator() {
    int[] vals = randomEnqueue(NUM_TESTS);
    assertFalse(intRQ.isEmpty());

    final Iterator<Integer> it = intRQ.iterator();
    assertNotNull(it);
    while (it.hasNext()) {
      int val = it.next();
      assertTrue(Arrays.binarySearch(vals, val) >= 0);
    }

    for (int val : intRQ) {
      assertTrue(Arrays.binarySearch(vals, val) >= 0);
    }
  }

  /**
   * Enqueues the required number of random numbers in the global queue and sorts the val[].
   */
  private int[] randomEnqueue(int n) {
    int[] vals = new int[NUM_TESTS];

    assertTrue(intRQ.isEmpty());
    for (int i = 0; i < n; i++) {
      assertEquals(i, intRQ.size());
      vals[i] = rnd.nextInt();
      intRQ.enqueue(vals[i]);
    }
    assertEquals(NUM_TESTS, intRQ.size());
    Arrays.sort(vals);

    return vals;
  }

}
