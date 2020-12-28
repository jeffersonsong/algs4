package edu.princeton.cs.algs4.graphs.maxflow;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphReader;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FlowNetworkTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowNetworkTest.class);
    private Graph<FlowEdge> network;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/65maxflow/tinyFN.txt");
        network = GraphReader.readFlowNetwork(in);
    }

    @Test
    public void test() {
        assertThat(network.V(), is(6));
        assertThat(network.E(), is(8));

        List<FlowEdge> edgeList = toList(FlowNetworkUtils.edges(network));
        assertThat(edgeList.size(), is(network.E()));
        LOGGER.info(network.toString());
    }
}