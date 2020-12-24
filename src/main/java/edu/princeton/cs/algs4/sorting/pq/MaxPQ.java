package edu.princeton.cs.algs4.sorting.pq;

public interface MaxPQ<Key> extends Iterable<Key> {
    void insert(Key x);

    Key delMax();

    Key maxKey();

    boolean isEmpty();

    int size();
}
