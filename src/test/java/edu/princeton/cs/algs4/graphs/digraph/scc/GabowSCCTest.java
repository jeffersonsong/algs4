package edu.princeton.cs.algs4.graphs.digraph.scc;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.UnweightedEdgeNode;

public class GabowSCCTest extends SCCBaseTest {
    @Override
    protected SCC createSCC(Graph<UnweightedEdgeNode> G) {
        return new GabowSCC<>(G);
    }
}