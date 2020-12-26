package edu.princeton.cs.algs4.graphs.graph;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class BipartiteTest {
    @Test
    public void test() {
        In in = new In("src/test/resources/41graph/tinyG.txt");
        Graph G = GraphReader.readGraph(in);
        Bipartite b = new Bipartite(G);
        assertFalse(b.isBipartite());
    }
}