package edu.princeton.cs.algs4.graphs.graph;

import edu.princeton.cs.algs4.fundamentals.bag.LinkedBag;
import edu.princeton.cs.algs4.graphs.digraph.Digraph;
import edu.princeton.cs.algs4.graphs.digraph.DigraphImpl;
import edu.princeton.cs.algs4.graphs.mst.Edge;
import edu.princeton.cs.algs4.graphs.mst.EdgeWeightedGraph;
import edu.princeton.cs.algs4.graphs.mst.EdgeWeightedGraphImpl;
import edu.princeton.cs.algs4.graphs.sp.DirectedEdge;
import edu.princeton.cs.algs4.graphs.sp.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.graphs.sp.EdgeWeightedDigraphImpl;
import edu.princeton.cs.algs4.utils.io.In;

import java.util.NoSuchElementException;
import java.util.function.IntFunction;

import static edu.princeton.cs.algs4.utils.ArrayUtils.newArray;
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
    public static Graph readGraph(In in) {
        return readGraph(in, GraphImpl::new);
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
    public static Digraph readDigraph(In in) {
        return readGraph(in, DigraphImpl::new);
    }

    private static <T extends Graph> T readGraph(In in, IntFunction<T> factoryMethod) {
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
                G.addEdge(v, w);
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
    public static EdgeWeightedGraph readEdgeWeightedGraph(In in) {
        requiresNotNull(in, "argument is null");

        try {
            int V = in.readInt();
            checkArgument(V >= 0, "number of vertices in a Graph must be nonnegative");
            EdgeWeightedGraph G = new EdgeWeightedGraphImpl(V);

            int E = in.readInt();
            checkArgument(E >= 0, "Number of edges must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                double weight = in.readDouble();
                Edge e = new Edge(v, w, weight);
                G.addEdge(e);
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
    public static EdgeWeightedDigraph readEdgeWeightedDigraph(In in) {
        requiresNotNull(in,"argument is null");
        try {
            int V = in.readInt();
            EdgeWeightedDigraph G = new EdgeWeightedDigraphImpl(V);
            checkArgument(V >= 0, "number of vertices in a Digraph must be nonnegative");

            int E = in.readInt();
            checkArgument(E >= 0, "Number of edges must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                double weight = in.readDouble();
                G.addEdge(new DirectedEdge(v, w, weight));
            }

            return G;
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in EdgeWeightedDigraph constructor", e);
        }
    }
}
