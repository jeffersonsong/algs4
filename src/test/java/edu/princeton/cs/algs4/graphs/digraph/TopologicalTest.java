package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TopologicalTest {
    @Test
    public void test() {
        In in = new In("src/test/resources/42digraph/topological.txt");
        Digraph g = DigraphReader.read(in);
        Iterable<Integer> order = new Topological(g).order();
        List<Integer> list = toList(order);
        System.out.println(list);
        assertThat(list, is(asList(8, 7, 2, 3, 0, 1, 5, 6, 4, 9, 10, 11, 12)));
    }

}