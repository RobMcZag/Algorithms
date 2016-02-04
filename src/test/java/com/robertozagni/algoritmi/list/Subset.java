package com.robertozagni.algoritmi.list;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Subset {

  /**
   * Unit testing
   */
  public static void main(String[] args) {
    int k = 0;
    try {
      k = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      System.out.println("Expected an argument with the number of elements to print.");
      System.out.println("USAGE: Subset k - to print k values from the input.");
      System.out.println("EXAMPLE: echo A B C D E F G H I | java Subset 3  => prints out 3 values from the input.");
    }

    int n = 0;
    RandomizedQueue<String> rq = new RandomizedQueue<String>();
    while (!StdIn.isEmpty()) {

      String s = StdIn.readString();
      n++;
      if (rq.size() == k) {
        final double rnd = StdRandom.uniform(1, n + 1);
        if (rnd <= k) {
          rq.dequeue();
          rq.enqueue(s);
        }
      } else {
        rq.enqueue(s);
      }

    }

    for (int i = 0; i < k && !rq.isEmpty(); i++) {
      System.out.println(rq.dequeue());
    }

  }
}
