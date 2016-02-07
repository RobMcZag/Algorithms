package com.robertozagni.algoritmi.sort;

public abstract class SortBase {

  /**
   * Returns true iff <code>v < w</code>, i.e. <b>v is strictly less than w</b>.<br>
   * Attention that <code>true </code> means v is greater <b>or equal</b> than w.
   * 
   * @param v The "smaller" argument, is the lesser of the two when returning true.
   * @param w The "bigger" argument, is the bigger of the two when returning true.
   * @param w
   * @return
   */
  protected static <T extends Comparable<T>> boolean less(T v, T w) {
    return v.compareTo(w) < 0;
  }

  /**
   * Swap the values identified by the given indexes in the given array.
   * 
   * @param a the array we want to swap the elements of.
   * @param i the first of the elements to swap.
   * @param j the second of the elements to swap.
   * @throws IndexOutOfBoundsException if i or j are out of the bounds for the given array.
   */
  protected static void swapValues(Object[] a, int i, int j) {
    Object tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  /**
   * Checks if the given array is sorted, scanning through it.
   * 
   * @param a an array to be checked.
   * @return <code>true</code> if the array is sorted, <code>false</code> otherwise.
   */
  protected static <T extends Comparable<T>> boolean isSorted(T[] a) {
    return isSorted(a, 0, a.length - 1);
  }

  /**
   * Checks if the selected interval given array is sorted, scanning through it.<br>
   * Typical usage could be <code>isSorted(a, 0, a.length -1);</code>
   * 
   * @param a the array to check
   * @param start the first element to check, inclusive.
   * @param end the last element to check, inclusive.
   * @return <code>true</code> if the interval is sorted, <code>false</code> otherwise.
   */
  protected static <T extends Comparable<T>> boolean isSorted(T[] a, int start, int end) {
    for (int i = start + 1; i <= end; i++) {
      if (less(a[i], a[i - 1])) {
        return false;
      }
    }
    return true;
  }

  protected static void show(String[] a) {
    for (String string : a) {
      System.out.print(string + " ");
    }
    System.out.println();
  }

}
