package edu.princeton.cs.algs4.sorting.pq;

public class MaxPQImplTest extends MaxPQBaseTest {
    @Override
    protected MaxPQ<Character> createMaxPQ() {
        return new MaxPQImpl<>();
    }
}