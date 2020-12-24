package edu.princeton.cs.algs4.fundamentals.unionfind;

public class QuickFindUFTest extends UFBaseTest {
    @Override
    protected UF createUF(final int N) {
        return new QuickFindUF(N);
    }
}