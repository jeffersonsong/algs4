package edu.princeton.cs.algs4.fundamentals.unionfind;

public interface UF {
    int find(int p);

    int count();

    boolean connected(int p, int q);

    void union(int p, int q);
}
