package com.robertozagni.algoritmi.uf;

public class WeightedQuickUnionPathCompressionUF extends WeightedQuickUnionUF {

    public WeightedQuickUnionPathCompressionUF(int N) {
        super(N);
    }

    @Override
    public int find(int p) {
        int pos = p;
        while (parent[pos] != pos) {
            pos = parent[pos];
        }
        parent[p] = pos;
        return pos;
    }

}
