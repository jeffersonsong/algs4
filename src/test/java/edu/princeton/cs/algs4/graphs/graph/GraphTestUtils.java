package edu.princeton.cs.algs4.graphs.graph;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class GraphTestUtils {
    private GraphTestUtils() {
    }

    public static  <T extends EdgeNode> List<Integer> toIdList(Iterable<T> edgeNodeList) {
        return StreamSupport.stream(edgeNodeList.spliterator(), false).map(EdgeNode::w).collect(Collectors.toList());
    }
}
