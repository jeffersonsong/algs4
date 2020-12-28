package edu.princeton.cs.algs4.graphs.maxflow;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphReader;
import edu.princeton.cs.algs4.graphs.graph.WeightedEdge;
import edu.princeton.cs.algs4.utils.io.In;
import edu.princeton.cs.algs4.utils.io.StdOut;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertTrue;


public class GlobalMincutTest {
    private GlobalMincut mc;
    private Graph<WeightedEdge> G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/43mst/tinyEWG.txt");
        G = GraphReader.readEdgeWeightedGraph(in);
        mc = new GlobalMincut(G);
    }

    @Test
    public void test() {
        StdOut.print("Min cut: ");
        assertTrue(mc.cut(5));
        assertThat(mc.weight(), is(closeTo(0.95, 1e-5)));
    }
}