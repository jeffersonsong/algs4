package edu.princeton.cs.algs4.sorting.pq;

public class IndexFibonacciMinPQTest extends IndexMinPQBaseTest {

    protected IndexPQ<Character> createIndexMinPQ() {
        return new IndexFibonacciMinPQ<> (10);
    }

}