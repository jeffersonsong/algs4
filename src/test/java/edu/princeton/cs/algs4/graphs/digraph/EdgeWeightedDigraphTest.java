package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.graphs.sp.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EdgeWeightedDigraphTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EdgeWeightedDigraphTest.class);

    private EdgeWeightedDigraph G;

    @Before
    public void setUp() throws Exception {
        In in = new In("src/test/resources/tinyEWD.txt");
        G = new EdgeWeightedDigraph(in);
    }

    @Test
    public void test() {
        assertThat(G.V(), is(8));
        assertThat(G.E(), is(15));
        LOGGER.info(G.toString());
    }
}