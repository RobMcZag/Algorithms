package com.robertozagni.algoritmi.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public abstract class QueueTestBase<T extends Queue<E>, E> {

  private T queue;

  protected abstract T createInstance();

  protected abstract E generateValue();

  @Before
  public void setUp() {
    queue = createInstance();
  }

  @Test
  public void canCreateQueue() throws Exception {
    assertNotNull(queue);
    assertTrue(queue.isEmpty());
    assertEquals(0, queue.size());
  }

  @Test(expected = NoSuchElementException.class)
  public void queueUnderflowFromEmpty() throws Exception {
    queue.dequeue();
    fail("The line above should throw an exception!");
  }

  @Test(expected = NoSuchElementException.class)
  public void queueUnderflowAfterUse() throws Exception {
    queue.enqueue(generateValue());
    queue.dequeue();
    queue.dequeue();
    fail("The line above should throw an exception!");
  }

  @Test
  public void longSequenceInOut() throws Exception {

    final int sizeToTest = 1000;
    @SuppressWarnings("unchecked")
    E[] vals = (E[]) new Object[sizeToTest];

    assertTrue(queue.isEmpty());

    for (int i = 0; i < sizeToTest; i++) {
      vals[i] = generateValue();
      queue.enqueue(vals[i]);
    }
    assertEquals(sizeToTest, queue.size());
    assertFalse(queue.isEmpty());

    for (int i = 0; i < sizeToTest; i++) {
      assertEquals(vals[i], queue.dequeue());
    }
    assertEquals(0, queue.size());
    assertTrue(queue.isEmpty());
  }

  @Test
  public void mixedSequence1() throws Exception {
    /* Perform selected number operations: positive means enqueue, negative dequeue. */
    final int[] commands = {25, 50, -25, 10, -50, 15, -25};

    checkSequence(commands);
  }

  @Test
  public void mixedSequence2() throws Exception {
    /* Perform selected number operations: positive means enqueue, negative dequeue. */
    final int[] commands = {15, -6, 4, -10, 4, -5, -1};

    checkSequence(commands);
  }

  @Test
  public void mixedSequence3() throws Exception {
    /* Perform selected number operations: positive means enqueue, negative dequeue. */
    final int[] commands = {32, -32, 9, -9, 64, -60, 20, -24};

    checkSequence(commands);
  }

  /**
   * @param commands
   */
  private E[] checkSequence(final int[] commands) {

    int maxSize = 0;
    for (int ops : commands) {
      maxSize = ops > 0 ? maxSize + ops : maxSize;
    }

    @SuppressWarnings("unchecked")
    E[] vals = (E[]) new Object[maxSize];

    assertTrue(queue.isEmpty());

    int posW = 0;
    int posR = 0;
    int tot = 0;
    for (int c = 0; c < commands.length; c++) {

      int ops = Math.abs(commands[c]);
      for (int i = 0; i < ops; i++) {

        if (commands[c] > 0) { // Add the required # of values to the queue and save them
          final E val = generateValue();
          vals[posW++] = val;
          queue.enqueue(val);

        } else { // Read the required # of values from the queue and verify them

          final E val = queue.dequeue();
          assertEquals(vals[posR++], val);

        }
      }

      // Keep a count of how many values are in the queue and verify the size.
      tot += commands[c];
      assertEquals(tot, queue.size());
    }

    assertEquals(tot == 0, queue.isEmpty());
    return vals;
  }

  @Test
  public void iteratorReturnsAllElements1() throws Exception {

    final int[] commands = {7, -5, 3}; // Wrote 10, consumed 5
    E[] vals = checkSequence(commands);

    int pos = -(0 - 5 + 0);
    for (E val : queue) {
      assertEquals(vals[pos++], val);
    }
  }

  @Test
  public void iteratorReturnsAllElements2() throws Exception {

    final int[] commands = {32, -32, 9, -9, 64, -60, 20, -24};
    E[] vals = checkSequence(commands);

    int pos = -(0 - 32 + 0 - 9 + 0 - 60 + 0 - 24);
    for (E val : queue) {
      assertEquals(vals[pos++], val);
    }
  }

  @Test
  public void iteratorReturnsAllElements3() throws Exception {

    final int[] commands = {15, -8, 5, -9, 27, -10, 10};
    E[] vals = checkSequence(commands);

    System.out.println(Arrays.asList(vals));
    int pos = -(0 - 8 + 0 - 9 + 0 - 10 + 0);
    for (E val : queue) {
      assertEquals(vals[pos++], val);
    }
  }

  @Test
  public void iteratorReturnsZeroElementsForEmptyQueue() throws Exception {

    assertTrueIsEmptyAndIteratorReturnsZeroElements(queue);

    final int[] commands = {10, -5, -5};
    checkSequence(commands);
    assertTrueIsEmptyAndIteratorReturnsZeroElements(queue);

  }

  /**
   * @param aQueue TODO
   * 
   */
  private void assertTrueIsEmptyAndIteratorReturnsZeroElements(T aQueue) {
    assertTrue(aQueue.isEmpty());
    assertEquals(0, aQueue.size());
    // # iterator for Empty queue returns 0 elements
    int count = 0;
    for (E val : aQueue) {
      count++;
    }
    assertEquals(0, count);
  }
}
