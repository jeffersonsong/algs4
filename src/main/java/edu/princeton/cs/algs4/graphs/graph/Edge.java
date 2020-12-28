package edu.princeton.cs.algs4.graphs.graph;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

public class Edge implements Comparable<Edge> {
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

    public int w() {
        return y;
    }

    public int other(int vertex) {
        if      (vertex == x) return y;
        else if (vertex == y) return x;
        else throw new IllegalArgumentException("invalid endpoint");
    }

    public Edge reverse() {
        return new Edge(w(), v());
    }

    @Override
    public int compareTo(Edge that) {
        int cmp = Integer.compare(this.v(), that.v());
        return cmp != 0 ? cmp : Integer.compare(this.w(), that.w());
    }

    public String toString() {
        return v() + "->" + w();
    }
}
