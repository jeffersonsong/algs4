package edu.princeton.cs.algs4.graphs.sp;

public interface SP {
    // length of shortest path from s to v
    double distTo(int v);

    // shortest path from s to v
    Iterable<DirectedEdge> pathTo(int v);

    // is there a path from s to v?
    boolean hasPathTo(int v);
}
