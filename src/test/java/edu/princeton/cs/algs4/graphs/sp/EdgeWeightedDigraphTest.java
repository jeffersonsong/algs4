package edu.princeton.cs.algs4.graphs.sp;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.GraphReader;
import edu.princeton.cs.algs4.graphs.mst.WeightedEdge;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EdgeWeightedDigraphTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EdgeWeightedDigraphTest.class);

    private Graph<WeightedEdge> G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/44sp/tinyEWD.txt");
        G = GraphReader.readEdgeWeightedDigraph(in);
    }

    @Test
    public void test() {
        assertThat(G.V(), is(8));
        assertThat(G.E(), is(15));
        LOGGER.info(G.toString());
    }
}