package edu.princeton.cs.algs4.graphs.graph;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DepthFirstSearchTest {
    private Graph<Edge> G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/41graph/tinyG.txt");
        G = GraphReader.readGraph(in);
    }

    @Test
    public void test() {
        DepthFirstSearch<Edge> dfs = new DepthFirstSearch<>(G, 0);
        assertTrue(dfs.marked(1));
        assertFalse(dfs.marked(7));
    }
}