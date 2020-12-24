package edu.princeton.cs.algs4.fundamentals.unionfind;

public class QuickUnionUFTest extends UFBaseTest {
    @Override
    protected UF createUF(final int N) {
        return new QuickUnionUF(N);
    }
}