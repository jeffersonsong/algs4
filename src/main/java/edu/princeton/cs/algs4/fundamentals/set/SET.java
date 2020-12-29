package edu.princeton.cs.algs4.fundamentals.set;

public interface SET<Key> extends Iterable<Key> {
    // add the key to the set
    void add(Key key);

    // is the key in the set?
    boolean contains(Key key);

    // remove the key from the set.
    void remove(Key key);

    // return the number of keys in the set.
    int size();

    boolean isEmpty();
}