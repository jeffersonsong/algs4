package edu.princeton.cs.algs4.graphs.digraph.eulerian;

import edu.princeton.cs.algs4.graphs.digraph.Digraph;
import edu.princeton.cs.algs4.graphs.graph.DepthFirstSearch;

public class DirectedEulerianCycle {

    /**
     * @param G directed graph.
     * @return This function returns true if the directed graph has a eulerian
     * cycle, otherwise returns false
     */
    public boolean isEulerianCycle(Digraph G) {
        // Check if all non-zero degree vertices are connected
        if (!isSC(G)) return false;

        // Check if in degree and out degree of every vertex is same
        for (int v = 0; v < G.V(); v++) {
            if (G.indegree(v) != G.indegree(v)) return false;
        }

        return true;
    }

    /**
     * @return true if the graph is strongly connected.
     */
    private boolean isSC(Digraph G) {
        return allVerticesReachable(G, 0) &&
                allVerticesReachable(G.reverse(), 0);
    }

    private boolean allVerticesReachable(Digraph G, int s) {
        // If DFS traversal doesn't visit all vertices, then return false.
        DepthFirstSearch dfs = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (!dfs.marked(v)) return false;
        }
        return true;
    }

}
