/******************************************************************************
 *  Compilation:  javac DirectedEulerianPath.java
 *  Execution:    java DirectedEulerianPath V E
 *  Dependencies: Digraph.java Stack.java StdOut.java
 *                BreadthFirstPaths.java
 *                DigraphGenerator.java StdRandom.java
 *
 *  Find an Eulerian path in a digraph, if one exists.
 *
 ******************************************************************************/

package edu.princeton.cs.algs4.graphs.digraph.eulerian;

import edu.princeton.cs.algs4.fundamentals.stack.LinkedStack;
import edu.princeton.cs.algs4.fundamentals.stack.Stack;
import edu.princeton.cs.algs4.graphs.graph.impl.DigraphGenerator;
import edu.princeton.cs.algs4.graphs.graph.*;
import edu.princeton.cs.algs4.graphs.graph.eulerian.EulerianCycle;
import edu.princeton.cs.algs4.graphs.graph.eulerian.EulerianPath;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphImpl;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphReader;
import edu.princeton.cs.algs4.utils.io.StdOut;
import edu.princeton.cs.algs4.utils.io.In;
import edu.princeton.cs.algs4.utils.StdRandom;

import java.util.Iterator;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

/**
 *  The {@code DirectedEulerianPath} class represents a data type
 *  for finding an Eulerian path in a digraph.
 *  An <em>Eulerian path</em> is a path (not necessarily simple) that
 *  uses every edge in the digraph exactly once.
 *  <p>
 *  This implementation uses a nonrecursive depth-first search.
 *  The constructor take &Theta;(<em>E</em> + <em>V</em>) time
 *  in the worst case, where <em>E</em> is the number of edges and
 *  <em>V</em> is the number of vertices.
 *  It uses &Theta;(<em>V</em>) extra space (not including the digraph). 
 *  <p>
 *  To compute Eulerian cycles in digraphs, see {@link DirectedEulerianCycle}.
 *  To compute Eulerian cycles and paths in undirected graphs, see
 *  {@link EulerianCycle} and {@link EulerianPath}.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Nate Liu
 */
public class DirectedEulerianPath<T extends Edge> {
    private Stack<Integer> path = null;   // Eulerian path; null if no suh path

    /**
     * Computes an Eulerian path in the specified digraph, if one exists.
     * 
     * @param G the digraph
     */
    public DirectedEulerianPath(Graph<T> G) {
        checkArgument(G.isDirected(), "Only applicable to directed graph.");

        // find vertex from which to start potential Eulerian path:
        // a vertex v with outdegree(v) > indegree(v) if it exits;
        // otherwise a vertex with outdegree(v) > 0
        int deficit = 0;
        int s = nonIsolatedVertex(G);
        for (int v = 0; v < G.V(); v++) {
            if (G.outdegree(v) > G.indegree(v)) {
                deficit += (G.outdegree(v) - G.indegree(v));
                s = v;
            }
        }

        // digraph can't have an Eulerian path
        // (this condition is needed)
        if (deficit > 1) return;

        // special case for digraph with zero edges (has a degenerate Eulerian path)
        if (s == -1) s = 0;

        // create local view of adjacency lists, to iterate one vertex at a time
        Iterator<T>[] adj = (Iterator<T>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = G.adj(v).iterator();

        // greedily add to cycle, depth-first search style
        Stack<Integer> stack = new LinkedStack<>();
        stack.push(s);
        path = new LinkedStack<>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (adj[v].hasNext()) {
                stack.push(v);
                v = adj[v].next().w();
            }
            // push vertex with no more available edges to path
            path.push(v);
        }
            
        // check if all edges have been used
        if (path.size() != G.E() + 1)
            path = null;

        assert check(G);
    }

    /**
     * Returns the sequence of vertices on an Eulerian path.
     * 
     * @return the sequence of vertices on an Eulerian path;
     *         {@code null} if no such path
     */
    public Iterable<Integer> path() {
        return path;
    }

    /**
     * Returns true if the digraph has an Eulerian path.
     * 
     * @return {@code true} if the digraph has an Eulerian path;
     *         {@code false} otherwise
     */
    public boolean hasEulerianPath() {
        return path != null;
    }


