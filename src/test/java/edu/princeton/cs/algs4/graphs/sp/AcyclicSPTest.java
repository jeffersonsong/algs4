package edu.princeton.cs.algs4.graphs.sp;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.GraphReader;
import edu.princeton.cs.algs4.graphs.mst.Edge;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AcyclicSPTest {
    private SP sp;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/44sp/tinyEDG2.txt");
        Graph<Edge> G = GraphReader.readEdgeWeightedDigraph(in);
        sp = new AcyclicSP(G, 0);
    }

    @Test
    public void test() {
        assertThat(sp.distTo(7), is(8.0));
        assertThat(sp.distTo(6), is(25.0));
    }
}