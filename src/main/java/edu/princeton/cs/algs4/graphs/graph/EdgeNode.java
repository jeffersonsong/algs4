package edu.princeton.cs.algs4.graphs.graph;

/**
 * Adjacency list node.
 */
public interface EdgeNode {
    /**
     * @return adjacent vertex.
     */
    int w();           /* adjancency info */

    /**
     * Create a copy of edge node for given adjacent vertex.
     *
     * @param v adjacent vertex.
     * @return copy of edge node for given adjacent vertex.
     */
    EdgeNode copy(int v);
}
