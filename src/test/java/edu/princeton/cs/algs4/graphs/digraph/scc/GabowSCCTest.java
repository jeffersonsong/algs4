package edu.princeton.cs.algs4.graphs.digraph.scc;

import edu.princeton.cs.algs4.graphs.digraph.Digraph;

public class GabowSCCTest extends SCCBaseTest {
    @Override
    protected SCC createSCC(Digraph G) {
        return new GabowSCC(G);
    }
}