package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TopologicalXTest {
    @Test
    public void test() {
        In in = new In("src/test/resources/topological.txt");
        Digraph g = new DigraphImpl(in);
        Iterable<Integer> order = new TopologicalX(g).order();
        List<Integer> list = toList(order);
        System.out.println(list);
        assertThat(list, is(asList(2, 8, 3, 0, 7, 5, 1, 6, 9, 4, 11, 10, 12)));
    }
}