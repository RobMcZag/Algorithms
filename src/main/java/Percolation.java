
/**
 * Percolation system of size N x N.
 *  
 * @author roberto.zagni - Copyright (c) 2016
 */

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

  /** The index of node representing the BOTTOM where percolation ends. */
  private final int theBOTTOM;

  // /** The value telling if the system percolates or not. */
  // private boolean percolates;

  /** The array tracking the sites that are open. */
  private boolean[] open = null;

  /** The union-find ADT tracking the connected sites. */
  private WeightedQuickUnionUF uf = null;

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
    this.open = new boolean[N * N];
    this.uf = new WeightedQuickUnionUF(N * N + 2);
    this.theTOP = N * N;
    this.theBOTTOM = this.theTOP + 1;
  }

  /**
   * The linearized 0-based index from the two 1-based dimensions.
   * 
   * @param row the 1 based dimension for rows;
   * @param col the 1 based dimension for columns;
   * @return the 0-based 1D index corresponding to the given 2D position
   */
  int index(int row, int col) {
    return (row - 1) * N + (col - 1);
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
    if (!isOpenSafe(row, col)) {

      // open it
      int idx = index(row, col);
      open[idx] = true;

      // connect UP
      if (row == 1) { // if row = 1 connect to TOP
        uf.union(idx, theTOP);
      } else if (isOpenSafe(row - 1, col)) { // if UP isOpen connect to it
        uf.union(idx, index(row - 1, col));
      }

      // connect LEFT
      if (col > 1 && isOpenSafe(row, col - 1)) {
        uf.union(idx, index(row, col - 1));
      }

      // connect RIGHT
      if (col < N && isOpenSafe(row, col + 1)) {
        uf.union(idx, index(row, col + 1));
      }

      // connect DOWN
      if (row == N) { // if row = N connect to BOTTOM
        // uf.union(idx, theBOTTOM);
      } else if (isOpenSafe(row + 1, col)) { // if UP isOpen connect to it
        uf.union(idx, index(row + 1, col));
      }
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
    return isOpenSafe(row, col);
  }

  boolean isOpenSafe(int row, int col) {
    return open[index(row, col)];
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
    return isFullSafe(row, col);
  }

  boolean isFullSafe(int row, int col) {
    return isOpenSafe(row, col) && uf.connected(index(row, col), theTOP);
  }

  /**
   * Returns <code>true</code> if the system percolate, i.e. open and bottom are connected by open sites.
   * 
   * @param row the row in the grid (1 based).
   * @param col the column in the grid (1 based).
   */
  public boolean percolates() {
    for (int i = index(N, 1); i <= index(N, N); i++) {
      if (open[i] && uf.connected(i, theTOP)) {
        return true;
      }
    }
    return false;
  }

  // test client (optional)
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
