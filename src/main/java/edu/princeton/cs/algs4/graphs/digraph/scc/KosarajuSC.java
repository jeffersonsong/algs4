package edu.princeton.cs.algs4.graphs.digraph.scc;

import edu.princeton.cs.algs4.graphs.graph.DepthFirstSearch;
import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.Graph;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

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
    public static <T extends Edge> boolean isSC(Graph<T> G) {
        checkArgument(G.isDirected());
        return allVerticesReachable(G, 0) &&
                allVerticesReachable(G.reverse(), 0);
    }

    private static <T extends Edge> boolean allVerticesReachable(Graph<T> G, int s) {
        // If DFS traversal doesn't visit all vertices, then return false.
        DepthFirstSearch<T> dfs = new DepthFirstSearch<>(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (!dfs.marked(v)) return false;
        }
        return true;
    }
}
