package edu.princeton.cs.algs4.graphs.mst;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.WeightedEdge;

public class KruskalMSTTest extends MSTBaseTest {
    
    @Override
    protected MST createMST(Graph<WeightedEdge> G) {
        return new KruskalMST(G);
    }
}