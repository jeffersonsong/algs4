package edu.princeton.cs.algs4.graphs.mst;

public class KruskalMSTTest extends MSTBaseTest {
    
    @Override
    protected MST createMST(EdgeWeightedGraph G) {
        return new KruskalMST(G);
    }
}