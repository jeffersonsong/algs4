package edu.princeton.cs.algs4.sorting.pq;

public interface IndexMinPQ<Key> extends Iterable<Integer> {
    void insert(int i, Key key);

    Key keyOf(int i);

    void delete(int i);

    int delMin();

    void changeKey(int i, Key key);

    boolean contains(int i);

    int minIndex();

    Key minKey();

    boolean isEmpty();

    int size();
}
