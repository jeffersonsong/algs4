package edu.princeton.cs.algs4.graphs.mst;

import edu.princeton.cs.algs4.graphs.graph.Graph;

public class PrimMSTTest extends MSTBaseTest  {
    @Override
    protected MST createMST(Graph<Edge> G) {
        return new PrimMST(G);
    }
}