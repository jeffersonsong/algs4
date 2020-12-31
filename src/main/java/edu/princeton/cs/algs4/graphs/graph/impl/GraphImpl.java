/******************************************************************************
 *  Compilation:  javac Graph.java        
 *  Execution:    java Graph input.txt
 *  Dependencies: Bag.java Stack.java In.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/41graph/tinyG.txt
 *                https://algs4.cs.princeton.edu/41graph/mediumG.txt
 *                https://algs4.cs.princeton.edu/41graph/largeG.txt
 *
 *  A graph, implemented using an array of sets.
 *  Parallel edges and self-loops allowed.
 *
 *  % java Graph tinyG.txt
 *  13 vertices, 13 edges 
 *  0: 6 2 1 5 
 *  1: 0 
 *  2: 0 
 *  3: 5 4 
 *  4: 5 6 3 
 *  5: 3 4 0 
 *  6: 0 4 
 *  7: 8 
 *  8: 7 
 *  9: 11 10 12 
 *  10: 9 
 *  11: 9 12 
 *  12: 11 9 
 *
 *  % java Graph mediumG.txt
 *  250 vertices, 1273 edges 
 *  0: 225 222 211 209 204 202 191 176 163 160 149 114 97 80 68 59 58 49 44 24 15 
 *  1: 220 203 200 194 189 164 150 130 107 72 
 *  2: 141 110 108 86 79 51 42 18 14 
 *  ...
 *  
 ******************************************************************************/

package edu.princeton.cs.algs4.graphs.graph.impl;

import edu.princeton.cs.algs4.fundamentals.bag.Bag;
import edu.princeton.cs.algs4.fundamentals.bag.LinkedBag;
import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.utils.io.In;
import edu.princeton.cs.algs4.utils.io.StdOut;

import static edu.princeton.cs.algs4.utils.ArrayUtils.newArray;
import static edu.princeton.cs.algs4.utils.ArrayUtils.newIntArray;
import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;
import static edu.princeton.cs.algs4.utils.PreConditions.requiresNotNull;

/**
 *  The {@code Graph} class represents an graph of vertices named 0 through <em>V</em> – 1.
 *  It can be undirected or directed graph.
 *
 *  It supports the following two primary operations: add an edge to the graph,
 *  iterate over all of the vertices adjacent to a vertex. It also provides
 *  methods for returning the degree of a vertex, the number of vertices
 *  <em>V</em> in the graph, and the number of edges <em>E</em> in the graph.
 *  Parallel edges and self-loops are permitted.
 *  By convention, a self-loop <em>v</em>-<em>v</em> appears in the
 *  adjacency list of <em>v</em> twice and contributes two to the degree
 *  of <em>v</em>.
 *  <p>
 *  This implementation uses an <em>adjacency-lists representation</em>, which
 *  is a vertex-indexed array of {@link Bag} objects.
 *  It uses &Theta;(<em>E</em> + <em>V</em>) space, where <em>E</em> is
 *  the number of edges and <em>V</em> is the number of vertices.
 *  All instance methods take &Theta;(1) time. (Though, iterating over
 *  the vertices returned by {@link #adj(int)} takes time proportional
 *  to the degree of the vertex.)
 *  Constructing an empty graph with <em>V</em> vertices takes
 *  &Theta;(<em>V</em>) time; constructing a graph with <em>E</em> edges
 *  and <em>V</em> vertices takes &Theta;(<em>E</em> + <em>V</em>) time. 
 *  <p>
 *  For additional documentation, see
 *  <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class GraphImpl<T extends Edge> implements Graph<T> {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final boolean directed;   /* is the graph directed? */
    private final int V;              /* number of vertices in the graph */
    private int E;                    /* number of edges in the graph */
    private final Bag<T>[] adj;       /* adjacency info */
    private final int[] indegree;        // indegree[v] = indegree of vertex v

    /**
     * Create an empty directed graph.
     * @param V number of vertices.
     * @param <T> edge type.
     * @return Empty directed graph.
     */
    public static <T extends Edge> Graph<T> digraph(int V) {
        return new GraphImpl<>(V, true);
    }

    /**
     * Create an empty undirected graph.
     * @param V number of vertices.
     * @param <T> edge type.
     * @return Empty undirected graph.
     */
    public static <T extends Edge> Graph<T> graph(int V) {
        return new GraphImpl<>(V, false);
    }

    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  V number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    private GraphImpl(int V, boolean directed) {
        checkArgument(V >= 0, "Number of vertices must be nonnegative");
        this.directed = directed;
        this.V = V;
        this.E = 0;
        this.adj = newArray(V, i->new LinkedBag<>());
        this.indegree = new int[V];
    }

    /**
     * Initializes a new GraphImpl that is a deep copy of {@code G}.
     *
     * @param  G the graph to copy
     * @throws IllegalArgumentException if {@code G} is {@code null}
     */
    public GraphImpl(Graph<T> G) {
        requiresNotNull(G, "argument is null");
        checkArgument(G.V() >= 0, "Number of vertices in a Digraph must be nonnegative");

        this.directed = G.isDirected();
        this.V = G.V();
        this.E = G.E();

        // update indegrees
        this.indegree = newIntArray(V, G::indegree);
        this.adj = (Bag<T>[])new Bag[V];

        for (int v = 0; v < G.V(); v++) {
            this.adj[v] = new LinkedBag<>(G.adj(v), true);
        }
    }

    @Override
    public boolean isDirected() {
        return this.directed;
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        checkArgument(v >= 0 && v < V, "vertex " + v + " is not in range [0, " + (V-1) + "]");
    }

    /**
     * Adds the edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  edge the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, T edge) {
        validateVertex(v);
        validateVertex(edge.w());
        addEdge(v, edge, this.directed);
    }

    private void addEdge(int v, T edge, boolean directed) {
        adj[v].add(edge);
        indegree[edge.w()]++;

        if (!directed)
            addEdge(edge.w(), (T)edge.reverse(), true);
        else
            E++;
    }

    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<T> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    @Deprecated
    public int degree(int v) {
        return outdegree(v);
    }

    @Override
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    @Override
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    @Override
    public Graph<T> reverse() {
        GraphImpl<T> reverse = new GraphImpl<>(V, directed);
        for (int v = 0; v < V; v++) {
            for (T e : adj(v)) {
                int w = e.w();
                reverse.addEdge(w, (T)e.reverse());
            }
        }
        return reverse;
    }

    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" vertices, ").append(E).append(" edges ").append(NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (T edge : adj(v)) {
                s.append(edge).append(" ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    /**
     * Unit tests the {@code Graph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph<Edge> G = GraphReader.readGraph(in);
        StdOut.println(G);
    }
}

/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
