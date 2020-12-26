package edu.princeton.cs.algs4.graphs.graph.euler;

import edu.princeton.cs.algs4.graphs.graph.DepthFirstSearch;
import edu.princeton.cs.algs4.graphs.graph.Graph;

/**
 * https://www.geeksforgeeks.org/eulerian-path-and-circuit/
 *
 * @author Aakash Hasija
 */
public class Eulerian {

    /**
     * Check undirected graph Eulerian type.
     * @param G undirected graph.
     * @return
     *  NON_EULERIAN if the graph doesn't have a Eulerian path nor circle.
     *  EULER_PATH if the graph has a Eulerian path (Semi-Eulerian).
     *  EULER_CIRCLE If graph has an Euler Circle (Eulerian)
     */
    public EulerianType isEulerian(Graph G) {
        // Check if all non-zero degree vertices are connected
        if (!isConnected(G)) {
            return EulerianType.NON_EULERIAN;
        }

        // Count vertices with odd degree
        int odd = 0;
        for (int v = 0; v < G.V(); v++) {
            if (G.degree(v) % 2 != 0) {
                odd++;
            }
        }

        // If count is more than 2, then graph is not Eulerian
        if (odd > 2) {
            return EulerianType.NON_EULERIAN;
        }

        // If odd count is 2, then semi-eulerian.
        // If odd count is 0, then eulerian
        // Note that odd count can never be 1 for undirected graph
        return (odd == 2) ? EulerianType.EULER_PATH : EulerianType.EULER_CIRCLE;
    }

    private boolean isConnected(Graph G) {
        int i;

        // Find a vertex with non-zero degree
        for (i = 0; i < G.V(); i++) {
            if (G.degree(i) != 0) {
                break;
            }
        }

        // Start DFS traversal from a vertex with non-zero degree
        DepthFirstSearch dfs = new DepthFirstSearch(G, i);

        // Check if all non-zero degree vertices are visited
        for (int v = 0; v < G.V(); v++) {
            if (!dfs.marked(v) && G.degree(v) > 0) {
                return false;
            }
        }

        return true;
    }
}
