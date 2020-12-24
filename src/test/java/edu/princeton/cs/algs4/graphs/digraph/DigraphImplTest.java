package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.*;

public class DigraphImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DigraphImplTest.class);
    private Digraph G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/tinyDG.txt");
        G = new DigraphImpl(in);
    }

    @Test
    public void test() {
        assertThat(G.V(), is(13));
        assertThat(G.E(), is(22));

        List<Integer> adj = toList(G.adj(6));
        assertThat(adj.size(), is(4));
        assertThat(adj, hasItems(9, 4, 8, 0));

        LOGGER.info(G.toString());

        Digraph R = G.reverse();
        assertThat(R.V(), is(13));
        assertThat(R.E(), is(22));

        adj = toList(R.adj(3));
        assertThat(adj.size(), is(2));
        assertThat(adj, hasItems(2, 4));

        LOGGER.info("Reversed:");
        LOGGER.info(R.toString());
    }
}