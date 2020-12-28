package edu.princeton.cs.algs4.graphs.digraph.eulerian;

import edu.princeton.cs.algs4.graphs.digraph.scc.KosarajuSC;
import edu.princeton.cs.algs4.graphs.graph.EdgeNode;
import edu.princeton.cs.algs4.graphs.graph.Graph;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

/**
 * @author Princi Singh
 */
public class DirectedEulerianCycleX<T extends EdgeNode> {

    /**
     * @param G directed graph.
     * @return This function returns true if the directed graph has a eulerian
     * cycle, otherwise returns false
     */
    public boolean isEulerianCycle(Graph<T> G) {
        checkArgument(G.isDirected());
        // Check if all non-zero degree vertices are connected
        if (!KosarajuSC.isSC(G)) return false;

        // Check if in degree and out degree of every vertex is same
        for (int v = 0; v < G.V(); v++) {
            if (G.indegree(v) != G.indegree(v)) return false;
        }

        return true;
    }
}
