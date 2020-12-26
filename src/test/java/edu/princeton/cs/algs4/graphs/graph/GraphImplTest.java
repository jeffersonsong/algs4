package edu.princeton.cs.algs4.graphs.graph;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GraphImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GraphImplTest.class);

    private Graph G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/41graph/tinyG.txt");
        G = GraphGenerator.read(in);
    }

    @Test
    public void test() {
        assertThat(G.V(), is(13));
        assertThat(G.E(), is(13));

        LOGGER.info(G.toString());
    }

    @Test
    public void testCopyConstructor() {
        Graph G2 = new GraphImpl(G);
        assertThat(G2.V(), is(13));
        assertThat(G2.E(), is(13));

        assertThat(G2.toString(), is(G.toString()));

        LOGGER.info(G2.toString());
    }
}