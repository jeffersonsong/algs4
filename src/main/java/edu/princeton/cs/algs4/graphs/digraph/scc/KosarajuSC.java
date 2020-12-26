package edu.princeton.cs.algs4.graphs.digraph.scc;

import edu.princeton.cs.algs4.graphs.digraph.Digraph;
import edu.princeton.cs.algs4.graphs.graph.DepthFirstSearch;

/**
 * Given a directed graph, find out whether the graph is strongly connected or not. A directed graph is strongly
 * connected if there is a path between any two pair of vertices.
 *
 * Kosaraju’s DFS based simple algorithm that does two DFS traversals of graph:
 *
 * 1) Initialize all vertices as not visited.
 * 2) Do a DFS traversal of graph starting from any arbitrary vertex v. If DFS traversal doesn’t visit all vertices,
 * then return false.
 * 3) Reverse all arcs (or find transpose or reverse of graph)
 * 4) Mark all vertices as not-visited in reversed graph.
 * 5) Do a DFS traversal of reversed graph starting from same vertex v (Same as step 2). If DFS traversal doesn’t
 * visit all vertices, then return false. Otherwise return true.
 */
public class KosarajuSC {
    /**
     * @return true if the graph is strongly connected.
     */
    public static boolean isSC(Digraph G) {
        return allVerticesReachable(G, 0) &&
                allVerticesReachable(G.reverse(), 0);
    }

    private static boolean allVerticesReachable(Digraph G, int s) {
        // If DFS traversal doesn't visit all vertices, then return false.
        DepthFirstSearch dfs = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (!dfs.marked(v)) return false;
        }
        return true;
    }
}
