package edu.princeton.cs.algs4.graphs.graph.impl;

import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.UnWeightedEdge;
import edu.princeton.cs.algs4.graphs.graph.WeightedEdge;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphImpl;
import edu.princeton.cs.algs4.graphs.maxflow.FlowEdge;
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
    public static Graph<Edge> readGraph(In in) {
        return readGraph(in, GraphImpl::graph);
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
    public static Graph<Edge> readDigraph(In in) {
        return readGraph(in, GraphImpl::digraph);
    }

    private static <T extends Graph<Edge>> T readGraph(In in, IntFunction<T> factoryMethod) {
        requiresNotNull(in, "argument is null");
        requiresNotNull(factoryMethod, "Factory method not set.");
        try {
            int V = in.readInt();
            checkArgument(V >= 0, "number of vertices in a Graph must be nonnegative");
            T G = factoryMethod.apply(V);
            int E = in.readInt();
            checkArgument(E >= 0, "number of edges in a Graph must be nonnegative");
            for (int i = 0; i < E; i++) {
                UnWeightedEdge edge = readUnWeightedEdge(in);
                G.addEdge(edge.v(), edge);
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
    public static Graph<WeightedEdge> readEdgeWeightedGraph(In in) {
        return readEdgeWeightedGraph(in, GraphImpl::graph);
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
    public static Graph<WeightedEdge> readEdgeWeightedDigraph(In in) {
        return readEdgeWeightedGraph(in, GraphImpl::digraph);
    }

    public static Graph<WeightedEdge> readEdgeWeightedGraph(In in, IntFunction<Graph<WeightedEdge>> factoryMethod) {
        requiresNotNull(in, "argument is null");

        try {
            int V = in.readInt();
            checkArgument(V >= 0, "number of vertices in a Graph must be nonnegative");
            Graph<WeightedEdge> G = factoryMethod.apply(V);

            int E = in.readInt();
            checkArgument(E >= 0, "Number of edges must be nonnegative");
            for (int i = 0; i < E; i++) {
                WeightedEdge e = readWeightedEdge(in);
                G.addEdge(e.v(), e);
            }

            return G;
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in EdgeWeightedGraph constructor", e);
        }
    }

    /**
     * Initializes a flow network from an input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices and edge capacities,
     * with each entry separated by whitespace.
     * @param in the input stream
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     */
    public static Graph<FlowEdge> readFlowNetwork(In in) {
        int V = in.readInt();
        checkArgument(V >= 0, "number of vertices in a Digraph must be nonnegative");

        int E = in.readInt();
        checkArgument(E >= 0, "number of edges must be nonnegative");

        Graph<FlowEdge> fn = GraphImpl.graph(V);
        for (int i = 0; i < E; i++) {
            FlowEdge edge = readFlowEdge(in);
            fn.addEdge(edge.v(), edge);
        }
        return fn;
    }

    private static UnWeightedEdge readUnWeightedEdge(In in) {
        int v = in.readInt();
        int w = in.readInt();
        return new UnWeightedEdge(v, w);
    }

    private static WeightedEdge readWeightedEdge(In in) {
        int v = in.readInt();
        int w = in.readInt();
        double weight = in.readDouble();
        return new WeightedEdge(v, w, weight);
    }

    private static FlowEdge readFlowEdge(In in) {
        int v = in.readInt();
        int w = in.readInt();
        double capacity = in.readDouble();
        return new FlowEdge(v, w, capacity);
    }

}
