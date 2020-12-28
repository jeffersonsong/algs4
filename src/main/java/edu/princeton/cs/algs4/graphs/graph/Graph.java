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

package edu.princeton.cs.algs4.graphs.graph;

import edu.princeton.cs.algs4.fundamentals.bag.Bag;

/**
 * The {@code Graph} class represents an undirected graph of vertices
 * named 0 through <em>V</em> – 1.
 * It supports the following two primary operations: add an edge to the graph,
 * iterate over all of the vertices adjacent to a vertex. It also provides
 * methods for returning the degree of a vertex, the number of vertices
 * <em>V</em> in the graph, and the number of edges <em>E</em> in the graph.
 * Parallel edges and self-loops are permitted.
 * By convention, a self-loop <em>v</em>-<em>v</em> appears in the
 * adjacency list of <em>v</em> twice and contributes two to the degree
 * of <em>v</em>.
 * <p>
 * This implementation uses an <em>adjacency-lists representation</em>, which
 * is a vertex-indexed array of {@link Bag} objects.
 * It uses &Theta;(<em>E</em> + <em>V</em>) space, where <em>E</em> is
 * the number of edges and <em>V</em> is the number of vertices.
 * All instance methods take &Theta;(1) time. (Though, iterating over
 * the vertices returned by {@link #adj(int)} takes time proportional
 * to the degree of the vertex.)
 * Constructing an empty graph with <em>V</em> vertices takes
 * &Theta;(<em>V</em>) time; constructing a graph with <em>E</em> edges
 * and <em>V</em> vertices takes &Theta;(<em>E</em> + <em>V</em>) time.
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a>
 * of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public interface Graph<T extends Edge> {
    boolean isDirected();
    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    int V();

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    int E();

    void addEdge(int v, T edge);

    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    Iterable<T> adj(int v);

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    int outdegree(int v);

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    int indegree(int v);

    /**
     * Returns the reverse of the digraph.
     *
     * @return the reverse of the digraph
     */
    Graph<T> reverse();
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
