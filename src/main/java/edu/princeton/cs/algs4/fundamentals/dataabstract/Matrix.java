package edu.princeton.cs.algs4.fundamentals.dataabstract;

public interface Matrix<T> {
    T get(int r, int c);

    void set(int r, int c, T val);
}
