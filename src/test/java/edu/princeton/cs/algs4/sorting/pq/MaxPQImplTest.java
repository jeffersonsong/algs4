package edu.princeton.cs.algs4.sorting.pq;

public class MaxPQImplTest extends MaxPQBaseTest {
    @Override
    protected PQ<Character> createMaxPQ() {
        return PQBinaryHeapImpl.maxPQ();
    }
}