package edu.princeton.cs.algs4.sorting.pq;

public interface IndexMaxPQ<Key> extends Iterable<Integer>{
    void insert(int i, Key key);

    int maxIndex();

    Key maxKey();

    int delMax();

    void changeKey(int i, Key key);

    void increaseKey(int i, Key key);

    void decreaseKey(int i, Key key);

    void delete(int i);

    boolean contains(int i);
    Key keyOf(int i);

    boolean isEmpty();

    int size();
}
