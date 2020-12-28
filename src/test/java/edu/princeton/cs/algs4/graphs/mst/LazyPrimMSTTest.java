package edu.princeton.cs.algs4.graphs.mst;

import edu.princeton.cs.algs4.graphs.graph.Graph;

public class LazyPrimMSTTest extends MSTBaseTest {
    
    @Override
    protected MST createMST(Graph<WeightedEdge> G) {
        return new LazyPrimMST(G);
    }
}