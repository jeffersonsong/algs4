package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class KosarajuSharirSCCTest {
    private Digraph G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/tinyDG.txt");
        G = new DigraphImpl(in);
    }

    @Test
    public void test() {
        KosarajuSharirSCC scc = new KosarajuSharirSCC(G);
        assertThat(scc.count(), is(5));
        assertThat(scc.id(1), is(0));
        assertTrue(scc.stronglyConnected(0, 4));
        assertTrue(scc.stronglyConnected(9, 12));
        assertFalse(scc.stronglyConnected(11, 4));
    }
}