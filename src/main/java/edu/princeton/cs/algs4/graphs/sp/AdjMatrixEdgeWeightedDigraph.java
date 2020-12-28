/******************************************************************************
 *  Compilation:  javac AdjMatrixEdgeWeightedDigraph.java
 *  Execution:    java AdjMatrixEdgeWeightedDigraph V E
 *  Dependencies: StdOut.java
 *
 *  An edge-weighted digraph, implemented using an adjacency matrix.
 *  Parallel edges are disallowed; self-loops are allowed.
 *  
 ******************************************************************************/

package edu.princeton.cs.algs4.graphs.sp;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.mst.WeightedEdge;
import edu.princeton.cs.algs4.utils.io.StdOut;
import edu.princeton.cs.algs4.utils.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

/**
 *  The {@code AdjMatrixEdgeWeightedDigraph} class represents a edge-weighted
 *  digraph of vertices named 0 through <em>V</em> - 1, where each
 *  directed edge is of type {@link WeightedEdge} and has a real-valued weight.
 *  It supports the following two primary operations: add a directed edge
 *  to the digraph and iterate over all of edges incident from a given vertex.
 *  It also provides
 *  methods for returning the number of vertices <em>V</em> and the number
 *  of edges <em>E</em>. Parallel edges are disallowed; self-loops are permitted.
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
public class AdjMatrixEdgeWeightedDigraph implements Graph<WeightedEdge> {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private final WeightedEdge[][] adj;
    
    /**
     * Initializes an empty edge-weighted digraph with {@code V} vertices and 0 edges.
     * @param V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public AdjMatrixEdgeWeightedDigraph(int V) {
        checkArgument(V >= 0, "number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        this.adj = new WeightedEdge[V][V];
    }

    /**
     * Initializes a random edge-weighted digraph with {@code V} vertices and <em>E</em> edges.
     * @param V the number of vertices
     * @param E the number of edges
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}
     */
    public AdjMatrixEdgeWeightedDigraph(int V, int E) {
        this(V);
        checkArgument(E >= 0, "number of edges must be nonnegative");
        checkArgument(E <= V*V, "too many edges");

        // can be inefficient
        while (this.E != E) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = Math.round(100 * StdRandom.uniform()) / 100.0;
            addEdge(v, new WeightedEdge(v, w, weight));
        }
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
     * @param e the edge
     */
    public void addEdge(int v, WeightedEdge e) {
        checkArgument(v == e.v());
        int w = e.w();
        validateVertex(v);
        validateVertex(w);
        if (adj[v][w] == null) {
            E++;
            adj[v][w] = e;
        }
    }

    /**
     * Returns the directed edges incident from vertex {@code v}.
     * @param v the vertex
     * @return the directed edges incident from vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<WeightedEdge> adj(int v) {
        validateVertex(v);
        return new AdjIterator(v);
    }

    @Override
    public int outdegree(int v) {
        int degree = 0;
        for (int w = 0;w < V;w++) {
            if (adj[v][w] != null) degree++;
        }
        return degree;
    }

    @Override
    public int indegree(int v) {
        int degree = 0;
        for (int w = 0;w < V;w++) {
            if (adj[w][v] != null) degree++;
        }
        return degree;
    }

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public Graph<WeightedEdge> reverse() {
        throw new UnsupportedOperationException();
    }

    // support iteration over graph vertices
    private class AdjIterator implements Iterator<WeightedEdge>, Iterable<WeightedEdge> {
        private final int v;
        private int w = 0;

        public AdjIterator(int v) {
            this.v = v;
        }

        public Iterator<WeightedEdge> iterator() {
            return this;
        }

        public boolean hasNext() {
            while (w < V) {
                if (adj[v][w] != null) return true;
                w++;
            }
            return false;
        }

        public WeightedEdge next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return adj[v][w++];
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
            for (WeightedEdge e : adj(v)) {
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


    /**
     * Unit tests the {@code AdjMatrixEdgeWeightedDigraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        Graph<WeightedEdge> G = new AdjMatrixEdgeWeightedDigraph(V, E);
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
