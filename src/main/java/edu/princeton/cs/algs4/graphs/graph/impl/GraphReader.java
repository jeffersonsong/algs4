package edu.princeton.cs.algs4.graphs.graph.impl;

import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.UnWeightedEdge;
import edu.princeton.cs.algs4.graphs.graph.WeightedEdge;
import edu.princeton.cs.algs4.graphs.maxflow.FlowEdge;
import edu.princeton.cs.algs4.utils.io.In;

import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.IntFunction;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;
import static edu.princeton.cs.algs4.utils.PreConditions.requiresNotNull;

/**
 * Read graph from Input.
 */
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
        return readGraph(in, Graph::graph, GraphReader::readUnWeightedEdge);
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
        return readGraph(in, Graph::digraph, GraphReader::readUnWeightedEdge);
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
        return readGraph(in, Graph::graph, GraphReader::readWeightedEdge);
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
        return readGraph(in, Graph::digraph, GraphReader::readWeightedEdge);
    }

    public static Graph<Edge> readGraphInAdjMatrix(In in) {
        return readGraph(in, AdjMatrixGraphImpl::graph, GraphReader::readUnWeightedEdge);
    }

    public static Graph<Edge> readDigraphInAdjMatrix(In in) {
        return readGraph(in, AdjMatrixGraphImpl::graph, GraphReader::readUnWeightedEdge);
    }

    public static Graph<WeightedEdge> readEdgeWeightedGraphInAdjMatrix(In in) {
        return readGraph(in, AdjMatrixGraphImpl::graph, GraphReader::readWeightedEdge);
    }

    public static Graph<WeightedEdge> readEdgeWeightedDigraphInAdjMatrix(In in) {
        return readGraph(in, AdjMatrixGraphImpl::digraph, GraphReader::readWeightedEdge);
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
        return readGraph(in, Graph::graph, GraphReader::readFlowEdge);
    }

    /**
     * Read graph from input.
     * @param in input,
     * @param graphFactory graph factory.
     * @param edgeReader edge reader.
     * @param <T> edge type.
     * @return graph.
     */
    private static <T extends Edge> Graph<T> readGraph(In in, IntFunction<Graph<T>> graphFactory, Function<In, T> edgeReader) {
        requiresNotNull(in, "Input is null");
        requiresNotNull(graphFactory, "Graph factory is null");
        requiresNotNull(edgeReader, "Edge reader not set.");

        try {
            int V = in.readInt();
            checkArgument(V >= 0, "number of vertices in a Graph must be nonnegative");
            Graph<T> G = graphFactory.apply(V);

            int E = in.readInt();
            checkArgument(E >= 0, "Number of edges must be nonnegative");
            for (int i = 0; i < E; i++) {
                T e = edgeReader.apply(in);
                G.addEdge(e.v(), e);
            }

            return G;
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    /**
     * Read unweighted edge from input.
     * @param in input.
     * @return edge.
     */
    private static UnWeightedEdge readUnWeightedEdge(In in) {
        int v = in.readInt();
        int w = in.readInt();
        return new UnWeightedEdge(v, w);
    }

    /**
     * Read weighted edge from input.
     * @param in input.
     * @return edge.
     */
    private static WeightedEdge readWeightedEdge(In in) {
        int v = in.readInt();
        int w = in.readInt();
        double weight = in.readDouble();
        return new WeightedEdge(v, w, weight);
    }

    /**
     * Read flow edge from input.
     * @param in input.
     * @return edge.
     */
    private static FlowEdge readFlowEdge(In in) {
        int v = in.readInt();
        int w = in.readInt();
        double capacity = in.readDouble();
        return new FlowEdge(v, w, capacity);
    }

}
