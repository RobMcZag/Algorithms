package com.robertozagni.algoritmi.list;

import java.util.Random;

public class LinkedListStackTest extends StackTestBase<Stack<Integer>, Integer> {

  private static final int SEED = 123456;
  private Random rnd = new Random(SEED);

  @Override
  protected LinkedListStack<Integer> createInstance() {
    return new LinkedListStack<Integer>();
  }

  // CHECKSTYLE DISABLE IllegalType FOR 2 LINES - Must return a non primitive type!
  @Override
  protected Integer generateValue() {
    return rnd.nextInt();
  }

}
