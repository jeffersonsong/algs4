package edu.princeton.cs.algs4.graphs.sp;

import edu.princeton.cs.algs4.graphs.graph.Graph;

public class DijkstraSPTest extends SPBaseTest {

    @Override
    protected SP createSP(Graph<DirectedEdge> G, int s) {
        return new DijkstraSP(G, s);
    }
}