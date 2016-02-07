package com.robertozagni.algoritmi.sort;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;

public class MergeSort extends SortBase {

  private static int mergeNum = 0;

  public static <T extends Comparable<T>> void sort(T[] a) {
    int N = a.length;
    T[] aux = (T[]) new Comparable[N];
    System.out.format("Start - %s%n", Arrays.asList(a));
    sort(a, aux, 0, N - 1);
  }

  public static <T extends Comparable<T>> void sort(T[] a, T[] aux, int start, int end) {

    if (end <= start) {
      return;
    }

    int half = start + (end - start) / 2;

    sort(a, aux, start, half);
    sort(a, aux, half + 1, end);
    merge(a, aux, start, half, end);
    System.out.format("Mergr n.%d - %s%n", ++mergeNum, Arrays.asList(a));
  }

  private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int start, int half, int end) {
    assert isSorted(a, start, half);
    assert isSorted(a, half + 1, end);

    for (int i = start; i <= end; i++) {
      aux[i] = a[i];
    }

    int l = start;
    int r = half + 1;
    for (int i = start; i <= end; i++) {
      if (l > half) {
        a[i] = aux[r++];
      } else if (r > end) {
        a[i] = aux[l++];
      } else if (less(aux[r], aux[l])) {
        a[i] = aux[r++];
      } else {
        a[i] = aux[l++];
      }
    }

  }

  public static void main(String[] args) {
    System.out.println("On ECPLISE press CTRL+D.");
    String[] a = StdIn.readAllStrings();
    sort(a);
    assert isSorted(a);
    show(a);
    System.out.println("Array size is " + a.length);
  }
}
