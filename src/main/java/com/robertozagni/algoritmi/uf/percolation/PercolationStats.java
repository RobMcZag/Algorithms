package com.robertozagni.algoritmi.uf.percolation;

/**
 * Executes experiments on a Percolation system of size N x N.
 *  
 * @author roberto.zagni - Copyright (c) 2016
 */

import java.io.IOException;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * This class executes experiments on a Percolation system of size N x N.
 * 
 * @author roberto.zagni - Copyright (c) 2016
 */
public class PercolationStats {

  // /** The number of open sites when system starts percolating. */
  // private final int[] open;

  /** The fraction of open sites over the total when system starts percolating. */
  private final double[] openFraction;

  /** The size of every dimension of the percolation system. */
  private final int N;

  /** The number of experiments to run. */
  private final int T;

  /** The mean value. */
  private double mean;

  /** The stdev value. */
  private double stdev;

  /**
   * Perform T independent experiments on an N-by-N grid.
   * 
   * @param N The size of every dimension of the percolation system.
   * @param T The number of experiments to run.
   */
  public PercolationStats(int N, int T) {
    if (T <= 0) {
      throw new IllegalArgumentException("Provided number of experiments (" + T + ") is not positive.");
    }
    if (N <= 0) {
      throw new IllegalArgumentException("Provided size (" + N + ") is not positive.");
    }
    if (Integer.MAX_VALUE / N < N) {
      throw new IllegalArgumentException("Provided size (" + N + ") is too big for N*N to be indexed by integers.");
    }

    this.N = N;
    this.T = T;

    int[] open = new int[T];
    for (int exp = 0; exp < T; exp++) {
      open[exp] = percSim(N);
    }

    double totRatio = 1D / (N * N);
    this.openFraction = new double[T];
    for (int i = 0; i < open.length; i++) {
      openFraction[i] = open[i] * totRatio;
    }

    // Calculate results
    mean = meanCalc();
    stdev = stdevCalc();
  }

  /**
   * Runs a simulation of percolation until the system percolates.
   * 
   * @param n the size of system's dimensions.
   * @return the number of open sites when the system starts percolating.
   */
  private int percSim(int n) {
    int openSites = 0;
    Percolation p = new Percolation(n);
    while (!p.percolates()) {
      int row = StdRandom.uniform(n) + 1;
      int col = StdRandom.uniform(n) + 1;
      if (!p.isOpen(row, col)) {
        openSites++;
        p.open(row, col);
      }
    }
    return openSites;
  }

  // sample mean of percolation threshold
  /**
   * Return the mean of percolation threshold.
   */
  public double mean() {
    return mean;
  }

  /**
   * Calculates the mean of percolation threshold.
   */
  private double meanCalc() {
    double m = 0;
    for (int i = 0; i < openFraction.length; i++) {
      m += openFraction[i];
    }
    return m / T;
  }

  /**
   * Returns the sample standard deviation of percolation threshold.
   */
  public double stddev() {
    return stdev;
  }

  /**
   * Calculates the sample standard deviation of percolation threshold.
   */
  private double stdevCalc() {
    double std = 0;
    for (int i = 0; i < openFraction.length; i++) {
      double delta = openFraction[i] - mean;
      std += delta * delta;
    }
    return Math.sqrt(std / (T - 1));
  }

  /**
   * low endpoint of 95% confidence interval.
   */
  public double confidenceLo() {
    return mean - (1.96 * stdev / Math.sqrt(T));
  }

  /**
   * high endpoint of 95% confidence interval
   */
  public double confidenceHi() {
    return mean + (1.96 * stdev / Math.sqrt(T));
  }

  // test client (described below)}
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("EXPECTED: java PercolationStats <N> <T>");
      System.out.println("          <N> is the size of the system; <T> is the number of experiemnts.");
      // System.exit(1);
      return;
    }
    int n = 1;
    int t = 1;
    try {
      n = Integer.parseInt(args[0]);
      t = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
      System.out.println("Could not convert to int one of the arguments: " + args[0] + ", " + args[0] + ".");
      e.printStackTrace();
    }

    try {
      System.out.println("READY to RUN?");
      System.in.read();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Stopwatch watch = new Stopwatch();
    PercolationStats stats = new PercolationStats(n, t);
    double elapsed = watch.elapsedTime();

    System.out.println("N = " + n + ", T = " + t);
    System.out.println("mean                    = " + stats.mean()); // 0.5929934999999997
    System.out.println("stddev                  = " + stats.stddev()); // 0.00876990421552567
    System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());

    System.out.println("elapsed = " + elapsed);

  }
}
