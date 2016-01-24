package com.robertozagni.algoritmi.uf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class QuickfindUFTest {

    @Test
    public void anCreateAnInstance() {
        QuickfindUF qf = new QuickfindUF(10);
        assertNotNull(qf);
    }

    @Test
    public void whenEmptyAllDisconnected() throws Exception {
        int N = 10;
        QuickfindUF qf = new QuickfindUF(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                assertFalse(qf.connected(i, j));
            }
        }
    }

    @Test
    public void afterUnionObjetsAreConnected() throws Exception {
        int N = 10;
        QuickfindUF qf = new QuickfindUF(N);

        qf.union(0, 1);
        assertEquals(N - 1, qf.count());
        assertTrue(qf.connected(0, 1));
        assertTrue(qf.connected(1, 0));

        qf.union(2, 1);
        assertEquals(N - 2, qf.count());
        assertTrue(qf.connected(2, 1));
        assertTrue(qf.connected(1, 2));
        assertTrue(qf.connected(2, 0));
        assertTrue(qf.connected(0, 2));

        qf.union(4, 5);
        assertEquals(N - 3, qf.count());
        qf.union(5, 1);
        assertEquals(N - 4, qf.count());
        assertTrue(qf.connected(1, 5));
        assertTrue(qf.connected(4, 5));
        assertTrue(qf.connected(2, 5));
        assertTrue(qf.connected(1, 5));
        assertTrue(qf.connected(0, 5));

        qf.union(2, 5); // already connected
        assertEquals(N - 4, qf.count());
        assertTrue(qf.connected(2, 5));
    }
}
