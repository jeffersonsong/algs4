package edu.princeton.cs.algs4.graphs.mst;

public class PrimMSTTest extends MSTBaseTest  {
    @Override
    protected MST createMST(EdgeWeightedGraph G) {
        return new PrimMST(G);
    }
}