package edu.princeton.cs.algs4.graphs.graph;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

public class Edge implements EdgeNode {
    private final int x;             // from
    private final int y;             // to

    public Edge(int x, int y) {
        checkArgument(x >= 0, "Vertex names must be nonnegative integers");
        checkArgument(y >= 0, "Vertex names must be nonnegative integers");
        this.x = x;
        this.y = y;
    }

    public int v() {
        return x;
    }

    @Override
    public int w() {
        return y;
    }

    @Override
    public EdgeNode copy(int v) {
        return this;
    }

    public int other(int vertex) {
        if      (vertex == x) return y;
        else if (vertex == y) return x;
        else throw new IllegalArgumentException("invalid endpoint");
    }

    public String toString() {
        return v() + "->" + w();
    }
}
