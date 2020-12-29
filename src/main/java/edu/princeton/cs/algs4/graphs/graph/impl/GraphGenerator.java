/******************************************************************************
 *  Compilation:  javac GraphGenerator.java
 *  Execution:    java GraphGenerator V E
 *  Dependencies: Graph.java
 *
 *  A graph generator.
 *
 *  For many more graph generators, see
 *  http://networkx.github.io/documentation/latest/reference/generators.html
 *
 ******************************************************************************/

package edu.princeton.cs.algs4.graphs.graph.impl;

import edu.princeton.cs.algs4.fundamentals.set.SET;
import edu.princeton.cs.algs4.fundamentals.set.SETImpl;
import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.UnWeightedEdge;
import edu.princeton.cs.algs4.sorting.pq.PQ;
import edu.princeton.cs.algs4.sorting.pq.PQIml;
import edu.princeton.cs.algs4.utils.io.StdOut;
import edu.princeton.cs.algs4.utils.StdRandom;

import static edu.princeton.cs.algs4.utils.ArrayUtils.*;
import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

/**
 *  The {@code GraphGenerator} class provides static methods for creating
 *  various graphs, including Erdos-Renyi random graphs, random bipartite
 *  graphs, random k-regular graphs, and random rooted trees.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class GraphGenerator {

    // this class cannot be instantiated
    private GraphGenerator() { }

    /**
     * Returns a random simple graph containing {@code V} vertices and {@code E} edges.
     * @param V the number of vertices
     * @param E the number of vertices
     * @return a random simple graph on {@code V} vertices, containing a total
     *     of {@code E} edges
     * @throws IllegalArgumentException if no such simple graph exists
     */
    public static Graph<Edge> simple(int V, int E) {
        checkArgument(E <= (long) V*(V-1)/2, "Too many edges");
        checkArgument(E >= 0, "Too few edges");
        Graph<Edge> G = GraphImpl.graph(V);
        SET<UnWeightedEdge> set = new SETImpl<>();
        while (G.E() < E) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            UnWeightedEdge e = new UnWeightedEdge(v, w);
            if ((v != w) && !set.contains(e)) {
                set.add(e);
                G.addEdge(v, new UnWeightedEdge(v, w));
            }
        }
        return G;
    }

    /**
     * Returns a random simple graph on {@code V} vertices, with an 
     * edge between any two vertices with probability {@code p}. This is sometimes
     * referred to as the Erdos-Renyi random graph model.
     * @param V the number of vertices
     * @param p the probability of choosing an edge
     * @return a random simple graph on {@code V} vertices, with an edge between
     *     any two vertices with probability {@code p}
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static Graph<Edge> simple(int V, double p) {
        checkArgument(p >= 0.0 && p <= 1.0, "Probability must be between 0 and 1");
        Graph<Edge> G = GraphImpl.graph(V);
        for (int v = 0; v < V; v++)
            for (int w = v+1; w < V; w++)
                if (StdRandom.bernoulli(p))
                    G.addEdge(v, new UnWeightedEdge(v, w));
        return G;
    }

    /**
     * Returns the complete graph on {@code V} vertices.
     * @param V the number of vertices
     * @return the complete graph on {@code V} vertices
     */
    public static Graph<Edge> complete(int V) {
        return simple(V, 1.0);
    }

    /**
     * Returns a complete bipartite graph on {@code V1} and {@code V2} vertices.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @return a complete bipartite graph on {@code V1} and {@code V2} vertices
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static Graph<Edge> completeBipartite(int V1, int V2) {
        return bipartite(V1, V2, V1*V2);
    }

    /**
     * Returns a random simple bipartite graph on {@code V1} and {@code V2} vertices
     * with {@code E} edges.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @param E the number of edges
     * @return a random simple bipartite graph on {@code V1} and {@code V2} vertices,
     *    containing a total of {@code E} edges
     * @throws IllegalArgumentException if no such simple bipartite graph exists
     */
    public static Graph<Edge> bipartite(int V1, int V2, int E) {
        checkArgument(E <= (long) V1*V2, "Too many edges");
        checkArgument(E >= 0, "Too few edges");
        Graph<Edge> G = GraphImpl.graph(V1 + V2);

        int[] vertices = newIndexArray(V1 + V2);
        StdRandom.shuffle(vertices);

        SET<UnWeightedEdge> set = new SETImpl<>();
        while (G.E() < E) {
            int i = StdRandom.uniform(V1);
            int j = V1 + StdRandom.uniform(V2);
            UnWeightedEdge e = new UnWeightedEdge(vertices[i], vertices[j]);
            if (!set.contains(e)) {
                set.add(e);
                G.addEdge(vertices[i], new UnWeightedEdge(vertices[i], vertices[j]));
            }
        }
        return G;
    }

    /**
     * Returns a random simple bipartite graph on {@code V1} and {@code V2} vertices,
     * containing each possible edge with probability {@code p}.
     * @param V1 the number of vertices in one partition
     * @param V2 the number of vertices in the other partition
     * @param p the probability that the graph contains an edge with one endpoint in either side
     * @return a random simple bipartite graph on {@code V1} and {@code V2} vertices,
     *    containing each possible edge with probability {@code p}
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static Graph<Edge> bipartite(int V1, int V2, double p) {
        checkArgument(p >= 0.0 && p <= 1.0, "Probability must be between 0 and 1");
        int[] vertices = newIndexArray(V1 + V2);
        StdRandom.shuffle(vertices);
        Graph<Edge> G = GraphImpl.graph(V1 + V2);
        for (int i = 0; i < V1; i++)
            for (int j = 0; j < V2; j++)
                if (StdRandom.bernoulli(p))
                    G.addEdge(vertices[i], new UnWeightedEdge(vertices[i],vertices[V1+j]));
        return G;
    }

    /**
     * Returns a path graph on {@code V} vertices.
     * @param V the number of vertices in the path
     * @return a path graph on {@code V} vertices
     */
    public static Graph<Edge> path(int V) {
        Graph<Edge> G = GraphImpl.graph(V);
        int[] vertices = newIndexArray(V);
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V-1; i++) {
            G.addEdge(vertices[i], new UnWeightedEdge(vertices[i], vertices[i+1]));
        }
        return G;
    }

    /**
     * Returns a complete binary tree graph on {@code V} vertices.
     * @param V the number of vertices in the binary tree
     * @return a complete binary tree graph on {@code V} vertices
     */
    public static Graph<Edge> binaryTree(int V) {
        Graph<Edge> G = GraphImpl.graph(V);
        int[] vertices = newIndexArray(V);
        StdRandom.shuffle(vertices);
        for (int i = 1; i < V; i++) {
            G.addEdge(vertices[i], new UnWeightedEdge(vertices[i], vertices[(i-1)/2]));
        }
        return G;
    }

    /**
     * Returns a cycle graph on {@code V} vertices.
     * @param V the number of vertices in the cycle
     * @return a cycle graph on {@code V} vertices
     */
    public static Graph<Edge> cycle(int V) {
        Graph<Edge> G = GraphImpl.graph(V);
        int[] vertices = newIndexArray(V);
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V-1; i++) {
            G.addEdge(vertices[i], new UnWeightedEdge(vertices[i], vertices[i+1]));
        }
        G.addEdge(vertices[V-1], new UnWeightedEdge(vertices[V-1], vertices[0]));
        return G;
    }

    /**
     * Returns an Eulerian cycle graph on {@code V} vertices.
     *
     * @param  V the number of vertices in the cycle
     * @param  E the number of edges in the cycle
     * @return a graph that is an Eulerian cycle on {@code V} vertices
     *         and {@code E} edges
     * @throws IllegalArgumentException if either {@code V <= 0} or {@code E <= 0}
     */
    public static Graph<Edge> eulerianCycle(int V, int E) {
        checkArgument(E > 0, "An Eulerian cycle must have at least one edge");
        checkArgument(V > 0, "An Eulerian cycle must have at least one vertex");
        Graph<Edge> G = GraphImpl.graph(V);
        int[] vertices = newIntArray(E, i->StdRandom.uniform(V));
        for (int i = 0; i < E-1; i++) {
            G.addEdge(vertices[i], new UnWeightedEdge(vertices[i],vertices[i+1]));
        }
        G.addEdge(vertices[E-1], new UnWeightedEdge(vertices[E-1], vertices[0]));
        return G;
    }

    /**
     * Returns an Eulerian path graph on {@code V} vertices.
     *
     * @param  V the number of vertices in the path
     * @param  E the number of edges in the path
     * @return a graph that is an Eulerian path on {@code V} vertices
     *         and {@code E} edges
     * @throws IllegalArgumentException if either {@code V <= 0} or {@code E < 0}
     */
    public static Graph<Edge> eulerianPath(int V, int E) {
        checkArgument(E >= 0, "negative number of edges");
        checkArgument(V > 0, "An Eulerian path must have at least one vertex");
        Graph<Edge> G = GraphImpl.graph(V);
        int[] vertices = newIntArray(E+1, i->StdRandom.uniform(V));
        for (int i = 0; i < E; i++) {
            G.addEdge(vertices[i], new UnWeightedEdge(vertices[i], vertices[i+1]));
        }
        return G;
    }

    /**
     * Returns a wheel graph on {@code V} vertices.
     * @param V the number of vertices in the wheel
     * @return a wheel graph on {@code V} vertices: a single vertex connected to
     *     every vertex in a cycle on {@code V-1} vertices
     */
    public static Graph<Edge> wheel(int V) {
        checkArgument(V > 1, "Number of vertices must be at least 2");
        Graph<Edge> G = GraphImpl.graph(V);
        int[] vertices = newIndexArray(V);
        StdRandom.shuffle(vertices);

        // simple cycle on V-1 vertices
        for (int i = 1; i < V-1; i++) {
            G.addEdge(vertices[i], new UnWeightedEdge(vertices[i], vertices[i+1]));
        }
        G.addEdge(vertices[V-1], new UnWeightedEdge(vertices[V-1],vertices[1]));

        // connect vertices[0] to every vertex on cycle
        for (int i = 1; i < V; i++) {
            G.addEdge(vertices[0], new UnWeightedEdge(vertices[0], vertices[i]));
        }

        return G;
    }

    /**
     * Returns a star graph on {@code V} vertices.
     * @param V the number of vertices in the star
     * @return a star graph on {@code V} vertices: a single vertex connected to
     *     every other vertex
     */
    public static Graph<Edge> star(int V) {
        checkArgument(V > 0, "Number of vertices must be at least 1");
        Graph<Edge> G = GraphImpl.graph(V);
        int[] vertices = newIndexArray(V);
        StdRandom.shuffle(vertices);

        // connect vertices[0] to every other vertex
        for (int i = 1; i < V; i++) {
            G.addEdge(vertices[0], new UnWeightedEdge(vertices[0], vertices[i]));
        }

        return G;
    }

    /**
     * Returns a uniformly random {@code k}-regular graph on {@code V} vertices
     * (not necessarily simple). The graph is simple with probability only about e^(-k^2/4),
     * which is tiny when k = 14.
     *
     * @param V the number of vertices in the graph
     * @param k degree of each vertex
     * @return a uniformly random {@code k}-regular graph on {@code V} vertices.
     */
    public static Graph<Edge> regular(int V, int k) {
        checkArgument(V*k % 2 == 0, "Number of vertices * k must be even");
        Graph<Edge> G = GraphImpl.graph(V);

        // create k copies of each vertex
        int[] vertices = new int[V*k];
        for (int v = 0; v < V; v++) {
            for (int j = 0; j < k; j++) {
                vertices[v + V*j] = v;
            }
        }

        // pick a random perfect matching
        StdRandom.shuffle(vertices);
        for (int i = 0; i < V*k/2; i++) {
            G.addEdge(vertices[2*i], new UnWeightedEdge(vertices[2*i], vertices[2*i + 1]));
        }
        return G;
    }

    // http://www.proofwiki.org/wiki/Labeled_Tree_from_Prüfer_Sequence
    // http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.36.6484&rep=rep1&type=pdf
    /**
     * Returns a uniformly random tree on {@code V} vertices.
     * This algorithm uses a Prufer sequence and takes time proportional to <em>V log V</em>.
     * @param V the number of vertices in the tree
     * @return a uniformly random tree on {@code V} vertices
     */
    public static Graph<Edge> tree(int V) {
        Graph<Edge> G = GraphImpl.graph(V);

        // special case
        if (V == 1) return G;

        // Cayley's theorem: there are V^(V-2) labeled trees on V vertices
        // Prufer sequence: sequence of V-2 values between 0 and V-1
        // Prufer's proof of Cayley's theorem: Prufer sequences are in 1-1
        // with labeled trees on V vertices
        int[] prufer = newIntArray(V-2, i->StdRandom.uniform(V));

        // degree of vertex v = 1 + number of times it appers in Prufer sequence
        int[] degree = newIntArray(V, 1);
        for (int i = 0; i < V-2; i++)
            degree[prufer[i]]++;

        // pq contains all vertices of degree 1
        PQ<Integer> pq = PQIml.minPQ();
        for (int v = 0; v < V; v++)
            if (degree[v] == 1) pq.add(v);

        // repeatedly delMin() degree 1 vertex that has the minimum index
        for (int i = 0; i < V-2; i++) {
            int v = pq.poll();
            G.addEdge(v, new UnWeightedEdge(v, prufer[i]));
            degree[v]--;
            degree[prufer[i]]--;
            if (degree[prufer[i]] == 1) pq.add(prufer[i]);
        }
        int v;
        G.addEdge(v=pq.poll(), new UnWeightedEdge(v, pq.poll()));
        return G;
    }

    /**
     * Unit tests the {@code GraphGenerator} library.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        int V1 = V/2;
        int V2 = V - V1;

        StdOut.println("complete graph");
        StdOut.println(complete(V));
        StdOut.println();

        StdOut.println("simple");
        StdOut.println(simple(V, E));
        StdOut.println();

        StdOut.println("Erdos-Renyi");
        double p = (double) E / (V*(V-1)/2.0);
        StdOut.println(simple(V, p));
        StdOut.println();

        StdOut.println("complete bipartite");
        StdOut.println(completeBipartite(V1, V2));
        StdOut.println();

        StdOut.println("bipartite");
        StdOut.println(bipartite(V1, V2, E));
        StdOut.println();

        StdOut.println("Erdos Renyi bipartite");
        double q = (double) E / (V1*V2);
        StdOut.println(bipartite(V1, V2, q));
        StdOut.println();

        StdOut.println("path");
        StdOut.println(path(V));
        StdOut.println();

        StdOut.println("cycle");
        StdOut.println(cycle(V));
        StdOut.println();

        StdOut.println("binary tree");
        StdOut.println(binaryTree(V));
        StdOut.println();

        StdOut.println("tree");
        StdOut.println(tree(V));
        StdOut.println();

        StdOut.println("4-regular");
        StdOut.println(regular(V, 4));
        StdOut.println();

        StdOut.println("star");
        StdOut.println(star(V));
        StdOut.println();

        StdOut.println("wheel");
        StdOut.println(wheel(V));
        StdOut.println();
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
