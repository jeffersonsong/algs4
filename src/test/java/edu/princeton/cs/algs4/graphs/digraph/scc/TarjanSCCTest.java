package edu.princeton.cs.algs4.graphs.digraph.scc;

import edu.princeton.cs.algs4.graphs.digraph.Digraph;

public class TarjanSCCTest extends SCCBaseTest {
    @Override
    protected SCC createSCC(Digraph G) {
        return new TarjanSCC(G);
    }

}