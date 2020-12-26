package edu.princeton.cs.algs4.sorting.pq;

public class FibonacciMinPQTest extends MinPQBaseTest {
    @Override
    protected PQ<Character> createMinPQ() {
        return new FibonacciMinPQ<>();
    }
}