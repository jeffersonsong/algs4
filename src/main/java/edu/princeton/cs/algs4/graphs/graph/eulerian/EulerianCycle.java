/******************************************************************************
 *  Compilation:  javac EulerianCycle.java
 *  Execution:    java  EulerianCycle V E
 *  Dependencies: Graph.java Stack.java StdOut.java
 *
 *  Find an Eulerian cycle in a graph, if one exists.
 *
 *  Runs in O(E + V) time.
 *
 *  This implementation is tricker than the one for digraphs because
 *  when we use edge v-w from v's adjacency list, we must be careful
 *  not to use the second copy of the edge from w's adjaceny list.
 *
 ******************************************************************************/

package edu.princeton.cs.algs4.graphs.graph.eulerian;

import edu.princeton.cs.algs4.fundamentals.queue.LinkedQueue;
import edu.princeton.cs.algs4.fundamentals.queue.Queue;
import edu.princeton.cs.algs4.fundamentals.stack.LinkedStack;
import edu.princeton.cs.algs4.fundamentals.stack.Stack;
import edu.princeton.cs.algs4.graphs.digraph.eulerian.DirectedEulerianCycle;
import edu.princeton.cs.algs4.graphs.digraph.eulerian.DirectedEulerianPath;
import edu.princeton.cs.algs4.graphs.graph.*;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphGenerator;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphImpl;
import edu.princeton.cs.algs4.utils.StdRandom;
import edu.princeton.cs.algs4.utils.io.StdOut;

import static edu.princeton.cs.algs4.utils.ArrayUtils.newArray;
import static edu.princeton.cs.algs4.utils.ArrayUtils.newIndexArray;

/**
 *  The {@code EulerianCycle} class represents a data type
 *  for finding an Eulerian cycle or path in a graph.
 *  An <em>Eulerian cycle</em> is a cycle (not necessarily simple) that
 *  uses every edge in the graph exactly once.
 *  <p>
 *  This implementation uses a nonrecursive depth-first search.
 *  The constructor takes &Theta;(<em>E</em> + <em>V</em>) time in the worst
 *  case, where <em>E</em> is the number of edges and <em>V</em> is the
 *  number of vertices
 *  Each instance method takes &Theta;(1) time.
 *  It uses &Theta;(<em>E</em> + <em>V</em>) extra space in the worst case
 *  (not including the graph).
 *  <p>
 *  To compute Eulerian paths in graphs, see {@link EulerianPath}.
 *  To compute Eulerian cycles and paths in digraphs, see
 *  {@link DirectedEulerianCycle} and {@link DirectedEulerianPath}.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  https://www.geeksforgeeks.org/eulerian-path-and-circuit/
 * 
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Nate Liu
 */
public class EulerianCycle<T extends Edge> {
    private Stack<Integer> cycle = new LinkedStack<>();  // Eulerian cycle; null if no such cycle

    // an undirected edge, with a field to indicate whether the edge has already been used
    private static class MyEdge extends UnWeightedEdge {
        private boolean isUsed;

        public MyEdge(int v, int w) {
            super(v, w);
            isUsed = false;
        }
    }

    /**
     * Computes an Eulerian cycle in the specified graph, if one exists.
     * 
     * @param G the graph
     */
    public EulerianCycle(Graph<T> G) {

        // must have at least one edge
        if (G.E() == 0) return;

        // necessary condition: all vertices have even degree
        // (this test is needed or it might find an Eulerian path instead of cycle)
        for (int v = 0; v < G.V(); v++) 
            if (G.outdegree(v) % 2 != 0)
                return;

        // create local view of adjacency lists, to iterate one vertex at a time
        // the helper Edge data type is used to avoid exploring both copies of an edge v-w
        Queue<MyEdge>[] adj = newArray(G.V(), v->new LinkedQueue<>());

        for (int v = 0; v < G.V(); v++) {
            int selfLoops = 0;
            for (T l : G.adj(v)) {
                int w = l.w();
                // careful with self loops
                if (v == w) {
                    if (selfLoops % 2 == 0) {
                        MyEdge e = new MyEdge(v, w);
                        adj[v].enqueue(e);
                        adj[w].enqueue(e);
                    }
                    selfLoops++;
                }
                else if (v < w) {
                    MyEdge e = new MyEdge(v, w);
                    adj[v].enqueue(e);
                    adj[w].enqueue(e);
                }
            }
        }

        // initialize stack with any non-isolated vertex
        int s = nonIsolatedVertex(G);
        Stack<Integer> stack = new LinkedStack<>();
        stack.push(s);

        // greedily search through edges in iterative DFS style
        cycle = new LinkedStack<>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (!adj[v].isEmpty()) {
                MyEdge edge = adj[v].dequeue();
                if (edge.isUsed) continue;
                edge.isUsed = true;
                stack.push(v);
                v = edge.other(v);
            }
            // push vertex with no more leaving edges to cycle
            cycle.push(v);
        }

