package edu.princeton.cs.algs4.sorting;

public interface RandomAccessDataCollection<T> extends DataCollection {
    T a(int i);

    int compareTo(T a, T b);

    @Override
    default int compare(int i, int j) {
        return compareTo(a(i), a(j));
    }

    int length();
}
