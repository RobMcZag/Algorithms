package com.robertozagni.algoritmi.uf.percolation;

/**
 * Percolation system of size N x N.
 *  
 * @author roberto.zagni - Copyright (c) 2016
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * This class models a Percolation system of size N x N.
 * 
 * @author roberto.zagni - Copyright (c) 2016
 */
public class Percolation {

  /** The number of row and columns. The number of locations is N*N. */
  private int N;

  /** The index of node representing the TOP where percolation starts. */
  private final int theTOP;

  /** The array tracking the sites that are open. */
  private boolean[] open = null;

  /** The union-find ADT tracking the connected sites for PERCOLATION. */
  // private WeightedQuickUnionUF uf = null;
  /** The array tracking the ROOTS that are connected to TOP. */
  private boolean[] toTop = null;
  /** The array tracking the ROOTS that are connected to BOTTOM. */
  private boolean[] toBottom = null;

  /** The value telling if the system percolates or not. */
  private boolean percolates = false;

  /** The union-find ADT tracking the connected sites for FULL. */
  private WeightedQuickUnionUF ufTop = null;

  /**
   * create N-by-N grid, with all sites blocked.
   * 
   * @param N the size of the grid.
   * @throws IllegalArgumentException if N ≤ 0.
   */
  public Percolation(int N) {
    if (N <= 0) {
      throw new IllegalArgumentException("Provided size (" + N + ") is not positive.");
    }
    if (Integer.MAX_VALUE / N < N) {
      throw new IllegalArgumentException("Provided size (" + N + ") is too big for N*N to be indexed by integers.");
    }
    this.N = N;
    this.theTOP = N * N;
    this.open = new boolean[N * N]; // N^2 x 1B
    this.toTop = new boolean[N * N]; // N^2 x 1B
    this.toBottom = new boolean[N * N];
    this.ufTop = new WeightedQuickUnionUF(N * N + 1); // N^2 x 7B

    for (int col = 1; col <= N; col++) {
      toTop[index(1, col)] = true;
      toBottom[index(N, col)] = true;
    }

  }

  /**
   * The linearized 0-based index from the two 1-based dimensions.
   * 
   * @param row the 1 based dimension for rows;
   * @param col the 1 based dimension for columns;
   * @return the 0-based 1D index corresponding to the given 2D position
   */
  private int index(int row, int col) {
    return (row - 1) * N + (col - 1) * 1;
  }

  /**
   * Checks that the given value is in the range 1 to N.
   * 
   * @param val the value to be validated
   * @throws IndexOutOfBoundsException if value is out of range.
   */
  private void validate(int val) {
    if (val <= 0 || val > N) {
      throw new IndexOutOfBoundsException(String.format("Value %d is out of bounds ( 1 to %d).", val, N));
    }
  }

  // open site (row i, column j) if it is not open already
  public void open(int row, int col) {
    validate(row);
    validate(col);

    int idx = index(row, col);
    if (!isOpenV(idx)) {

      // open it
      open[idx] = true;

      // connect UP
      if (row == 1) { // if row = 1 connect to TOP
        if (toBottom[idx]) {
          percolates = true;
        }
        ufTop.union(idx, theTOP);
      } else { // if UP isOpen connect to it
        connectIfDestOpen(idx, row - 1, col);
      }

      // connect LEFT
      if (col > 1) {
        connectIfDestOpen(idx, row, col - 1);
      }

      // connect RIGHT
      if (col < N) {
        connectIfDestOpen(idx, row, col + 1);
      }

      // connect DOWN
      if (row == N) {
        if (toTop[idx]) {
          percolates = true;
        }
      } else { // if UP isOpen connect to it
        connectIfDestOpen(idx, row + 1, col);
      }

    }
  }

