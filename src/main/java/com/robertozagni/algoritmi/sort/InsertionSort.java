package com.robertozagni.algoritmi.sort;

import edu.princeton.cs.algs4.StdIn;

public class InsertionSort extends SortBase {

  public static <T extends Comparable<T>> void sort(T[] a) {
    int N = a.length;
    for (int i = 1; i < N; i++) {
      insert(a, i);
    }
    assert isSorted(a);
  }

  /**
   * Inserts the item in the given position in its place on the preceding part
   * 
   * @param <T> the type of the elements in the array.
   * @param a the array being sorted.
   * @param i the position of the lement to insert in the right position between 0 and i, inclusive.
   */
  private static <T extends Comparable<T>> void insert(T[] a, int pos) {
    for (int i = pos; i > 0; i--) {
      if (less(a[i], a[i - 1])) {
        swapValues(a, i, i - 1);
      } else {
        return;
      }
    }
    assert isSorted(a, 0, pos);

  }

  public static void main(String[] args) {
    String[] a = StdIn.readAllStrings();
    sort(a);
    assert isSorted(a);
    show(a);
    System.out.println("Array size is " + a.length);
  }

}
