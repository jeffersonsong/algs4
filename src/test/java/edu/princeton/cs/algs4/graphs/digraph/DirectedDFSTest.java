package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.graphs.graph.DepthFirstSearch;
import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.GraphReader;
import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DirectedDFSTest {
    private Graph<Edge> G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/42digraph/tinyDG.txt");
        G = GraphReader.readDigraph(in);
    }

    @Test
    public void test() {
        DepthFirstSearch<Edge> dfs = new DepthFirstSearch<>(G, 0);
        assertTrue(dfs.marked(1));
        assertFalse(dfs.marked(11));
    }
}