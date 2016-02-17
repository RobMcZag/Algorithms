package com.robertozagni.algoritmi.sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort extends SortBase {

  public static <T extends Comparable<T>> void sort(T[] a) {

    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);

    assert isSorted(a);

  }

  public static <T extends Comparable<T>> void sort(T[] a, int start, int end) {

    if (start >= end) { // size 1 (or less) is already sorted
      return;
    }

    int p = partition(a, start, end);

    sort(a, start, p - 1);
    sort(a, p + 1, end);

    assert isSorted(a, start, end);

  }

  public static <T extends Comparable<T>> int partition2(T[] a, int start, int end) {
    T P = a[start];

    int L = start + 1; // next pos for vals < P
    for (int R = L; R <= end; R++) {
      if (less(a[R], P)) {
        swapValues(a, L, R);
        L++;
      }
    }
    swapValues(a, start, --L);

    return L;
  }

  public static <T extends Comparable<T>> int partition(T[] a, int start, int end) {

    T pivot = a[start];

    int L = start;
    int R = end + 1;

    // System.out.format("Start partitioning - s = %3d, e = %3d | ", start, end);
    // show(a);

    while (true) {

      while (less(a[++L], pivot)) {
        if (L == end) {
          break;
        }
      }

      while (less(pivot, a[--R])) {
        if (R == start) {
          break;
        }
      }

      if (L >= R) {
        break;
      }

      swapValues(a, L, R);

    }

    swapValues(a, R, start);

    // System.out.format("End partitioning - L = %3d, R = %3d | ", L, R);
    // show(a);

    return R;
  }

  public static void main(String[] args) {
    System.out.println("On ECPLISE press CTRL+D.");
    String[] a = StdIn.readAllStrings();
    sort(a);
    show(a);
    System.out.println("Array size is " + a.length);
    assert isSorted(a);
  }

}
