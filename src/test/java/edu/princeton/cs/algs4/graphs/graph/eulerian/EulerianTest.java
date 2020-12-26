package edu.princeton.cs.algs4.graphs.graph.eulerian;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.GraphImpl;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EulerianTest {
    private Eulerian euler = new Eulerian();

    @Test
    public void testEulerianPath() {
        Graph g1 = new GraphImpl(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);

        assertThat(euler.isEulerian(g1), is(EulerianType.EULER_PATH));
    }

    @Test
    public void testEulerianCycle() {
        Graph g2 = new GraphImpl(5);
        g2.addEdge(1, 0);
        g2.addEdge(0, 2);
        g2.addEdge(2, 1);
        g2.addEdge(0, 3);
        g2.addEdge(3, 4);
        g2.addEdge(4, 0);
        assertThat(euler.isEulerian(g2), is(EulerianType.EULER_CYCLE));
    }

    @Test
    public void testEulerianCycle2() {
        Graph g4 = new GraphImpl(3);
        g4.addEdge(0, 1);
        g4.addEdge(1, 2);
        g4.addEdge(2, 0);
        assertThat(euler.isEulerian(g4), is(EulerianType.EULER_CYCLE));
    }

    @Test
    public void testEulerianCycle3() {
        Graph g5 = new GraphImpl(3);
        assertThat(euler.isEulerian(g5), is(EulerianType.EULER_CYCLE));
    }

    @Test
    public void testNonEulerian() {
        Graph g3 = new GraphImpl(5);
        g3.addEdge(1, 0);
        g3.addEdge(0, 2);
        g3.addEdge(2, 1);
        g3.addEdge(0, 3);
        g3.addEdge(3, 4);
        g3.addEdge(1, 3);
        assertThat(euler.isEulerian(g3), is(EulerianType.NOT_EULERIAN));
    }
}