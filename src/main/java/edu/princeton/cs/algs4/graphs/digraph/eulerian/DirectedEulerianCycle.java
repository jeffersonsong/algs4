/******************************************************************************
 *  Compilation:  javac DirectedEulerianCycle.java
 *  Execution:    java DirectedEulerianCycle V E
 *  Dependencies: Digraph.java Stack.java StdOut.java
 *                BreadthFirstPaths.java
 *                DigraphGenerator.java StdRandom.java
 *
 *  Find an Eulerian cycle in a digraph, if one exists.
 *
 ******************************************************************************/

package edu.princeton.cs.algs4.graphs.digraph.eulerian;

import edu.princeton.cs.algs4.fundamentals.stack.LinkedStack;
import edu.princeton.cs.algs4.fundamentals.stack.Stack;
import edu.princeton.cs.algs4.graphs.digraph.DigraphGenerator;
import edu.princeton.cs.algs4.graphs.graph.*;
import edu.princeton.cs.algs4.graphs.graph.eulerian.EulerianCycle;
import edu.princeton.cs.algs4.graphs.graph.eulerian.EulerianPath;
import edu.princeton.cs.algs4.utils.io.StdOut;
import edu.princeton.cs.algs4.utils.io.In;
import edu.princeton.cs.algs4.utils.StdRandom;

import java.util.Iterator;

import static edu.princeton.cs.algs4.utils.ArrayUtils.newIndexArray;
import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

/**
 *  The {@code DirectedEulerianCycle} class represents a data type
 *  for finding an Eulerian cycle or path in a digraph.
 *  An <em>Eulerian cycle</em> is a cycle (not necessarily simple) that
 *  uses every edge in the digraph exactly once.
 *  <p>
 *  This implementation uses a nonrecursive depth-first search.
 *  The constructor takes &Theta;(<em>E</em> + <em>V</em>) time in the worst
 *  case, where <em>E</em> is the number of edges and <em>V</em> is the
 *  number of vertices
 *  Each instance method takes &Theta;(1) time.
 *  It uses &Theta;(<em>V</em>) extra space (not including the digraph).
 *  <p>
 *  To compute Eulerian paths in digraphs, see {@link DirectedEulerianPath}.
 *  To compute Eulerian cycles and paths in undirected graphs, see
 *  {@link EulerianCycle} and {@link EulerianPath}.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * 
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Nate Liu
 */
public class DirectedEulerianCycle<T extends EdgeNode> {
    private Stack<Integer> cycle = null;  // Eulerian cycle; null if no such cylce

    /**
     * Computes an Eulerian cycle in the specified digraph, if one exists.
     * 
     * @param G the digraph
     */
    public DirectedEulerianCycle(Graph<T> G) {
        checkArgument(G.isDirected());
        // must have at least one edge
        if (G.E() == 0) return;

        // necessary condition: indegree(v) = outdegree(v) for each vertex v
        // (without this check, DFS might return a path instead of a cycle)
        for (int v = 0; v < G.V(); v++)
            if (G.outdegree(v) != G.indegree(v))
                return;

        // create local view of adjacency lists, to iterate one vertex at a time
        Iterator<T>[] adj = (Iterator<T>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++) {
            adj[v] = G.adj(v).iterator();
        }

        // initialize stack with any non-isolated vertex
        int s = nonIsolatedVertex(G);
        Stack<Integer> stack = new LinkedStack<>();
        stack.push(s);

        // greedily add to putative cycle, depth-first search style
        cycle = new LinkedStack<>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (adj[v].hasNext()) {
                stack.push(v);
                v = adj[v].next().to();
            }
            // add vertex with no more leaving edges to cycle
            cycle.push(v);
        }

        // check if all edges have been used
        // (in case there are two or more vertex-disjoint Eulerian cycles)
        if (cycle.size() != G.E() + 1)
            cycle = null;

