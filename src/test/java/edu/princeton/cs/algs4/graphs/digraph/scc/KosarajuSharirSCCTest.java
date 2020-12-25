package edu.princeton.cs.algs4.graphs.digraph.scc;

import edu.princeton.cs.algs4.graphs.digraph.Digraph;

public class KosarajuSharirSCCTest extends SCCBaseTest {
    @Override
    protected SCC createSCC(Digraph G) {
        return new KosarajuSharirSCC(G);
    }
}
