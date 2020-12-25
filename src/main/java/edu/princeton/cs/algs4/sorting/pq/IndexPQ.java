package edu.princeton.cs.algs4.sorting.pq;

public interface IndexPQ<Key> extends Iterable<Integer> {
    void insert(int i, Key key);

    Key keyOf(int i);

    void delete(int i);

    int poll();

    int peek();

    void changeKey(int i, Key key);

    boolean contains(int i);

    Key peekKey();

    boolean isEmpty();

    int size();
}
