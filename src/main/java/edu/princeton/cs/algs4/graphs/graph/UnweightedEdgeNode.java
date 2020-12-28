package edu.princeton.cs.algs4.graphs.graph;

/**
 * Unweighted edge note.
 */
public class UnweightedEdgeNode implements EdgeNode {
    private final int y;                /* adjancency info */

    public UnweightedEdgeNode(int y) {
        this.y = y;
    }

    @Override
    public int w() {
        return y;
    }

    public UnweightedEdgeNode copy(int v) {
        return new UnweightedEdgeNode(v);
    }
}
