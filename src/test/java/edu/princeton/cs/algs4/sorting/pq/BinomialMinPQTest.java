package edu.princeton.cs.algs4.sorting.pq;

public class BinomialMinPQTest extends MinPQBaseTest {
    @Override
    protected PQ<Character> createMinPQ() {
        return new BinomialMinPQ<Character>();
    }
}