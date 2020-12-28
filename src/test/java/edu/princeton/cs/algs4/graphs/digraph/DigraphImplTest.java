package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.GraphReader;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static edu.princeton.cs.algs4.graphs.graph.GraphTestUtils.toIdList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class DigraphImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DigraphImplTest.class);
    private Graph<Edge> G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/42digraph/tinyDG.txt");
        G = GraphReader.readDigraph(in);
    }

    @Test
    public void test() {
        assertThat(G.V(), is(13));
        assertThat(G.E(), is(22));

        List<Integer> adj = toIdList(G.adj(6));
        assertThat(adj.size(), is(4));
        assertThat(adj, hasItems(9, 4, 8, 0));

        LOGGER.info(G.toString());

        Graph<Edge> R = G.reverse();
        assertThat(R.V(), is(13));
        assertThat(R.E(), is(22));

        adj = toIdList(R.adj(3));
        assertThat(adj.size(), is(2));
        assertThat(adj, hasItems(2, 4));

        LOGGER.info("Reversed:");
        LOGGER.info(R.toString());
    }
}