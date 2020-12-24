package edu.princeton.cs.algs4.graphs.sp;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AcyclicSPTest {
    private SP sp;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/tinyEDG2.txt");
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        sp = new AcyclicSP(G, 0);
    }

    @Test
    public void test() {
        assertThat(sp.distTo(7), is(8.0));
        assertThat(sp.distTo(6), is(25.0));
    }
}