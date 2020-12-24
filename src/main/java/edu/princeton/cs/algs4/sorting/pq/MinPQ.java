package edu.princeton.cs.algs4.sorting.pq;

public interface MinPQ<Key> extends Iterable<Key> {
    void insert(Key x);

    Key minKey();

    Key delMin();

    boolean isEmpty();

    int size();
}
