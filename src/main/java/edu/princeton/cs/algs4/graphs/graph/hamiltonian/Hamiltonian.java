package edu.princeton.cs.algs4.graphs.graph.hamiltonian;

import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Hamiltonian<T extends Edge> {
    public enum HamiltonianType {
        NOT_HAMILTONIAN,
        HAMILTONIAN_PATH,
        HAMILTONIAN_CYCLE
    }

    private int[] edgeTo;
    private boolean[] marked;
    private int start;
    private List<Integer> path;
    private HamiltonianType type;

    public Hamiltonian(Graph<T> graph) {
        this.edgeTo = new int[graph.V()];
        Arrays.fill(this.edgeTo, -1);
        this.marked = new boolean[graph.V()];
        this.start = 0;

        edgeTo[start] = -1;
        marked[start] = true;
        type = HamiltonianType.NOT_HAMILTONIAN;

        dfs(graph, start, 1);
    }

    private void dfs(Graph<T> graph, int v, int depth) {
        if (depth == graph.V()) {
            LinkedList<Integer> path = new LinkedList<>();
            type = HamiltonianType.HAMILTONIAN_PATH;

            for(int p = edgeTo.length - 1; p != this.start; p = edgeTo[p]) {
                path.addFirst(p);
            }
            path.addFirst(this.start);

            for (T e : graph.adj(v)) {
                int w = e.w();
                if (w == this.start) {
                    type = HamiltonianType.HAMILTONIAN_CYCLE;
                    path.add(this.start);
                }
            }

            this.path = path;

        } else {
            for (T e: graph.adj(v)) {
                int w = e.w();
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;

                    dfs(graph, w, depth + 1);

                    edgeTo[w] = -1;
                    marked[w] = false;
                }
            }
        }
    }

    public HamiltonianType getType() {
        return type;
    }

    public List<Integer> getPath() {
        return path;
    }
}
