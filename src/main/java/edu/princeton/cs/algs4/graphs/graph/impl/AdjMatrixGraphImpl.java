/******************************************************************************
 *  Compilation:  javac AdjMatrixEdgeWeightedDigraph.java
 *  Execution:    java AdjMatrixEdgeWeightedDigraph V E
 *  Dependencies: StdOut.java
 *
 *  An edge-weighted digraph, implemented using an adjacency matrix.
 *  Parallel edges are disallowed; self-loops are allowed.
 *  
 ******************************************************************************/

package edu.princeton.cs.algs4.graphs.graph.impl;

import edu.princeton.cs.algs4.fundamentals.dataabstract.DenseMatrix;
import edu.princeton.cs.algs4.fundamentals.dataabstract.Matrix;
import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.WeightedEdge;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

/**
 *  The {@code AdjMatrixGraphImpl} class represents an graph of vertices named 0 through <em>V</em> – 1.
 *  t can be undirected or directed graph.
 *  <p>
 *  This implementation uses an adjacency-matrix representation.
 *  All operations take constant time (in the worst case) except
 *  iterating over the edges incident from a given vertex, which takes
 *  time proportional to <em>V</em>.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class AdjMatrixGraphImpl<T extends Edge> implements Graph<T> {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final boolean directed;   /* is the graph directed? */
    private final int V;
    private int E;
    private final Matrix<T> adj;

    /**
     * Create an empty directed graph.
     * @param V number of vertices.
     * @param <T> edge type.
     * @return Empty directed graph.
     */
    public static <T extends Edge> Graph<T> digraph(int V) {
        return new AdjMatrixGraphImpl<>(V, true);
    }

    /**
     * Create an empty undirected graph.
     * @param V number of vertices.
     * @param <T> edge type.
     * @return Empty undirected graph.
     */
    public static <T extends Edge> Graph<T> graph(int V) {
        return new AdjMatrixGraphImpl<>(V, false);
    }

    /**
     * Initializes an empty edge-weighted digraph with {@code V} vertices and 0 edges.
     * @param V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public AdjMatrixGraphImpl(int V, boolean directed) {
        checkArgument(V >= 0, "number of vertices must be nonnegative");
        this.directed = directed;
        this.V = V;
        this.E = 0;
        // It can be implemented as sparse matrix.
        this.adj = new DenseMatrix<>(V, V);
    }

    /**
     * Returns the number of vertices in the edge-weighted digraph.
     * @return the number of vertices in the edge-weighted digraph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in the edge-weighted digraph.
     * @return the number of edges in the edge-weighted digraph
     */
    public int E() {
        return E;
    }

    /**
     * Adds the directed edge {@code e} to the edge-weighted digraph (if there
     * is not already an edge with the same endpoints).
     * @param edge the edge
     */
    public void addEdge(int v, T edge) {
        checkArgument(v == edge.v());
        validateVertex(v);
        validateVertex(edge.w());
        addEdge(v, edge, this.directed);
    }

    private void addEdge(int v, T edge, boolean directed) {
        int w = edge.w();
        setAdj(v, w, edge);

        if (!directed)
            addEdge(edge.w(), (T)edge.reverse(), true);
        else
            E++;
    }

    /**
     * Returns the directed edges incident from vertex {@code v}.
     * @param v the vertex
     * @return the directed edges incident from vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<T> adj(int v) {
        validateVertex(v);
        return new AdjIterator(v);
    }

    @Override
    public int outdegree(int v) {
        int degree = 0;
        for (int w = 0;w < V;w++) {
            if (adj(v, w) != null) degree++;
        }
        return degree;
    }

    @Override
    public int indegree(int v) {
        int degree = 0;
        for (int w = 0;w < V;w++) {
            if (adj(w, v) != null) degree++;
        }
        return degree;
    }

    private T adj(int v, int w) {
        return adj.get(v, w);
    }

    private void setAdj(int v, int w, T e) {
        adj.set(v, w, e);
    }

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public Graph<T> reverse() {
        Graph<T> reverse = new AdjMatrixGraphImpl<T>(V, directed);
        for (int v = 0; v < V; v++) {
            for (T e : adj(v)) {
                int w = e.w();
                reverse.addEdge(w, (T)e.reverse());
            }
        }
        return reverse;
    }

    // support iteration over graph vertices
    private class AdjIterator implements Iterator<T>, Iterable<T> {
        private final int v;
        private int w = 0;

        public AdjIterator(int v) {
            this.v = v;
        }

        public Iterator<T> iterator() {
            return this;
        }

        public boolean hasNext() {
            while (w < V) {
                if (adj(v, w) != null) return true;
                w++;
            }
            return false;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return adj(v, w++);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Returns a string representation of the edge-weighted digraph. This method takes
     * time proportional to <em>V</em><sup>2</sup>.
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *   followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" ").append(E).append(NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (T e : adj(v)) {
                s.append(e).append("  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        checkArgument(v >= 0 && v < V, "vertex " + v + " is not between 0 and " + (V-1));
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
