package edu.princeton.cs.algs4.graphs.sp;

import edu.princeton.cs.algs4.graphs.mst.WeightedEdge;

/**
 * A shortest path from vertex s to vertex t in an edge-weighted digraph is a directed path from
 * s to t with the property that no other such path has a lower weight.
 */
public interface SP {
    // length of shortest path from s to v
    double distTo(int v);

    // shortest path from s to v
    Iterable<WeightedEdge> pathTo(int v);

    // is there a path from s to v?
    boolean hasPathTo(int v);
}
