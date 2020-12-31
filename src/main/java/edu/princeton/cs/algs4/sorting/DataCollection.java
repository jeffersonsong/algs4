package edu.princeton.cs.algs4.sorting;

public interface DataCollection {
    int compare(int i, int j);

    default boolean less(int i, int j) {
        return compare(i, j) < 0;
    }

    default boolean greater(int i, int j) {
        return compare(i, j) > 0;
    }

    void exch(int i, int j);
}
