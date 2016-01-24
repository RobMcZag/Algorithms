package com.robertozagni.algoritmi.uf;

import java.util.Arrays;

public class WeightedQuickUnionUF extends QuickUnionUF {

    // private static final Logger LOG = LoggerFactory.getLogger(WeightedQuickUnionUF.class);

    protected int[] size;

    public WeightedQuickUnionUF(int N) {
        super(N);
        size = new int[N];
        Arrays.fill(size, 1);
    }

    @Override
    public void union(int p, int q) {
        int pp = find(p);
        int pq = find(q);
        if (pp != pq) {
            if (size[pp] < size[pq]) {
                parent[pp] = pq;
                size[pq] += size[pp];
            } else {
                parent[pq] = pp;
                size[pp] += size[pq];
            }

            count--;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(parent) + " - " + Arrays.toString(size);
    }
}
