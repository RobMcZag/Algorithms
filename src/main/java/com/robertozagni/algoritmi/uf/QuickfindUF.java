package com.robertozagni.algoritmi.uf;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Quick-find tracks for every object the id of the component the object belong.<br>
 * This means that checking if two objects are connect is as simple as to check if they belong to the same component.
 * The operation to add a new connection is on the contrary quite expensive as it has to update all the id for the
 * objects of one of the two components that get connected.
 * 
 * @author roberto.zagni
 *
 */
public class QuickfindUF implements UnionFind {

    private static final Logger LOG = LoggerFactory.getLogger(QuickfindUF.class.getName());

    private final int[] id;
    private int count;

    /**
     * Create an UnionFind object to track connections between N objects.
     * 
     * @param N The maximum number of objects to be able to track.
     */
    public QuickfindUF(int N) {

        id = new int[N];
        count = N;

        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }

        if (LOG.isTraceEnabled()) {
            LOG.trace("Initialisation done for {} objects.", N);
            LOG.trace(Arrays.toString(id));
        }
    }

    @Override
    public void union(int p, int q) {
        validate(p);
        validate(q);
        int src = id[p];
        int dest = id[q];

        if (src != dest) {
            for (int i = 0; i < id.length; i++) {
                if (id[i] == src) {
                    id[i] = dest;
                }
            }
            count--;
            LOG.trace("Connected {} and {}.", p, q);
        } else {
            LOG.trace("Nothing to do: {} and {} are already connected.", p, q);
        }
        LOG.trace(Arrays.toString(id));
    }

    @Override
    public int find(int p) {
        validate(p);
        return id[p];
    }

    @Override
    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return id[p] == id[q];
    }

    @Override
    public int count() {
        return count;
    }

    private void validate(int p) {
        if (p < 0 || p >= id.length) {
            throw new IndexOutOfBoundsException(
                    "Provided index (" + p + ") is out of bounds: 0 to " + (id.length - 1) + " inclusive.");
        }
    }

}
