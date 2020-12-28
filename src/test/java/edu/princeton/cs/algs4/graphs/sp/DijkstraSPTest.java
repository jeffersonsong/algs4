package edu.princeton.cs.algs4.graphs.sp;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.mst.WeightedEdge;

public class DijkstraSPTest extends SPBaseTest {

    @Override
    protected SP createSP(Graph<WeightedEdge> G, int s) {
        return new DijkstraSP(G, s);
    }
}