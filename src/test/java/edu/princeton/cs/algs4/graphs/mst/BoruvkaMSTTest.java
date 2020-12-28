package edu.princeton.cs.algs4.graphs.mst;

import edu.princeton.cs.algs4.graphs.graph.Graph;

public class BoruvkaMSTTest extends MSTBaseTest {

    @Override
    protected MST createMST(Graph<Edge> G) {
        return new BoruvkaMST(G);
    }

}