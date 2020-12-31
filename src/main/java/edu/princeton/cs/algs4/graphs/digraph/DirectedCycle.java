/******************************************************************************
 *  Compilation:  javac DirectedCycle.java
 *  Execution:    java DirectedCycle input.txt
 *  Dependencies: Digraph.java Stack.java StdOut.java In.java
 *  Data files:   https://algs4.cs.princeton.edu/42digraph/tinyDG.txt
 *                https://algs4.cs.princeton.edu/42digraph/tinyDAG.txt
 *
 *  Finds a directed cycle in a digraph.
 *
 *  % java DirectedCycle tinyDG.txt 
 *  Directed cycle: 3 5 4 3 
 *
 *  %  java DirectedCycle tinyDAG.txt 
 *  No directed cycle
 *
 ******************************************************************************/

package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.fundamentals.stack.LinkedStack;
import edu.princeton.cs.algs4.fundamentals.stack.Stack;
import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.WeightedEdge;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphImpl;
import edu.princeton.cs.algs4.utils.StdRandom;
import edu.princeton.cs.algs4.utils.io.StdOut;

import static edu.princeton.cs.algs4.utils.ArrayUtils.newIndexArray;
import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

/**
 *  The {@code DirectedCycle} class represents a data type for 
 *  determining whether a digraph has a directed cycle.
 *  The <em>hasCycle</em> operation determines whether the digraph has
 *  a simple directed cycle and, if so, the <em>cycle</em> operation
 *  returns one.
 *  <p>
 *  This implementation uses depth-first search.
 *  The constructor takes &Theta;(<em>V</em> + <em>E</em>) time in the worst
 *  case, where <em>V</em> is the number of vertices and <em>E</em> is
 *  the number of edges.
 *  Each instance method takes &Theta;(1) time.
 *  It uses &Theta;(<em>V</em>) extra space (not including the digraph).
 *  <p>
 *  See {@link Topological} to compute a topological order if the
 *  digraph is acyclic.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class DirectedCycle<T extends Edge> {
    private final boolean[] marked;        // marked[v] = has vertex v been marked?
    private final Edge[] edgeTo;           // edgeTo[v] = previous vertex on path to v
    private final boolean[] onStack;       // onStack[v] = is vertex on the stack?
    private Stack<T> cycle;                // directed cycle (or null if no such cycle)

    /**
     * Determines whether the digraph {@code G} has a directed cycle and, if so,
     * finds such a cycle.
     * @param G the digraph
     */
    public DirectedCycle(Graph<T> G) {
        checkArgument(G.isDirected(), "Only applicable to directed graph.");

        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new Edge[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null) dfs(G, v);
    }

    // check that algorithm computes either the topological order or finds a directed cycle
    private void dfs(Graph<T> G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (T e : G.adj(v)) {
            int w = e.w();

            // short circuit if directed cycle found
            if (cycle != null) return;

            // found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new LinkedStack<>();

                T f = e;
                while (f.v() != w) {
                    cycle.push(f);
                    f = (T)edgeTo[f.v()];
                }
                cycle.push(f);
                assert check();
            }
        }
        onStack[v] = false;
    }

    /**
     * Does the digraph have a directed cycle?
     * @return {@code true} if the digraph has a directed cycle, {@code false} otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Returns a directed cycle if the digraph has a directed cycle, and {@code null} otherwise.
     * @return a directed cycle (as an iterable) if the digraph has a directed cycle,
     *    and {@code null} otherwise
     */
    public Iterable<T> cycle() {
        return cycle;
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

    /**
     * Unit tests the {@code DirectedCycle} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // create random DAG with V vertices and E edges; then add F random edges
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        int F = Integer.parseInt(args[2]);
        Graph<WeightedEdge> G = GraphImpl.digraph(V);
        int[] vertices = newIndexArray(V);
        StdRandom.shuffle(vertices);
        for (int i = 0; i < E; i++) {
            int v, w;
            do {
                v = StdRandom.uniform(V);
                w = StdRandom.uniform(V);
            } while (v >= w);
            double weight = StdRandom.uniform();
            G.addEdge(v, new WeightedEdge(v, w, weight));
        }

        // add F extra edges
        for (int i = 0; i < F; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = StdRandom.uniform(0.0, 1.0);
            G.addEdge(v, new WeightedEdge(v, w, weight));
        }

        StdOut.println(G);

        // find a directed cycle
        DirectedCycle<WeightedEdge> finder = new DirectedCycle<>(G);
        if (finder.hasCycle()) {
            StdOut.print("Cycle: ");
            for (WeightedEdge e : finder.cycle()) {
                StdOut.print(e + " ");
            }
            StdOut.println();
        }

        // or give topologial sort
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
