package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphReader;
import edu.princeton.cs.algs4.graphs.graph.SymbolGraph;
import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TopologicalTest {
    @Test
    public void test() {
        In in = new In("src/test/resources/42digraph/topological.txt");
        Graph<Edge> g = GraphReader.readDigraph(in);
        Iterable<Integer> order = new Topological<>(g).order();
        List<Integer> list = toList(order);
        System.out.println(list);
        assertThat(list, is(asList(8, 7, 2, 3, 0, 1, 5, 6, 4, 9, 10, 11, 12)));
    }

    @Test
    public void testCourseScheduling() {
        String[] expected = {"Calculus", "Linear Algebra", "Introduction to CS", "Advanced Programming",
                "Algorithms", "Theoretical CS", "Artificial Intelligence", "Robotics", "Machine Learning",
                "Neural Networks", "Databases", "Scientific Computing", "Computational Biology"};

        SymbolGraph sg = SymbolGraph.symbolDigraph("src/test/resources/42digraph/jobs.txt", "/");
        Graph<Edge> G = sg.graph();
        Topological<Edge> topological = new Topological<>(G);

        List<String> result = new ArrayList<>();
        for (int v : topological.order()) {
            result.add(sg.nameOf(v));
        }

        assertThat(result, is(asList(expected)));
    }

}