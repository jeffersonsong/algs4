package edu.princeton.cs.algs4.graphs.maxflow;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphImpl;
import edu.princeton.cs.algs4.utils.StdRandom;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

public class FlowNetworkGenerator {
    private FlowNetworkGenerator() {
    }

    /**
     * Initializes a random flow network with {@code V} vertices and <em>E</em> edges.
     * The capacities are integers between 0 and 99 and the flow values are zero.
     * @param V the number of vertices
     * @param E the number of edges
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}
     */
    public static Graph<FlowEdge> simple(int V, int E) {
        Graph<FlowEdge> fn = new GraphImpl<>(V, false);
        checkArgument(E >= 0,"Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double capacity = StdRandom.uniform(100);
            fn.addEdge(v, new FlowEdge(v, w, capacity));
        }
        return fn;
    }
}
