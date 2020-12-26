package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.graphs.graph.NonrecursiveDFS;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NonrecursiveDirectedDFSTest {
    private Digraph G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/42digraph/tinyDG.txt");
        G = DigraphReader.readDigraph(in);
    }

    @Test
    public void test() {
        NonrecursiveDFS dfs = new NonrecursiveDFS(G, 0);
        assertTrue(dfs.marked(1));
        assertFalse(dfs.marked(11));
    }
}