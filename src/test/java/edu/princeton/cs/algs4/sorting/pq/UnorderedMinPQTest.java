package edu.princeton.cs.algs4.sorting.pq;

import java.util.Comparator;

public class UnorderedMinPQTest extends MinPQBaseTest {

    protected PQ<Character> createMinPQ() {
        return new UnorderedMinPQ<Character>(Comparator.naturalOrder());
    }
}
