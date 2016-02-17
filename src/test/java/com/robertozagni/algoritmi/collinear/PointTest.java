package com.robertozagni.algoritmi.collinear;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

public class PointTest {

  private final Point origin = new Point(0, 0);
  private final Point p = new Point(1, 1);
  private final Point q = new Point(2, 2);
  private final Point r = new Point(3, 3);
  private final Point s = new Point(4, 4);
  private final Point pv = new Point(1, 4);
  private final Point ph = new Point(4, 1);

  @Test
  public final void slopeIsAValue() {
    double spq = p.slopeTo(q);
    assertEquals(1, spq, 0.001);

    double spr = p.slopeTo(r);
    assertEquals(1, spr, 0.001);

    assertEquals(spq, spr, 0.001);

    double spo = p.slopeTo(origin);
    assertEquals(1, spo, 0.001);

    double spn = p.slopeTo(new Point(-1, -1));
    assertEquals(1, spn, 0.001);
  }

  @Test
  public void selfSlope() throws Exception {
    double spp = p.slopeTo(p);
    assertEquals(Double.NEGATIVE_INFINITY, spp, 0.001);

  }

  @Test
  public void verticalSlope() throws Exception {
    double spVert = p.slopeTo(pv);
    assertEquals(Double.POSITIVE_INFINITY, spVert, 0.001);

  }

  @Test
  public void horizzontalSlope() throws Exception {
    double spVert = p.slopeTo(ph);
    assertEquals(0D, spVert, 0.001);

  }

  @Test
  public void comparePoints() throws Exception {

    assertTrue(p.compareTo(q) < 0);
    assertTrue(p.compareTo(p) == 0);
    assertTrue(q.compareTo(p) > 0);

    assertTrue(p.compareTo(pv) < 0);
    assertTrue(p.compareTo(ph) < 0);

    assertTrue(q.compareTo(ph) > 0);
    assertTrue(q.compareTo(pv) < 0);

    assertTrue(p.compareTo(origin) > 0);
    assertTrue(origin.compareTo(new Point(-1, -1)) > 0);

    assertTrue(s.compareTo(origin) > 0);
  }

  @Test
  public void testComparator() throws Exception {
    Comparator<Point> cmp = p.slopeOrder();

    assertEquals(0, cmp.compare(q, r));
    assertEquals(0, cmp.compare(q, s));

    assertEquals(0, cmp.compare(s, s));

    assertEquals(0, cmp.compare(p, p));
    assertEquals(0, cmp.compare(ph, ph));
    assertEquals(0, cmp.compare(ph, new Point(-1, 1)));

    assertEquals(0, cmp.compare(pv, pv));
    assertEquals(0, cmp.compare(pv, new Point(1, -1)));

    // p is the comparison base and p.slopeTo(p) == Double.NEGATIVE_INFINITY < p.slopeTo(s) == 1
    assertEquals(-1, cmp.compare(p, s));

    assertTrue(cmp.compare(ph, s) < 0); // 0 < 1
    assertTrue(cmp.compare(pv, s) > 0);

  }
}
