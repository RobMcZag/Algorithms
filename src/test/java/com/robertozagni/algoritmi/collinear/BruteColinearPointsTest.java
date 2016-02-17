package com.robertozagni.algoritmi.collinear;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class BruteColinearPointsTest {

  @Test(expected = NullPointerException.class)
  public void canNotCreateWithNullArg() throws Exception {
    new BruteCollinearPoints(null);
  }

  @Test(expected = NullPointerException.class)
  public void canNotCreateWithNullPoint() throws Exception {
    Point[] points = {new Point(1, 1), new Point(2, 1), null, new Point(1, 2)};
    new BruteCollinearPoints(points);
  }

  @Test(expected = IllegalArgumentException.class)
  public void exceptionIfCreateWithDuplicatePoints() throws Exception {
    Point[] points = {new Point(1, 1), new Point(2, 1), new Point(2, 1), new Point(1, 2)};
    new BruteCollinearPoints(points);
  }

  @Test(expected = IllegalArgumentException.class)
  public void exceptionIfCreateWithDuplicatePoints3points() throws Exception {
    Point[] points = {new Point(1, 1), new Point(2, 1), new Point(2, 1)};
    new BruteCollinearPoints(points);
  }

  @Test
  public void canCreateWithEmptyOrShortArray() throws Exception {
    BruteCollinearPoints bcp = new BruteCollinearPoints(new Point[] {});
    assertNotNull(bcp);
    assertEquals(0, bcp.numberOfSegments());
    assertEquals(0, bcp.segments().length); // redundant :)
    assertArrayEquals(new LineSegment[] {}, bcp.segments());

  }

  @Test
  public void createSegmentInCorrectOrder() throws Exception {
    final Point p = new Point(0, 0);
    final Point q = new Point(4, 4);
    Point[] points = {new Point(1, 1), new Point(3, 3), new Point(2, 1), p, q};
    BruteCollinearPoints bcp = new BruteCollinearPoints(points);
    assertEquals(1, bcp.numberOfSegments());
    assertEquals(1, bcp.segments().length); // redundant :)
    assertEquals(p + " -> " + q, bcp.segments()[0].toString());

  }
}
