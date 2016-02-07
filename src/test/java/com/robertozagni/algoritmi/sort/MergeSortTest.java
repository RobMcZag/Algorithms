package com.robertozagni.algoritmi.sort;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class MergeSortTest {

  private static final int SEED = 123456;
  private Random rnd = new Random(SEED);

  // CHECKSTYLE DISABLE IllegalType FOR 1 LINES
  private Integer[] ints = null;

  @Before
  public void setUp() throws Exception {
    int N = 100;
    ints = new Integer[N];

    randomFill(ints);

  }

  // CHECKSTYLE DISABLE IllegalType FOR 1 LINES
  private void randomFill(Integer[] a) {
    for (int i = 0; i < a.length; i++) {
      a[i] = rnd.nextInt();
    }
  }

  @Test
  public final void sortIsWorking() {
    assertFalse(MergeSort.isSorted(ints));
    MergeSort.sort(ints);
    assertTrue(MergeSort.isSorted(ints));
  }

  @Test
  public final void sortTimed() {
    int N = 1000;
    int T = 20;
    int I = 1000;

    // CHECKSTYLE DISABLE IllegalType FOR 1 LINES
    Integer[] orig = new Integer[N];
    randomFill(orig);

    long total = 0;
    for (int test = 0; test < T; test++) {

      long startTime = System.nanoTime();
      for (int iter = 1; iter <= I; iter++) {
        // CHECKSTYLE DISABLE IllegalType FOR 1 LINES
        Integer[] clone = orig.clone();
        MergeSort.sort(clone);
      }
      long estimatedTime = System.nanoTime() - startTime;
      if (test > 0) {
        total += estimatedTime;
      }

      System.out.format("Standard - Elapsed time for %d iterations: %,d ns %n", I, estimatedTime);

    }
    System.out.format("Standard - Average time on %d tests: %,d ns%n", T, total / T);

  }

}
