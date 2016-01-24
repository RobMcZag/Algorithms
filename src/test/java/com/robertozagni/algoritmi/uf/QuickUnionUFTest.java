package com.robertozagni.algoritmi.uf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class QuickUnionUFTest {

    @Test
    public void testQuickUnionUF() {
        QuickUnionUF qu = new QuickUnionUF(10);
        assertNotNull(qu);
    }

    @Test
    public void whenEmptyAllDisconnected() throws Exception {
        int N = 10;
        QuickUnionUF qu = new QuickUnionUF(10);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                assertFalse(qu.connected(i, j));
            }
        }
    }

    @Test
    public void afterUnionObjetsAreConnected() throws Exception {
        int N = 10;
        QuickUnionUF qu = new QuickUnionUF(N);

        qu.union(0, 1);
        assertEquals(N - 1, qu.count());
        assertTrue(qu.connected(0, 1));
        assertTrue(qu.connected(1, 0));

        qu.union(2, 1);
        assertEquals(N - 2, qu.count());
        assertTrue(qu.connected(2, 1));
        assertTrue(qu.connected(1, 2));
        assertTrue(qu.connected(2, 0));
        assertTrue(qu.connected(0, 2));

        qu.union(4, 5);
        assertEquals(N - 3, qu.count());
        qu.union(5, 1);
        assertEquals(N - 4, qu.count());
        assertTrue(qu.connected(1, 5));
        assertTrue(qu.connected(4, 5));
        assertTrue(qu.connected(2, 5));
        assertTrue(qu.connected(1, 5));
        assertTrue(qu.connected(0, 5));

        qu.union(2, 5); // already connected
        assertEquals(N - 4, qu.count());
        assertTrue(qu.connected(2, 5));
    }

    @Test
    public void testUnion() {
        int N = 10;
        QuickUnionUF qu = new QuickUnionUF(N);

        qu.union(3, 5);
        assertEquals(N - 1, qu.count());
        assertTrue(qu.connected(3, 5));
        assertTrue(qu.connected(5, 3));
    }

    @Test
    public void testFind() {
        int N = 10;
        QuickUnionUF qu = new QuickUnionUF(N);

        assertEquals(4, qu.find(4));
        assertEquals(7, qu.find(7));

        qu.union(4, 7);
        assertTrue(qu.find(4) == 7 || qu.find(4) == 4);
        assertTrue(qu.find(7) == 7 || qu.find(4) == 4);
    }

    @Test
    public void testConnected() {
        int N = 10;
        QuickUnionUF qu = new QuickUnionUF(N);

        assertEquals(N, qu.count());
        assertFalse(qu.connected(0, 1));
        assertFalse(qu.connected(1, 0));

        qu.union(0, 1);

        assertEquals(N - 1, qu.count());
        assertTrue(qu.connected(0, 1));
        assertTrue(qu.connected(1, 0));
    }

    @Test
    public void testCount() {
        int N = 10;
        QuickUnionUF qu = new QuickUnionUF(N);

        qu.union(3, 5);
        assertEquals(N - 1, qu.count());

        qu.union(6, 5);
        assertEquals(N - 2, qu.count());

        qu.union(0, 8);
        assertEquals(N - 3, qu.count());
    }

}