        assert certifySolution(G);
    }

    /**
     * Returns the sequence of vertices on an Eulerian cycle.
     * 
     * @return the sequence of vertices on an Eulerian cycle;
     *         {@code null} if no such cycle
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }

    /**
     * Returns true if the digraph has an Eulerian cycle.
     * 
     * @return {@code true} if the digraph has an Eulerian cycle;
     *         {@code false} otherwise
     */
    public boolean hasEulerianCycle() {
        return cycle != null;
    }

    // returns any non-isolated vertex; -1 if no such vertex
    private static <T extends EdgeNode> int nonIsolatedVertex(Graph<T> G) {
        for (int v = 0; v < G.V(); v++)
            if (G.outdegree(v) > 0)
                return v;
        return -1;
    }

    /**************************************************************************
     *
     *  The code below is solely for testing correctness of the data type.
     *
     **************************************************************************/

    // Determines whether a digraph has an Eulerian cycle using necessary
    // and sufficient conditions (without computing the cycle itself):
    //    - at least one edge
    //    - indegree(v) = outdegree(v) for every vertex
    //    - the graph is connected, when viewed as an undirected graph
    //      (ignoring isolated vertices)
    private static <T extends EdgeNode> boolean satisfiesNecessaryAndSufficientConditions(Graph<T> G) {

        // Condition 0: at least 1 edge
        if (G.E() == 0) return false;

        // Condition 1: indegree(v) == outdegree(v) for every vertex
        for (int v = 0; v < G.V(); v++)
            if (G.outdegree(v) != G.indegree(v))
                return false;

        // Condition 2: graph is connected, ignoring isolated vertices
        Graph<T> H = new GraphImpl<>(G.V(), G.isDirected());
        for (int v = 0; v < G.V(); v++)
            for (T e : G.adj(v))
                H.addEdge(v, e);
        
        // check that all non-isolated vertices are conneted
        int s = nonIsolatedVertex(G);
        BreadthFirstPaths<T> bfs = new BreadthFirstPaths<>(H, s);
        for (int v = 0; v < G.V(); v++)
            if (H.degree(v) > 0 && !bfs.hasPathTo(v))
                return false;

        return true;
    }

    // check that solution is correct
    private boolean certifySolution(Graph<T> G) {

        // internal consistency check
        if (hasEulerianCycle() == (cycle() == null)) return false;

        // hashEulerianCycle() returns correct value
        if (hasEulerianCycle() != satisfiesNecessaryAndSufficientConditions(G)) return false;

        // nothing else to check if no Eulerian cycle
        if (cycle == null) return true;

        // check that cycle() uses correct number of edges
        if (cycle.size() != G.E() + 1) return false;

        // check that cycle() is a directed cycle of G
        // TODO

        return true;
    }

    private static <T extends EdgeNode> void unitTest(Graph<T> G, String description) {
        StdOut.println(description);
        StdOut.println("-------------------------------------");
        StdOut.print(G);

        DirectedEulerianCycle<T> euler = new DirectedEulerianCycle<>(G);

        StdOut.print("Eulerian cycle: ");
        if (euler.hasEulerianCycle()) {
            for (int v : euler.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
        else {
            StdOut.println("none");
        }
        StdOut.println();
    }


    /**
     * Unit tests the {@code DirectedEulerianCycle} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);

        // Eulerian cycle
        Graph<UnweightedEdgeNode> G1 = DigraphGenerator.eulerianCycle(V, E);
        unitTest(G1, "Eulerian cycle");

        // Eulerian path
        Graph<UnweightedEdgeNode> G2 = DigraphGenerator.eulerianPath(V, E);
        unitTest(G2, "Eulerian path");

        // empty digraph
        Graph<UnweightedEdgeNode> G3 = new GraphImpl<>(V, true);
        unitTest(G3, "empty digraph");

        // self loop
        Graph<UnweightedEdgeNode> G4 = new GraphImpl<>(V, true);
        int v4 = StdRandom.uniform(V);
        G4.addEdge(v4, new UnweightedEdgeNode(v4));
        unitTest(G4, "single self loop");

        // union of two disjoint cycles
        Graph<UnweightedEdgeNode> H1 = DigraphGenerator.eulerianCycle(V/2, E/2);
        Graph<UnweightedEdgeNode> H2 = DigraphGenerator.eulerianCycle(V - V/2, E - E/2);
        int[] perm = newIndexArray(V);
        StdRandom.shuffle(perm);
        Graph<UnweightedEdgeNode> G5 = new GraphImpl<>(V, true);
        for (int v = 0; v < H1.V(); v++)
            for (UnweightedEdgeNode e : H1.adj(v)) {
                int w = e.to();
                G5.addEdge(perm[v], new UnweightedEdgeNode(perm[w]));
            }
        for (int v = 0; v < H2.V(); v++)
            for (UnweightedEdgeNode e : H2.adj(v)) {
                int w = e.to();
                G5.addEdge(perm[V / 2 + v], new UnweightedEdgeNode(perm[V / 2 + w]));
            }
        unitTest(G5, "Union of two disjoint cycles");

        // random digraph
        Graph<UnweightedEdgeNode> G6 = DigraphGenerator.simple(V, E);
        unitTest(G6, "simple digraph");

        // 4-vertex digraph
        Graph<UnweightedEdgeNode> G7 = GraphReader.readDigraph(new In("eulerianD.txt"));
        unitTest(G7, "4-vertex Eulerian digraph");
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
