package edu.princeton.cs.algs4.graphs.graph.eulerian;

import edu.princeton.cs.algs4.graphs.graph.DepthFirstSearch;
import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.Graph;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

/**
 * Eulerian Path is a path in graph that visits every edge exactly once.
 *
 * Eulerian Circuit is an Eulerian Path which starts and ends on the same vertex.
 *
 * https://www.geeksforgeeks.org/eulerian-path-and-circuit/
 *
 * @author Aakash Hasija
 */
public class Eulerian<T extends Edge> {

    public enum EulerianType {
        NOT_EULERIAN,
        EULER_PATH,
        EULER_CYCLE
    }

    /**
     * Check undirected graph Eulerian type.
     * @param G undirected graph.
     * @return
     *  NON_EULERIAN if the graph doesn't have a Eulerian path nor circle.
     *  EULER_PATH if the graph has a Eulerian path (Semi-Eulerian).
     *  EULER_CIRCLE If graph has an Euler Circle (Eulerian)
     */
    public EulerianType isEulerian(Graph<T> G) {
        checkArgument(!G.isDirected(), "Only applicable to undirected graph.");

        // Check if all non-zero degree vertices are connected
        if (!isConnected(G)) {
            return EulerianType.NOT_EULERIAN;
        }

        // Count vertices with odd degree
        int odd = 0;
        for (int v = 0; v < G.V(); v++) {
            if (G.outdegree(v) % 2 != 0) {
                odd++;
            }
        }

        // If count is more than 2, then graph is not Eulerian
        if (odd > 2) {
            return EulerianType.NOT_EULERIAN;
        }

        // If odd count is 2, then semi-eulerian.
        // If odd count is 0, then eulerian
        // Note that odd count can never be 1 for undirected graph
        return (odd == 2) ? EulerianType.EULER_PATH : EulerianType.EULER_CYCLE;
    }

    private boolean isConnected(Graph<T> G) {
        int i = findFirstNonZeroDegreeVertice(G);

        // If there are no edges in the graph, return true
        if (i == G.V()) {
            return true;
        }

        // Start DFS traversal from a vertex with non-zero degree
        DepthFirstSearch<T> dfs = new DepthFirstSearch<>(G, i);

        // Check if all non-zero degree vertices are visited
        for (int v = 0; v < G.V(); v++) {
            if (!dfs.marked(v) && G.outdegree(v) > 0) {
                return false;
            }
        }

        return true;
    }

    private int findFirstNonZeroDegreeVertice(Graph<T> g) {
        int i;

        // Find a vertex with non-zero degree
        for (i = 0; i < g.V(); i++) {
            if (g.outdegree(i) != 0) {
                return i;
            }
        }
        return i;
    }
}
