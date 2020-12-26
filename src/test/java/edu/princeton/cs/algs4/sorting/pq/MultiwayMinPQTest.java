package edu.princeton.cs.algs4.sorting.pq;


public class MultiwayMinPQTest extends MinPQBaseTest {

    protected PQ<Character> createMinPQ() {
        return new MultiwayMinPQ<>(2);
    }
}
