package com.robertozagni.algoritmi.sort;

import java.util.Arrays;

import org.junit.Test;

public class QuickSortTest {

  @Test
  public final void testPartition() {
    String[] a = {"A", "A", "B", "B", "B", "A", "A", "B", "B", "B", "B", "B"};
    // String[] a = {"B", "B", "B", "A", "B", "B", "B", "B", "B"};
    System.out.println(Arrays.asList(a));

    int p = QuickSort.partition(a, 0, a.length - 1);
    System.out.println(Arrays.asList(a) + " | p = " + p);

    QuickSort.sort(a);
    System.out.println(Arrays.asList(a));

  }

  @Test
  public final void testPartition2() {
    String[] a = {"A", "A", "B", "B", "B", "A", "A", "B", "B", "B", "B", "B"};
    // String[] a = {"B", "B", "B", "A", "A", "B", "B", "B", "B", "B"};
    System.out.println(Arrays.asList(a));

    int p2 = QuickSort.partition2(a, 0, a.length - 1);
    System.out.println(Arrays.asList(a) + " | p2 = " + p2);

    QuickSort.sort(a);
    System.out.println(Arrays.asList(a));

  }

}
