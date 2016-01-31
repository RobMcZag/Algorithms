package com.robertozagni.algoritmi.list;

import java.util.Random;

public class LinkedListQueueTest extends QueueTestBase<Queue<Integer>, Integer> {

  private static final int SEED = 123456;
  private Random rnd = new Random(SEED);

  @Override
  protected LinkedListQueue<Integer> createInstance() {
    return new LinkedListQueue<Integer>();
  }

  // CHECKSTYLE DISABLE IllegalType FOR 2 LINES - Must return a non primitive type!
  @Override
  protected Integer generateValue() {
    return rnd.nextInt();
  }

}
