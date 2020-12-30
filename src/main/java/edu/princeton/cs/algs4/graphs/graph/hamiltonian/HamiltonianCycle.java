package edu.princeton.cs.algs4.graphs.graph.hamiltonian;

import edu.princeton.cs.algs4.backtrack.BackTrack;
import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.Graph;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * Hamiltonian Path in an undirected graph is a path that visits each vertex exactly once.
 *
 * A Hamiltonian cycle (or Hamiltonian circuit) is a Hamiltonian Path such that there is an edge (in the graph)
 * from the last vertex to the first vertex of the Hamiltonian Path.
 *
 * Determine whether a given graph contains Hamiltonian Cycle or not. If it contains, then prints the path.
 * Following are the input and output of the required function.
 *
 * Input:
 * A 2D array graph[V][V] where V is the number of vertices in graph and graph[V][V] is adjacency matrix
 * representation of the graph. A value graph[i][j] is 1 if there is a direct edge from i to j,
 * otherwise graph[i][j] is 0.
 *
 * Output:
 * An array path[V] that should contain the Hamiltonian Path. path[i] should represent the ith vertex in the
 * Hamiltonian Path. The code should also return false if there is no Hamiltonian Cycle in the graph.
 */
public class HamiltonianCycle<T extends Edge> implements BackTrack.BackTrackCallback<Integer> {
    private Graph<T> graph;
    private boolean[] marked;
    private int s;
    private List<Integer> path = null;

    public HamiltonianCycle(Graph<T> graph) {
        this.graph = graph;
        this.marked = new boolean[graph.V()];
        this.s = 0;

        BackTrack<Integer> backtrack = new BackTrack<>();
        int[] a = new int[graph.V()];
        a[0] = s;
        marked[0] = true;
        backtrack.backtrack(a, 0, graph.V(), this);
    }

    @Override
    public boolean isaSolution(int[] a, int k, Integer V) {
        if (k == V - 1) {
            for (T e : graph.adj(a[k])) {
                if (e.w() == s) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean processSolution(int[] a, int k, Integer V) {
        path = new ArrayList<>(graph.V() + 1);
        for (int v : a) {
            path.add(v);
        }
        path.add(s);
        return true;
    }

    @Override
    public List<Integer> candidates(int[] a, int k, Integer V) {
        if (k == V) {
            return emptyList();

        } else {
            List<Integer> candidates = new ArrayList<>(graph.V());
            int last = a[k-1];
            for (T e : graph.adj(last)) {
                int w = e.w();
                if (!marked[w]) {
                    candidates.add(w);
                }
            }
            return candidates;
        }
    }

    @Override
    public void move(int[] a, int k, Integer integer) {
        marked[a[k]] = true;
    }

    @Override
    public void undo(int[] a, int k, Integer integer) {
        marked[a[k]] = false;
    }

    public boolean isHamiltonianCycle() {
        return path != null;
    }

    public List<Integer> getPath() {
        return path;
    }
}
