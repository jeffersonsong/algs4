package edu.princeton.cs.algs4.graphs.graph;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class BipartiteXTest {

    @Test
    public void test() {
        In in = new In("src/test/resources/41graph/tinyG.txt");
        Graph<UnweightedEdgeNode> G = GraphReader.readGraph(in, false);
        BipartiteX<UnweightedEdgeNode> b = new BipartiteX<>(G);
        assertFalse(b.isBipartite());
    }
}