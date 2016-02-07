package com.robertozagni.algoritmi.sort;

import edu.princeton.cs.algs4.StdIn;

public class SelectionSort extends SortBase {

  public static <T extends Comparable<T>> void sort(T[] a) {
    int N = a.length;

    for (int i = 0; i < N; i++) {

      int min = i;
      for (int j = i + 1; j < N; j++) {
        if (less(a[j], a[min])) {
          min = j;
        }
      }
      swapValues(a, i, min);
      assert isSorted(a, 0, i);
    }

    assert isSorted(a);
  }

  public static void main(String[] args) {
    String[] a = StdIn.readAllStrings();
    sort(a);
    show(a);
    System.out.println("Array size is " + a.length);
  }

}
