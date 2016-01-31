package com.robertozagni.algoritmi.list;

import java.util.Random;

public class ArrayStackTest extends StackTestBase<Stack<Integer>, Integer> {

  private static final int SEED = 123456;
  private Random rnd = new Random(SEED);

  @Override
  protected ArrayStack<Integer> createInstance() {
    return new ArrayStack<Integer>();
  }

  // CHECKSTYLE DISABLE IllegalType FOR 2 LINES - Must return a non primitive type!
  @Override
  protected Integer generateValue() {
    return rnd.nextInt();
  }

}
