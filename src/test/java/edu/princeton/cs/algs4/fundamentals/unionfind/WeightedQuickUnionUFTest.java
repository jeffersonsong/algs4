package edu.princeton.cs.algs4.fundamentals.unionfind;

public class WeightedQuickUnionUFTest extends UFBaseTest {
    @Override
    protected UF createUF(final int N) {
        return new WeightedQuickUnionUF(N);
    }
}