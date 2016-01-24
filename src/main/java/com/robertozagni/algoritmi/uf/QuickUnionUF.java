package com.robertozagni.algoritmi.uf;

import java.util.Arrays;

public class QuickUnionUF implements UnionFind {

    // private static final Logger LOG = LoggerFactory.getLogger(QuickUnionUF.class);

    protected final int[] parent;
    protected int count;

    public QuickUnionUF(int N) {

        parent = new int[N];
        count = N;

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int pp = find(p);
        int pq = find(q);
        if (pp != pq) {
            parent[pp] = pq;
            count--;
        }
    }

    @Override
    public int find(int p) {
        int pos = p;
        while (parent[pos] != pos) {
            pos = parent[pos];
        }
        return pos;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public String toString() {
        return Arrays.toString(parent);
    }
}
