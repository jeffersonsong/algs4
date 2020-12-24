package edu.princeton.cs.algs4.fundamentals.unionfind;

public class UFImplTest extends UFBaseTest {
    @Override
    protected UF createUF(final int N) {
        return new UFImpl(N);
    }
}