package com.robertozagni.algoritmi.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public abstract class StackTestBase<T extends Stack<E>, E> {

  private T stack;

  protected abstract T createInstance();

  protected abstract E generateValue();

  @Before
  public void setUp() {
    stack = createInstance();
  }

  @Test
  public void canCreateStack() throws Exception {
    assertNotNull(stack);
  }

  @Test(expected = NoSuchElementException.class)
  public void stackUnderflowFromEmpty() throws Exception {
    stack.pop();
  }

  @Test(expected = NoSuchElementException.class)
  public void stackUnderflowAfterUse() throws Exception {
    stack.push(generateValue());
    stack.pop();
    stack.pop();
    fail("The line above should throw an exception!");
  }

  @Test
  public void pushPopWorksFine() throws Exception {

    @SuppressWarnings("unchecked")
    E[] vals = (E[]) new Object[8];
    for (int i = 0; i < vals.length; i++) {
      vals[i] = generateValue();
    }

    stack.push(vals[0]);
    assertEquals(vals[0], stack.pop());

    stack.push(vals[1]);
    assertEquals(vals[1], stack.pop());

    stack.push(vals[2]);
    stack.push(vals[3]);
    stack.push(vals[4]);
    assertEquals(vals[4], stack.pop());
    stack.push(vals[5]);
    assertEquals(vals[5], stack.pop());
    assertEquals(vals[3], stack.pop());
    assertEquals(vals[2], stack.pop());
  }

  @Test
  public void isEmpty() throws Exception {
    assertTrue(stack.isEmpty());

    stack.push(generateValue());
    assertFalse(stack.isEmpty());

    stack.push(generateValue());
    assertFalse(stack.isEmpty());

    stack.pop();
    assertFalse(stack.isEmpty());

    stack.pop();
    assertTrue(stack.isEmpty());
  }

  @Test
  public void canPushAndPopNull() throws Exception {

    stack.push(null);
    assertFalse(stack.isEmpty());
    assertNull(stack.pop());

    assertTrue(stack.isEmpty());

    stack.push(generateValue());
    stack.push(null);
    assertNull(stack.pop());
    assertFalse(stack.isEmpty());

  }

  @Test
  public void sizeIsCorrect() throws Exception {

    assertEquals(0, stack.size());

    stack.push(generateValue());
    assertEquals(1, stack.size());

    stack.pop();
    assertEquals(0, stack.size());

    final int sizeToTest = 50;
    for (int i = 0; i < sizeToTest; i++) {
      stack.push(generateValue());
    }
    assertEquals(sizeToTest, stack.size());
    assertFalse(stack.isEmpty());

    for (int i = 0; i < sizeToTest; i++) {
      stack.pop();
    }
    assertEquals(0, stack.size());
    assertTrue(stack.isEmpty());
  }

  @Test
  public void longPushPop() throws Exception {

    final int sizeToTest = 1000;
    @SuppressWarnings("unchecked")
    E[] vals = (E[]) new Object[sizeToTest];

    for (int i = 0; i < sizeToTest; i++) {
      vals[i] = generateValue();
      stack.push(vals[i]);
    }
    assertEquals(sizeToTest, stack.size());
    assertFalse(stack.isEmpty());

    for (int i = sizeToTest - 1; i >= 0; i--) {
      assertEquals(vals[i], stack.pop());
    }
    assertEquals(0, stack.size());
    assertTrue(stack.isEmpty());
  }
}
