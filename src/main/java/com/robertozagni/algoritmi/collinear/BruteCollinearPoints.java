package com.robertozagni.algoritmi.collinear;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

  private LineSegment[] segments = {};

  /**
   * Finds all line segments containing 4 points.
   * 
   * @param points the array with the points to check for segments.
   */
  public BruteCollinearPoints(Point[] points) {
    points = points.clone();
    int N = points.length;
    /*
     * We do not exit early if N < 4 as we have to fulfill the contract of IllegalArgumentException for duplicate or
     * null points even if N < 4.
     */
    for (int i = 0; i < N; i++) { //
      Point p = points[i];
      for (int j = i + 1; j < N; j++) {
        Point q = points[j];
        checkNotSamePoint(i, p, j, q);
        double sq = p.slopeTo(q);
        for (int k = j + 1; k < N; k++) {
          Point r = points[k];
          double sr = p.slopeTo(r);
          if (sq != sr) { // without limitations on values should be ! (|sq-sr| < epsilon)
            continue;
          }
          for (int l = k + 1; l < N; l++) {
            Point s = points[l];
            double ss = p.slopeTo(s);
            if (sq == ss) { // without limitations on values should be |sq-ss| < epsilon
              addSegment(p, q, r, s);
            }

          } // l
        } // k
      } // j
    } // i

  }

  private void addSegment(Point p, Point q, Point r, Point s) {
    final int L = segments.length;
    LineSegment[] newSegs = new LineSegment[L + 1];
    System.arraycopy(segments, 0, newSegs, 0, L);
    Point[] segPoints = {p, q, r, s};
    Arrays.sort(segPoints);
    newSegs[L] = new LineSegment(segPoints[0], segPoints[3]);
    segments = newSegs;
  }

  /**
   * Checks that two points objects does not represent the same point.
   * 
   * @throws IllegalArgumentException if they do.
   */
  private void checkNotSamePoint(int i, Point p, int j, Point q) {
    if (p.compareTo(q) == 0) {
      throw new IllegalArgumentException(String.format("Two points are equal: [%d]=%s , [%d]=%s .", i, p, j, q));
    }
  }

  /**
   * Returns the number of line segments found.
   */
  public int numberOfSegments() {
    return segments.length;
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
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
  }

}
