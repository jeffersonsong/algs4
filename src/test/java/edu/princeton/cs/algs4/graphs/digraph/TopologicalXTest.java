package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.GraphReader;
import edu.princeton.cs.algs4.graphs.graph.SymbolGraph;
import edu.princeton.cs.algs4.graphs.graph.UnweightedEdgeNode;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TopologicalXTest {
    @Test
    public void test() {
        In in = new In("src/test/resources/42digraph/topological.txt");
        Graph<UnweightedEdgeNode> g = GraphReader.readDigraph(in);
        Iterable<Integer> order = new TopologicalX<>(g).order();
        List<Integer> list = toList(order);
        System.out.println(list);
        assertThat(list, is(asList(2, 8, 3, 0, 7, 5, 1, 6, 9, 4, 11, 10, 12)));
    }


    @Test
    public void testCourseScheduling() {
        String[] expected = {"Introduction to CS", "Calculus", "Algorithms", "Advanced Programming", "Linear Algebra",
                "Databases", "Scientific Computing", "Theoretical CS", "Artificial Intelligence",
                "Computational Biology", "Machine Learning", "Robotics", "Neural Networks"};

        SymbolGraph sg = SymbolGraph.symbolDigraph("src/test/resources/42digraph/jobs.txt", "/");
        Graph<UnweightedEdgeNode> G = sg.graph();
        TopologicalX<UnweightedEdgeNode> topological = new TopologicalX<>(G);

        List<String> result = new ArrayList<>();
        for (int v : topological.order()) {
            result.add(sg.nameOf(v));
        }

        assertThat(result, is(asList(expected)));
    }
}