  /**
   * Connect the site passed as index to the destination passed as row and col, if the destination is open.
   * 
   * @param idx the index of the site being open now
   * @param destRow the row of the destination site
   * @param destCol the col of the destination site
   */
  private void connectIfDestOpen(int idx, int destRow, int destCol) {
    final int destIdx = index(destRow, destCol);

    if (isOpenV(destIdx)) {
      final int destRoot = ufTop.find(destIdx);
      updateTopBottom(ufTop.find(idx), destRoot);
      ufTop.union(idx, destRoot); // destIdx
    }

  }

  /**
   * Update the mapping for roots to Top and Bottom & calculates percolation state.
   * 
   * @param myRoot the root of the site being open.
   * @param destRoot the root of the site being connected to the newly open site.
   */
  private void updateTopBottom(int myRoot, int destRoot) {

    if (toTop[myRoot]) {
      toTop[destRoot] = true;
    } else if (toTop[destRoot]) {
      toTop[myRoot] = true;
    }

    if (toBottom[myRoot]) {
      toBottom[destRoot] = true;
    } else if (toBottom[destRoot]) {
      toBottom[myRoot] = true;
    }

    if (toTop[myRoot] && toBottom[myRoot]) {
      percolates = true;
    }
  }

  /**
   * Returns <code>true</code> if site (row i, column j) is open.
   * 
   * @param row the row in the grid (1 based).
   * @param col the column in the grid (1 based).
   */
  public boolean isOpen(int row, int col) {
    validate(row);
    validate(col);
    return isOpenV(index(row, col));
  }

  private boolean isOpenV(int idx) {
    return open[idx];
  }

  /**
   * Returns <code>true</code> if site (row i, column j) is full, i.e. open and connected with an open site in the top.
   * 
   * @param row the row in the grid (1 based).
   * @param col the column in the grid (1 based).
   */
  public boolean isFull(int row, int col) {
    validate(row);
    validate(col);
    return isFullV(row, col);
  }

  private boolean isFullV(int row, int col) {
    final int idx = index(row, col);
    return isOpenV(idx) && ufTop.connected(idx, theTOP);
  }

  /**
   * Returns <code>true</code> if the system percolate, i.e. open and bottom are connected by open sites.
   * 
   * @param row the row in the grid (1 based).
   * @param col the column in the grid (1 based).
   */
  public boolean percolates() {
    return percolates;
  }

  // test client (optional)
  public static void main(String[] args) {

    String file = "/input10.txt";
    if ((args.length != 0) && (args[0] != null) && (args[0].length() > 0)) {
      file = args[0];
    }
    In in = new In(file); // input file
    int N = in.readInt(); // N-by-N percolation system

    Percolation perc = new Percolation(N);
    // ### Enable to stop waiting for input & allow to attach Profiler from jvisualvm ###
    // System.out.format("N=%d | file:%s%n", N, file);

    // repeatedly read in sites to open and draw resulting system
    int step = 1;
    while (!in.isEmpty()) {
      int i = in.readInt();
      int j = in.readInt();
      perc.open(i, j);
      step++;
      drawState(N, perc);
      System.out.format("%5d | open %3d %3d | %s%n", step, i, j, perc.percolates());
      // try {
      // Thread.sleep(5 * step / N);
      // } catch (InterruptedException e) {
      // e.printStackTrace();
      // }
    }
    // System.out.format("%5d | %s%n", step, perc.percolates());
    // drawState(N, perc);

  }

  /**
   * @param N
   * @param perc
   */
  private static void drawState(int N, Percolation perc) {
    System.out.print(String.format("\033[H\033[2J")); // clear screen (HOME "\u001b[H" + CLEAR "\u001b[2J")

    for (int row = 1; row <= N; row++) {
      System.out.print(" ");
      for (int col = 1; col <= N; col++) {
        if (perc.isFull(row, col)) {
          System.out.print(":");
        } else if (perc.isOpen(row, col)) {
          System.out.print(" ");
        } else {
          System.out.print("#");

        }
      }
      System.out.println(" ");
    }
  }

}
