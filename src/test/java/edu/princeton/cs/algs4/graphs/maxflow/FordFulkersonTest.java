package edu.princeton.cs.algs4.graphs.maxflow;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.GraphReader;
import edu.princeton.cs.algs4.utils.io.In;
import edu.princeton.cs.algs4.utils.io.StdOut;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FordFulkersonTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FordFulkersonTest.class);
    private Graph<FlowEdge> G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/65maxflow/tinyFN.txt");
        G = GraphReader.readFlowNetwork(in);
    }

    @Test
    public void test() {
        int s = 0, t = G.V() - 1;
        FordFulkerson maxflow = new FordFulkerson(G, s, t);
        StdOut.println("Max flow from " + s + " to " + t);
        for (int v = 0; v < G.V(); v++)
            for (FlowEdge e : G.adj(v))
                if ((v == e.v()) && e.flow() > 0)
                    StdOut.println(" " + e);
        StdOut.println("Max flow value = " + maxflow.value());
        assertThat(maxflow.value(), is(closeTo(4.0, 1e-2)));
    }
}