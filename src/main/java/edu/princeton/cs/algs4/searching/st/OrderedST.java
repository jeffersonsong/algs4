package edu.princeton.cs.algs4.searching.st;

public interface OrderedST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {
    Key min();

    Key max();

    Key ceiling(Key key);

    Key floor(Key key);

    int rank(Key key);

    void deleteMin();

    void deleteMax();

    Key select(int k);

    int size(Key lo, Key hi);

    Iterable<Key> keys(Key lo, Key hi);
}
