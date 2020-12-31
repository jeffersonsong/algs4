package edu.princeton.cs.algs4.graphs.graph.hamiltonian;

import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HamiltonianPath2<T extends Edge> {
    private final int[] path;
    private final boolean[] marked;
    private boolean isHamiltonianPath = false;

    public HamiltonianPath2(Graph<T> graph) {
        this.path = new int[graph.V()];
        Arrays.fill(this.path, -1);
        this.marked = new boolean[graph.V()];
        int start = 0;

        path[0] = start;
        marked[path[0]] = true;

        dfs(graph, start, path, 0);
    }

    private void dfs(Graph<T> graph, int v, int[] path, int k) {
        if (k == graph.V() - 1) {
            isHamiltonianPath = true;

        } else {
            k++;
            for (T e: graph.adj(v)) {
                int w = e.w();
                if (!marked[w]) {
                    path[k] = w;
                    marked[w] = true;

                    dfs(graph, w, path, k);
                    if (isHamiltonianPath) return;

                    path[k] = -1;
                    marked[w] = false;
                }
            }
        }
    }

    public boolean isHamiltonianPath() {
        return isHamiltonianPath;
    }

    public List<Integer> getPath() {
        List<Integer> list = new ArrayList<>(path.length);
        for (int v : path) {
            list.add(v);
        }
        return list;
    }
}
