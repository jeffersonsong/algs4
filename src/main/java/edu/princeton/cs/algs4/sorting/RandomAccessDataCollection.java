package edu.princeton.cs.algs4.sorting;

public interface RandomAccessDataCollection<T> extends DataCollection {
    T a(int i);

    int compare(T a, T b);

    @Override
    default int compareIndex(int i, int j) {
        return compare(a(i), a(j));
    }

    int length();
}
