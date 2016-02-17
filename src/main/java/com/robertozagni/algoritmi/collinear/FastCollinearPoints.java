package com.robertozagni.algoritmi.collinear;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

  private int count = 0;
  private LineSegment[] segments = new LineSegment[8];

  /**
   * Finds all line segments containing 4 or more points.
   * 
   * @param points the points to be checked.
   */
  public FastCollinearPoints(Point[] points) {

    Point[] pointsByCoord = points.clone();
    Arrays.sort(pointsByCoord);
    Point[] pointsBySlope = pointsByCoord.clone();

    int N = pointsByCoord.length;
    for (int p = 0; p < N - 1; p++) {
      Point origin = pointsByCoord[p];

      System.arraycopy(pointsByCoord, 0, pointsBySlope, 0, N);
      Arrays.sort(pointsBySlope, 0, N, origin.slopeOrder());
      // System.out.format("Origin %s | %s%n%n", origin, Arrays.asList(pointsBySlope));

      checkNotSamePoint(origin, pointsBySlope[1]); // if there is a point equal to origin it is the first

      double prevSlope = Double.NEGATIVE_INFINITY;
      int pairs = 0;
      for (int i = 1; i < N; i++) {
        final double currentSlope = origin.slopeTo(pointsBySlope[i]);
        if (currentSlope == prevSlope) {
          pairs++;
        } else { // slope is not matching anymore!
          checkAddSegment(pointsBySlope, origin, pairs, i);
          pairs = 0;
        }
        prevSlope = currentSlope;
      }
      checkAddSegment(pointsBySlope, origin, pairs, N);

    }
    resize(count);
  }

  /**
   * Check and eventually adds a segment to the results.<br>
   * The segment contains the point at position 0 (the origin) and from i-count-1 to i (included)
   * 
   * @param sPoints the point ordered by slope WRT the origin
   * @param origin the origin for the ordering
   * @param pairs the number of pairs with same slope
   * @param endidx the index after the last point with same slope.
   */
  private void checkAddSegment(Point[] sPoints, Point origin, int pairs, int endidx) {
    if (pairs >= 2) {
      final int pts = pairs + 1;
      final int first = endidx - pts;
      if (origin.compareTo(sPoints[first]) < 0) {
        LineSegment ls = new LineSegment(origin, sPoints[endidx - 1]);
        addSegment(ls);
      }
    }
  }

  /**
   * Adds a segment to the results. The segment contains the point at position 0 (the origin) and from i-count-1 to i
   * (included)
   */
  /**
   * @param points the points we have to check, sorted for the current origin
   * @param last the index of the last point in the segment
   * @param pts the number of the points in the segment, excluding the origin.
   */
  private void addSegment(LineSegment ls) {
    if (count == segments.length) {
      resize(2 * count);
    }
    segments[count++] = ls;
  }

  private void resize(int n) {
    LineSegment[] dest = new LineSegment[n];
    System.arraycopy(segments, 0, dest, 0, count);
    segments = dest;
  }

  /**
   * Checks that two points objects does not represent the same point.
   * 
   * @throws IllegalArgumentException if they do.
   */
  private void checkNotSamePoint(Point p, Point q) {
    if (p.compareTo(q) == 0) {
      throw new IllegalArgumentException(String.format("Two points are equal: %s , %s .", p, q));
    }
  }

  /**
   * Returns the number of line segments found.
   */
  public int numberOfSegments() {
    return count;
  }

  /**
   * Returns the line segments found.
   */
  public LineSegment[] segments() {
    return segments.clone();
  }

  public static void main(String[] args) {

    // read the N points from a file
    In in = new In(args[0]);
    int N = in.readInt();
    Point[] points = new Point[N];
    for (int i = 0; i < N; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.show(0);
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }

    StdOut.println("Num of Segs = " + collinear.numberOfSegments());
  }
}
