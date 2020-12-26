package edu.princeton.cs.algs4.sorting.pq;

public class IndexMultiwayMinPQTest extends IndexMinPQBaseTest {

    protected IndexPQ<Character> createIndexMinPQ() {
        return new IndexMultiwayMinPQ<> (10, 2);
    }

}