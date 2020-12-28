package edu.princeton.cs.algs4.graphs.graph;

import edu.princeton.cs.algs4.graphs.graph.impl.GraphReader;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NonrecursiveDFSTest {
    private Graph<Edge> G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/41graph/tinyG.txt");
        G = GraphReader.readGraph(in);
    }

    @Test
    public void test() {
        NonrecursiveDFS<Edge> dfs = new NonrecursiveDFS<>(G, 0);
        assertTrue(dfs.marked(1));
        assertFalse(dfs.marked(7));
    }
}