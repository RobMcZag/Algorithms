
/**
 * Percolation system of size N x N.
 *  
 * @author roberto.zagni - Copyright (c) 2016
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Unit tests for class Percolation.
 * 
 * @author roberto.zagni - Copyright (c) 2016
 */
public class PercolationTest {

  @Test
  public void canCreateObjects() {
    Percolation p = new Percolation(10);
    assertNotNull(p);

    p = new Percolation(10000);
    assertNotNull(p);
  }

  @Test(expected = IllegalArgumentException.class)
  public void canNotCreateNis0() {
    Percolation p = new Percolation(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void canNotCreateNisNegative() {
    Percolation p = new Percolation(-10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void canNotCreateNisTooBig() {
    Percolation p = new Percolation(50000); // 50.000 ^2 = 2.500.000.000 > Integer.MAX_VALUE !!!
  }

  @Test(expected = IllegalArgumentException.class)
  public void canNotCreateNisTooBig2() {
    Percolation p = new Percolation(Integer.MAX_VALUE);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testOpenOutOfRangeUpRow() {
    Percolation p = new Percolation(10);
    p.open(10 + 1, 5);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testOpenOutOfRangeUpCol() {
    Percolation p = new Percolation(10);
    p.open(5, 10 + 1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testOpenOutOfRangeLowRow() {
    Percolation p = new Percolation(10);
    p.open(0, 5);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testOpenOutOfRangeLowCol() {
    Percolation p = new Percolation(10);
    p.open(5, 0);
  }

  @Test
  public void testIsOpenOutOfRange() {
    Percolation p = new Percolation(10);

    try {
      p.isOpen(10 + 1, 5);
      fail("Line above should throw exception");
    } catch (IndexOutOfBoundsException e) {
      /* Expected */
    }

    try {
      p.isOpen(5, 10 + 1);
      fail("Line above should throw exception");
    } catch (IndexOutOfBoundsException e) {
      /* Expected */
    }

    try {
      p.isOpen(0, 5);
      fail("Line above should throw exception");
    } catch (IndexOutOfBoundsException e) {
      /* Expected */
    }

    try {
      p.isOpen(-5, 5);
      fail("Line above should throw exception");
    } catch (IndexOutOfBoundsException e) {
      /* Expected */
    }

    try {
      p.isOpen(5, 0);
      fail("Line above should throw exception");
    } catch (IndexOutOfBoundsException e) {
      /* Expected */
    }

    try {
      p.isOpen(5, -5);
      fail("Line above should throw exception");
    } catch (IndexOutOfBoundsException e) {
      /* Expected */
    }
  }

  @Test
  public void testIsFullOutOfRange() {
    Percolation p = new Percolation(10);

    try {
      p.isFull(-5, 5);
      fail("Line above should throw exception");
    } catch (IndexOutOfBoundsException e) {
      /* Expected */
    }

    try {
      p.isFull(0, 5);
      fail("Line above should throw exception");
    } catch (IndexOutOfBoundsException e) {
      /* Expected */
    }

    try {
      p.isFull(5, -5);
      fail("Line above should throw exception");
    } catch (IndexOutOfBoundsException e) {
      /* Expected */
    }

    try {
      p.isFull(5, 0);
      fail("Line above should throw exception");
    } catch (IndexOutOfBoundsException e) {
      /* Expected */
    }

    try {
      p.isFull(15, 5);
      fail("Line above should throw exception");
    } catch (IndexOutOfBoundsException e) {
      /* Expected */
    }

    try {
      p.isFull(5, 15);
      fail("Line above should throw exception");
    } catch (IndexOutOfBoundsException e) {
      /* Expected */
    }

  }

  @Test
  public void testIndexing() throws Exception {
    Percolation p = new Percolation(10);
    assertEquals(0, p.index(1, 1));
    assertEquals(9, p.index(1, 10));
    assertEquals(19, p.index(2, 10));
    assertEquals(40, p.index(5, 1));
    assertEquals(49, p.index(5, 10));
    assertEquals(90, p.index(10, 1));
    assertEquals(99, p.index(10, 10));
  }

  @Test
  public void atStrtupAllClosed() {
    int N = 10;
    Percolation p = new Percolation(N);
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        assertFalse(p.isOpenSafe(i, j));
        assertFalse(p.isFullSafe(i, j));
      }
    }
  }

  @Test
  public void testOpenInRange() {
    Percolation p = new Percolation(10);

    assertFalse(p.isOpenSafe(1, 1));
    p.open(1, 1);
    assertTrue(p.isOpenSafe(1, 1));
    assertFalse(p.isOpenSafe(1, 2));
    assertFalse(p.isOpenSafe(2, 1));

    p.open(1, 5);
    assertTrue(p.isOpenSafe(1, 5));
    assertFalse(p.isOpenSafe(1, 4));
    assertFalse(p.isOpenSafe(1, 6));
    assertFalse(p.isOpenSafe(2, 5));

    p.open(1, 10);
    assertTrue(p.isOpenSafe(1, 10));

    p.open(5, 5);
    assertTrue(p.isOpenSafe(5, 5));
    assertFalse(p.isOpenSafe(5, 4));
    assertFalse(p.isOpenSafe(5, 6));
    assertFalse(p.isOpenSafe(4, 5));
    assertFalse(p.isOpenSafe(6, 5));

    p.open(10, 1);
    assertTrue(p.isOpenSafe(10, 1));

    p.open(10, 5);
    assertTrue(p.isOpenSafe(10, 5));

    p.open(10, 10);
    assertTrue(p.isOpenSafe(10, 10));

  }

  @Test
  public void testIsFullInRange() {
    Percolation p = new Percolation(5);

    assertFalse(p.percolates());

    assertFalse(p.isFullSafe(1, 3));
    p.open(1, 3);
    assertTrue(p.isFullSafe(1, 3));

    p.open(2, 3);
    assertTrue(p.isFullSafe(2, 3));

    p.open(2, 4);
    assertTrue(p.isFullSafe(2, 4));
    assertFalse(p.isFullSafe(1, 4));

    p.open(4, 4);
    assertFalse(p.isFullSafe(4, 4));
    assertFalse(p.percolates());

    p.open(3, 4);
    assertTrue(p.isFullSafe(3, 4));
    assertTrue(p.isFullSafe(4, 4));
    assertFalse(p.percolates());

    p.open(5, 1);
    assertTrue(p.isOpenSafe(5, 1));
    assertFalse(p.isFullSafe(5, 1));

    p.open(5, 4);
    assertTrue(p.isFullSafe(5, 4));
    assertTrue(p.percolates());

  }

  @Test
  public void canAvoidBackFillOnPercolation() {
    Percolation p = new Percolation(5);

    assertFalse(p.percolates());

    p.open(1, 3);
    p.open(2, 3);
    p.open(2, 4);

    p.open(5, 1);
    assertTrue(p.isOpenSafe(5, 1));
    assertFalse(p.isFullSafe(5, 1));

    p.open(4, 4);
    p.open(3, 4);
    p.open(5, 4);
    assertTrue(p.percolates());

    assertFalse(p.isFullSafe(5, 1)); // NO BackFill!
  }

  @Test
  public void percolateWhenJoinInMiddle() {
    Percolation p = new Percolation(5);

    assertFalse(p.percolates());

    p.open(1, 3);
    p.open(2, 3);
    p.open(2, 4);

    p.open(5, 1);
    assertTrue(p.isOpenSafe(5, 1));
    assertFalse(p.isFullSafe(5, 1));

    p.open(5, 4);
    p.open(4, 4);
    p.open(3, 4);
    assertTrue(p.percolates());

    assertFalse(p.isFullSafe(5, 1)); // NO BackFill!
  }

}
