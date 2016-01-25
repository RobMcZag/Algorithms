
/**
 * Corner cases. By convention, the row and column indices i and j are integers between 1 and N,
 * where (1, 1) is the upper-left site: Throw a java.lang.IndexOutOfBoundsException if any argument
 * to open(), isOpen(), or isFull() is outside its prescribed range. The constructor should throw a
 * java.lang.IllegalArgumentException if N ≤ 0.
 * 
 * @author roberto.zagni
 */
public class Percolation {

  /**
   * create N-by-N grid, with all sites blocked.
   * 
   * @param nn the size of the grid.
   * @throws IllegalArgumentException if N ≤ 0.
   */
  public Percolation(int nn) {
    if (nn <= 0) {
      throw new IllegalArgumentException("Provided size (" + nn + ") is not positive.");
    }

    // TODO init array
  }

  // open site (row i, column j) if it is not open already
  public void open(int row, int col) {
  }

  // is site (row i, column j) open?
  public boolean isOpen(int row, int col) {
    return false;
  }

  // is site (row i, column j) full?
  public boolean isFull(int row, int col) {
    return false;
  }

  // does the system percolate?
  public boolean percolates() {
    return false;
  }

  // test client (optional)
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
