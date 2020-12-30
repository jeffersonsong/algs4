package edu.princeton.cs.algs4.graphs.graph.impl;

import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AdjMatrixGraphImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GraphImplTest.class);

    private Graph<Edge> G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/41graph/tinyG.txt");
        G = GraphReader.readGraphInAdjMatrix(in);
    }

    @Test
    public void test() {
        assertThat(G.V(), is(13));
        assertThat(G.E(), is(13));

        LOGGER.info(G.toString());
    }
}
