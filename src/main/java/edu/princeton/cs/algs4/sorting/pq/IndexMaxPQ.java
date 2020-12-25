package edu.princeton.cs.algs4.sorting.pq;

public interface IndexMaxPQ<Key> extends Iterable<Integer>{
    void insert(int i, Key key);

    Key keyOf(int i);

    void delete(int i);

    int delMax();

    void changeKey(int i, Key key);

    boolean contains(int i);

    int maxIndex();

    Key maxKey();

    boolean isEmpty();

    int size();
}
