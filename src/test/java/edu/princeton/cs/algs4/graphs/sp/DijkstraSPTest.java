package edu.princeton.cs.algs4.graphs.sp;

public class DijkstraSPTest extends SPBaseTest {

    @Override
    protected SP createSP(EdgeWeightedDigraph G, int s) {
        return new DijkstraSP(G, s);
    }
}