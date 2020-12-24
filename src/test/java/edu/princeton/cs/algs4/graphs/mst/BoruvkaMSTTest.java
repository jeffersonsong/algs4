package edu.princeton.cs.algs4.graphs.mst;

public class BoruvkaMSTTest extends MSTBaseTest {

    @Override
    protected MST createMST(EdgeWeightedGraph G) {
        return new BoruvkaMST(G);
    }

}