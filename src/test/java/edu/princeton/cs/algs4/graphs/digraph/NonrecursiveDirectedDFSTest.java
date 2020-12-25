package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NonrecursiveDirectedDFSTest {
    private Digraph G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/tinyDG.txt");
        G = new DigraphImpl(in);
    }

    @Test
    public void test() {
        NonrecursiveDirectedDFS dfs = new NonrecursiveDirectedDFS(G, 0);
        assertTrue(dfs.marked(1));
        assertFalse(dfs.marked(11));
    }
}