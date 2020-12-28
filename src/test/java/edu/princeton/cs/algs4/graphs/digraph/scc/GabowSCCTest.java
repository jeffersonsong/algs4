package edu.princeton.cs.algs4.graphs.digraph.scc;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.Edge;

public class GabowSCCTest extends SCCBaseTest {
    @Override
    protected SCC createSCC(Graph<Edge> G) {
        return new GabowSCC<>(G);
    }
}