package edu.princeton.cs.algs4.sorting.pq;

import org.junit.Ignore;

@Ignore
public class BinomialMinPQTest extends MinPQBaseTest {
    @Override
    protected PQ<Character> createMinPQ() {
        return new BinomialMinPQ<>();
    }
}