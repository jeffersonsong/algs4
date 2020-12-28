package edu.princeton.cs.algs4.graphs.graph;

import edu.princeton.cs.algs4.utils.io.StdOut;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SymbolGraphTest {
    private SymbolGraph sg;

    @Before
    public void setUp() {
        sg = SymbolGraph.symbolGraph("src/test/resources/41graph/routes.txt", " ");
    }

    @Test
    public void test() {
        Graph<Edge> G = sg.graph();

        assertTrue(sg.contains("LAS"));
        assertFalse(sg.contains("EWR"));

        int s = sg.indexOf("JFK");

        BreadthFirstPaths<Edge>  bfs = new BreadthFirstPaths<>(G, s);
        int t = sg.indexOf("ORD");

        assertTrue(bfs.hasPathTo(t));

        for (int v : bfs.pathTo(t)) {
            StdOut.println("   " + sg.nameOf(v));
        }
    }
}