package edu.princeton.cs.algs4.graphs.mst;

public class LazyPrimMSTTest extends MSTBaseTest {
    
    @Override
    protected MST createMST(EdgeWeightedGraph G) {
        return new LazyPrimMST(G);
    }
}