    // returns any non-isolated vertex; -1 if no such vertex
    private static <T extends Edge> int nonIsolatedVertex(Graph<T> G) {
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

    // Determines whether a digraph has an Eulerian path using necessary
    // and sufficient conditions (without computing the path itself):
    //    - indegree(v) = outdegree(v) for every vertex,
    //      except one vertex v may have outdegree(v) = indegree(v) + 1
    //      (and one vertex v may have indegree(v) = outdegree(v) + 1)
    //    - the graph is connected, when viewed as an undirected graph
    //      (ignoring isolated vertices)
    private static <T extends Edge> boolean satisfiesNecessaryAndSufficientConditions(Graph<T>  G) {
        if (G.E() == 0) return true;

        // Condition 1: indegree(v) == outdegree(v) for every vertex,
        // except one vertex may have outdegree(v) = indegree(v) + 1
        int deficit = 0;
        for (int v = 0; v < G.V(); v++)
            if (G.outdegree(v) > G.indegree(v))
                deficit += (G.outdegree(v) - G.indegree(v));
        if (deficit > 1) return false;

        // Condition 2: graph is connected, ignoring isolated vertices
        Graph<T> H = GraphImpl.graph(G.V());
        for (int v = 0; v < G.V(); v++)
            for (T e : G.adj(v)) {
                H.addEdge(v, e);
            }
        
        // check that all non-isolated vertices are connected
        int s = nonIsolatedVertex(G);
        BreadthFirstPaths<T> bfs = new BreadthFirstPaths<>(H, s);
        for (int v = 0; v < G.V(); v++)
            if (H.outdegree(v) > 0 && !bfs.hasPathTo(v))
                return false;

        return true;
    }


    private boolean check(Graph<T> G) {

        // internal consistency check
        if (hasEulerianPath() == (path() == null)) return false;

        // hashEulerianPath() returns correct value
        if (hasEulerianPath() != satisfiesNecessaryAndSufficientConditions(G)) return false;

        // nothing else to check if no Eulerian path
        if (path == null) return true;

        // check that path() uses correct number of edges
        if (path.size() != G.E() + 1) return false;

        // check that path() is a directed path in G
        // TODO

        return true;
    }


    private static <T extends Edge> void unitTest(Graph<T> G, String description) {
        StdOut.println(description);
        StdOut.println("-------------------------------------");
        StdOut.print(G);

        DirectedEulerianPath<T> euler = new DirectedEulerianPath<>(G);

        StdOut.print("Eulerian path:  ");
        if (euler.hasEulerianPath()) {
            for (int v : euler.path()) {
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
     * Unit tests the {@code DirectedEulerianPath} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);


        // Eulerian cycle
        Graph<Edge> G1 = DigraphGenerator.eulerianCycle(V, E);
        unitTest(G1, "Eulerian cycle");

        // Eulerian path
        Graph<Edge> G2 = DigraphGenerator.eulerianPath(V, E);
        unitTest(G2, "Eulerian path");

        // add one random edge
        Graph<Edge> G3 = new GraphImpl<>(G2);
        int v3;
        G3.addEdge(v3=StdRandom.uniform(V), new UnWeightedEdge(v3, StdRandom.uniform(V)));
        unitTest(G3, "one random edge added to Eulerian path");

        // self loop
        Graph<Edge> G4 = GraphImpl.digraph(V);
        int v4 = StdRandom.uniform(V);
        G4.addEdge(v4, new UnWeightedEdge(v4, v4));
        unitTest(G4, "single self loop");

        // single edge
        Graph<Edge> G5 = GraphImpl.digraph(V);
        int v5;
        G5.addEdge(v5=StdRandom.uniform(V), new UnWeightedEdge(v5, StdRandom.uniform(V)));
        unitTest(G5, "single edge");

        // empty digraph
        Graph<Edge> G6 = GraphImpl.digraph(V);
        unitTest(G6, "empty digraph");

        // random digraph
        Graph<Edge> G7 = DigraphGenerator.simple(V, E);
        unitTest(G7, "simple digraph");

        // 4-vertex digraph
        Graph<Edge> G8 = GraphReader.readDigraph(new In("eulerianD.txt"));
        unitTest(G8, "4-vertex Eulerian digraph");
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
