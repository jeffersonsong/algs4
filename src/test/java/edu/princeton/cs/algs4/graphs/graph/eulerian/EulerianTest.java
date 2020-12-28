package edu.princeton.cs.algs4.graphs.graph.eulerian;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphImpl;
import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.UnWeightedEdge;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EulerianTest {
    private final Eulerian<Edge> euler = new Eulerian<>();

    @Test
    public void testEulerianPath() {
        Graph<Edge> g1 = new GraphImpl<>(5, false);
        g1.addEdge(1, new UnWeightedEdge(1,0));
        g1.addEdge(0, new UnWeightedEdge(0, 2));
        g1.addEdge(2, new UnWeightedEdge(2, 1));
        g1.addEdge(0, new UnWeightedEdge(0, 3));
        g1.addEdge(3, new UnWeightedEdge(3,4));

        assertThat(euler.isEulerian(g1), is(Eulerian.EulerianType.EULER_PATH));
    }

    @Test
    public void testEulerianCycle() {
        Graph<Edge> g2 = new GraphImpl<>(5, false);
        g2.addEdge(1, new UnWeightedEdge(1, 0));
        g2.addEdge(0, new UnWeightedEdge(0, 2));
        g2.addEdge(2, new UnWeightedEdge(2, 1));
        g2.addEdge(0, new UnWeightedEdge(0,3));
        g2.addEdge(3, new UnWeightedEdge(3,4));
        g2.addEdge(4, new UnWeightedEdge(4,0));
        assertThat(euler.isEulerian(g2), is(Eulerian.EulerianType.EULER_CYCLE));
    }

    @Test
    public void testEulerianCycle2() {
        Graph<Edge> g4 = new GraphImpl<>(3, false);
        g4.addEdge(0, new UnWeightedEdge(0,1));
        g4.addEdge(1, new UnWeightedEdge(1,2));
        g4.addEdge(2, new UnWeightedEdge(2,0));
        assertThat(euler.isEulerian(g4), is(Eulerian.EulerianType.EULER_CYCLE));
    }

    @Test
    public void testEulerianCycle3() {
        Graph<Edge> g5 = new GraphImpl<>(3, false);
        assertThat(euler.isEulerian(g5), is(Eulerian.EulerianType.EULER_CYCLE));
    }

    @Test
    public void testNonEulerian() {
        Graph<Edge> g3 = new GraphImpl<>(5, false);
        g3.addEdge(1, new UnWeightedEdge(1, 0));
        g3.addEdge(0, new UnWeightedEdge(0,2));
        g3.addEdge(2, new UnWeightedEdge(2,1));
        g3.addEdge(0, new UnWeightedEdge(0, 3));
        g3.addEdge(3, new UnWeightedEdge(3,4));
        g3.addEdge(1, new UnWeightedEdge(1,3));
        assertThat(euler.isEulerian(g3), is(Eulerian.EulerianType.NOT_EULERIAN));
    }
}