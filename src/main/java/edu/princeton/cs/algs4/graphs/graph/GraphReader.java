package edu.princeton.cs.algs4.graphs.graph;

import edu.princeton.cs.algs4.graphs.mst.Edge;
import edu.princeton.cs.algs4.utils.io.In;

import java.util.NoSuchElementException;
import java.util.function.IntFunction;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;
import static edu.princeton.cs.algs4.utils.PreConditions.requiresNotNull;

public class GraphReader {
    /**
     * Initializes a graph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices, with each entry separated by whitespace.
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if {@code in} is {@code null}
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     * @throws IllegalArgumentException if the input stream is in the wrong format
     */
    public static Graph<UnweightedEdgeNode> readGraph(In in) {
        return readGraph(in, V -> new GraphImpl<>(V, false));
    }

    /**
     * Initializes a digraph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices, with each entry separated by whitespace.
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if {@code in} is {@code null}
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     * @throws IllegalArgumentException if the input stream is in the wrong format
     */
    public static Graph<UnweightedEdgeNode> readDigraph(In in) {
        return readGraph(in, V -> new GraphImpl<>(V, true));
    }

    private static <T extends Graph<UnweightedEdgeNode>> T readGraph(In in, IntFunction<T> factoryMethod) {
        requiresNotNull(in, "argument is null");
        requiresNotNull(factoryMethod, "Factory method not set.");
        try {
            int V = in.readInt();
            checkArgument(V >= 0, "number of vertices in a Graph must be nonnegative");
            T G = factoryMethod.apply(V);
            int E = in.readInt();
            checkArgument(E >= 0, "number of edges in a Graph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                G.addEdge(v, new UnweightedEdgeNode(w));
            }
            return G;
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    /**
     * Initializes an edge-weighted graph from an input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices and edge weights,
     * with each entry separated by whitespace.
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if {@code in} is {@code null}
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     */
    public static Graph<Edge> readEdgeWeightedGraph(In in) {
        requiresNotNull(in, "argument is null");

        try {
            int V = in.readInt();
            checkArgument(V >= 0, "number of vertices in a Graph must be nonnegative");
            Graph<Edge> G = new GraphImpl<>(V, false);

            int E = in.readInt();
            checkArgument(E >= 0, "Number of edges must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                double weight = in.readDouble();
                Edge e = new Edge(v, w, weight);
                G.addEdge(e.v(), e);
            }

            return G;
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in EdgeWeightedGraph constructor", e);
        }
    }

    /**
     * Initializes an edge-weighted digraph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices and edge weights,
     * with each entry separated by whitespace.
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if {@code in} is {@code null}
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     */
    public static Graph<Edge> readEdgeWeightedDigraph(In in) {
        requiresNotNull(in,"argument is null");
        try {
            int V = in.readInt();
            Graph<Edge> G = new GraphImpl<>(V, true);
            checkArgument(V >= 0, "number of vertices in a Digraph must be nonnegative");

            int E = in.readInt();
            checkArgument(E >= 0, "Number of edges must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                double weight = in.readDouble();
                G.addEdge(v, new Edge(v, w, weight));
            }

            return G;
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in EdgeWeightedDigraph constructor", e);
        }
    }
}
