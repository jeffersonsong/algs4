package edu.princeton.cs.algs4.sorting.pq;

import static org.hamcrest.MatcherAssert.assertThat;

public class MaxPQImplTest extends MaxPQBaseTest {
    @Override
    protected MaxPQ<Character> createMaxPQ() {
        return new MaxPQImpl<>();
    }
}