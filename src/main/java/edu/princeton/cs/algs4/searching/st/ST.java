package edu.princeton.cs.algs4.searching.st;

public interface ST<Key, Value> {
    Value get(Key key);

    void put(Key key, Value val);

    void delete(Key key);

    boolean contains(Key key);

    int size();

    boolean isEmpty();

    Iterable<Key> keys();
}