        // check if all edges are used
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
     * Returns true if the graph has an Eulerian cycle.
     * 
     * @return {@code true} if the graph has an Eulerian cycle;
     *         {@code false} otherwise
     */
    public boolean hasEulerianCycle() {
        return cycle != null;
    }

    // returns any non-isolated vertex; -1 if no such vertex
    private static int nonIsolatedVertex(Graph G) {
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

    // Determines whether a graph has an Eulerian cycle using necessary
    // and sufficient conditions (without computing the cycle itself):
    //    - at least one edge
    //    - degree(v) is even for every vertex v
    //    - the graph is connected (ignoring isolated vertices)
    private static <T extends Edge> boolean satisfiesNecessaryAndSufficientConditions(Graph<T> G) {

        // Condition 0: at least 1 edge
        if (G.E() == 0) return false;

        // Condition 1: degree(v) is even for every vertex
        for (int v = 0; v < G.V(); v++)
            if (G.outdegree(v) % 2 != 0)
                return false;

        // Condition 2: graph is connected, ignoring isolated vertices
        int s = nonIsolatedVertex(G);
        BreadthFirstPaths<T> bfs = new BreadthFirstPaths<>(G, s);
        for (int v = 0; v < G.V(); v++)
            if (G.outdegree(v) > 0 && !bfs.hasPathTo(v))
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

        // check that cycle() is a cycle of G
        // TODO

        // check that first and last vertices in cycle() are the same
        int first = -1, last = -1;
        for (int v : cycle()) {
            if (first == -1) first = v;
            last = v;
        }
        if (first != last) return false;

        return true;
    }

    private static <T extends Edge> void unitTest(Graph<T> G, String description) {
        StdOut.println(description);
        StdOut.println("-------------------------------------");
        StdOut.print(G);

        EulerianCycle<T> euler = new EulerianCycle<>(G);

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
     * Unit tests the {@code EulerianCycle} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);

        // Eulerian cycle
        Graph<Edge> G1 = GraphGenerator.eulerianCycle(V, E);
        unitTest(G1, "Eulerian cycle");

        // Eulerian path
        Graph<Edge> G2 = GraphGenerator.eulerianPath(V, E);
        unitTest(G2, "Eulerian path");

        // empty graph
        Graph<Edge> G3 = new GraphImpl<>(V,false);
        unitTest(G3, "empty graph");

        // self loop
        Graph<Edge> G4 = new GraphImpl<>(V, false);
        int v4 = StdRandom.uniform(V);
        G4.addEdge(v4, new UnWeightedEdge(v4, v4));
        unitTest(G4, "single self loop");

        // union of two disjoint cycles
        Graph<Edge> H1 = GraphGenerator.eulerianCycle(V/2, E/2);
        Graph<Edge> H2 = GraphGenerator.eulerianCycle(V - V/2, E - E/2);
        int[] perm = newIndexArray(V);
        StdRandom.shuffle(perm);
        Graph<Edge> G5 = new GraphImpl<>(V, false);
        for (int v = 0; v < H1.V(); v++)
            for (Edge e : H1.adj(v)) {
                int w = e.w();
                G5.addEdge(perm[v], new UnWeightedEdge(perm[v],perm[w]));
            }
        for (int v = 0; v < H2.V(); v++)
            for (Edge e  : H2.adj(v)) {
                int w = e.w();
                G5.addEdge(perm[V / 2 + v], new UnWeightedEdge(perm[V / 2 + v],perm[V / 2 + w]));
            }
        unitTest(G5, "Union of two disjoint cycles");

        // random digraph
        Graph<Edge> G6 = GraphGenerator.simple(V, E);
        unitTest(G6, "simple graph");
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
