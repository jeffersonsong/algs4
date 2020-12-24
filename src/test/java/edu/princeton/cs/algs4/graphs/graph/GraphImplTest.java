package edu.princeton.cs.algs4.graphs.graph;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GraphImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GraphImplTest.class);

    private GraphImpl G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/tinyG.txt");
        G = new GraphImpl(in);
    }

    @Test
    public void test() {
        assertThat(G.V(), is(13));
        assertThat(G.E(), is(13));

        LOGGER.info(G.toString());
    }
}