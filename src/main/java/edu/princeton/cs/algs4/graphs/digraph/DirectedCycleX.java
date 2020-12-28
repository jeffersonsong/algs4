/******************************************************************************
 *  Compilation:  javac DirectedCycleX.java
 *  Execution:    java DirectedCycleX V E F
 *  Dependencies: Queue.java Digraph.java Stack.java
 *
 *  Find a directed cycle in a digraph, using a nonrecursive, queue-based
 *  algorithm. Runs in O(E + V) time.
 *
 ******************************************************************************/

package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.fundamentals.queue.LinkedQueue;
import edu.princeton.cs.algs4.fundamentals.queue.Queue;
import edu.princeton.cs.algs4.fundamentals.stack.LinkedStack;
import edu.princeton.cs.algs4.fundamentals.stack.Stack;
import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.UnWeightedEdge;
import edu.princeton.cs.algs4.graphs.graph.impl.DigraphGenerator;
import edu.princeton.cs.algs4.utils.StdRandom;
import edu.princeton.cs.algs4.utils.io.StdOut;

import static edu.princeton.cs.algs4.utils.ArrayUtils.newIntArray;
import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

/**
 *  The {@code DirectedCycleX} class represents a data type for 
 *  determining whether a digraph has a directed cycle.
 *  The <em>hasCycle</em> operation determines whether the digraph has
 *  a simple directed cycle and, if so, the <em>cycle</em> operation
 *  returns one.
 *  <p>
 *  This implementation uses a nonrecursive, queue-based algorithm.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>
 *  (in the worst case),
 *  where <em>V</em> is the number of vertices and <em>E</em> is the
 *  number of edges.
 *  Each instance method takes &Theta;(1) time.
 *  It uses &Theta;(<em>V</em>) extra space (not including the digraph).
 *  <p>
 *  See {@link DirectedCycle} for a recursive version that uses depth-first search.
 *  See {@link Topological} or {@link TopologicalX} to compute a topological order
 *  when the digraph is acyclic.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

public class DirectedCycleX<T extends Edge> {
    private Stack<T> cycle;     // the directed cycle; null if digraph is acyclic

    public DirectedCycleX(Graph<T> G) {
        checkArgument(G.isDirected());
        // indegrees of remaining vertices
        int[] indegree = newIntArray(G.V(), G::indegree);

        // initialize queue to contain all vertices with indegree = 0
        Queue<Integer> queue = new LinkedQueue<>();
        for (int v = 0; v < G.V(); v++) {
            if (indegree[v] == 0) queue.enqueue(v);
        }

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (T e : G.adj(v)) {
                int w = e.other(v);
                indegree[w]--;
                if (indegree[w] == 0) queue.enqueue(w);
            }
        }

        // there is a directed cycle in subgraph of vertices with indegree >= 1.
        Edge[] edgeTo = new Edge[G.V()];
        int root = -1;  // any vertex with indegree >= -1
        for (int v = 0; v < G.V(); v++) {
            if (indegree[v] == 0) continue;
            else root = v;
            for (T e : G.adj(v)) {
                int w = e.other(v);
                if (indegree[w] > 0) {
                    edgeTo[w] = e;
                }
            }
        }

        if (root != -1) {
            // find any vertex on cycle
            boolean[] visited = new boolean[G.V()];
            while (!visited[root]) {
                visited[root] = true;
                root = edgeTo[root].other(root);
            }

            // extract cycle
            cycle = new LinkedStack<>();
            int v = root;
            do {
                cycle.push((T)edgeTo[v]);
                v = edgeTo[v].other(v);
            } while (v != root);
        }

        assert check();
    }

    /**
     * Returns a directed cycle if the digraph has a directed cycle, and {@code null} otherwise.
     * @return a directed cycle (as an iterable) if the digraph has a directed cycle,
     *    and {@code null} otherwise
     */
    public Iterable<T> cycle() {
        return cycle;
    }

    /**
     * Does the digraph have a directed cycle?
     * @return {@code true} if the digraph has a directed cycle, {@code false} otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    // certify that digraph is either acyclic or has a directed cycle
    private boolean check() {
        // edge-weighted digraph is cyclic
        if (hasCycle()) {
            // verify cycle
            T first = null, last = null;
            for (T e : cycle()) {
                if (first == null) first = e;
                if (last != null) {
                    if (last.w() != e.v()) {
                        System.err.printf("cycle edges %s and %s not incident\n", last, e);
                        return false;
                    }
                }
                last = e;
            }

            if (last.w() != first.v()) {
                System.err.printf("cycle edges %s and %s not incident\n", last, first);
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // create random DAG with V vertices and E edges; then add F random edges
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        int F = Integer.parseInt(args[2]);
        Graph<Edge> G = DigraphGenerator.dag(V, E);

        // add F extra edges
        for (int i = 0; i < F; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            G.addEdge(v, new UnWeightedEdge(v, w));
        }

        StdOut.println(G);

        DirectedCycleX<Edge> finder = new DirectedCycleX<>(G);
        if (finder.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (Edge v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

        else {
            StdOut.println("No directed cycle");
        }
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
