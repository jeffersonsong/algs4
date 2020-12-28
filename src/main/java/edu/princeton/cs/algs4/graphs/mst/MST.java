package edu.princeton.cs.algs4.graphs.mst;

/**
 * A minimum spanning tree (MST ) of an edge-weighted graph is a spanning tree whose weight
 * (the sum of the weights of its edges) is no larger than the weight of any other spanning tree.
 */
public interface MST {
    // edges in MST
    Iterable<Edge> edges();

    // weight in MST
    double weight();
}
