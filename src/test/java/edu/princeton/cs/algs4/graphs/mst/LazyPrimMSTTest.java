package edu.princeton.cs.algs4.graphs.mst;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.sp.DirectedEdge;

public class LazyPrimMSTTest extends MSTBaseTest {
    
    @Override
    protected MST createMST(Graph<DirectedEdge> G) {
        return new LazyPrimMST(G);
    }
}