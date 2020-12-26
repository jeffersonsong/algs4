package edu.princeton.cs.algs4.sorting.pq;

import org.junit.Ignore;

@Ignore
public class IndexBinomialMinPQTest extends IndexMinPQBaseTest {

    protected IndexPQ<Character> createIndexMinPQ() {
        return new IndexBinomialMinPQ<> (10);
    }
}