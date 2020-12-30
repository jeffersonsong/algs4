package edu.princeton.cs.algs4.backtrack;

import java.util.List;

/**
 * Generic backtrack implementation.
 * @param <Input> Input type.
 */
public class BackTrack<Input> {

    /**
     * Back track callback API.
     *
     * @param <Input> input data type.
     */
    public interface BackTrackCallback<Input> {
        /**
         * Test the first k elements of vector a are a complete solution for the given problem.
         *
         * @param a     solution vector.
         * @param k     first k elements.
         * @param input allow pass general information.
         * @return true if the first k elements of vector a are a complete solution, otherwise false.
         */
        boolean isaSolution(int[] a, int k, Input input);
        /**
         * Process a complete solution once it is constructed.
         *
         * @param a     solution vector.
         * @param k     first k elements.
         * @param input general input.
         * @return true if all required solutuin is found, and backtrace process will terminate.
         */
        boolean processSolution(int[] a, int k, Input input);
        /**
         * Fills an array c with the complete set of possible candidates for the kth position of a,
         * given the contents of the first k - 1 positions.
         *
         * @param a     solution vector.
         * @param k     kth element.
         * @param input general input.
         * @return candidate array.
         */
        List<Integer> candidates(int[] a, int k, Input input);
        /**
         * Make a move based on updated kth position of a.
         *
         * @param a     solution vector.
         * @param k     kth element.
         * @param input general input.
         */
        void move(int[] a, int k, Input input);
        /**
         * Undo the move based on updated kth position of a.
         *
         * @param a     solution vector.
         * @param k     kth element.
         * @param input general input.
         */
        void undo(int[] a, int k, Input input);
    }

    private boolean finished = false;

    /**
     * We will model our combinatorial search solution as a vector a = (a1, a2, ..., an),
     * where each element ai is selected from a finite ordered set Si. Such a vector might
     * represent an arrangement where ai contains the ith element of the permutation.
     * Or, the vector might represent a given subset S, where ai is true if and only if
     * the ith element of the universe is in S. The vector can even represent a sequence
     * of moves in a game ora path in a graph, where ai contains the ith event in the sequence.
     * <p>
     * At each step in the backtracking algorithm, we try to extend a given partial
     * solution a = (a1, a2, ..., ak) by adding another element at the end. After extending
     * it, we must test whether what we now have is a solution: if so, we should print it
     * or count it. If not, we must check whether the partial solution is still potentially
     * extendible to some complete solution.
     * <p>
     * Backtracking constructs a tree of partial solutions, where each vertex represents
     * a partial solution. There is an edge from x to y if node y was created by advancing
     * from x. This tree of partial solutions provides an alternative way to think about
     * backtracking, for the process of constructing the solutions corresponds exactly to
     * doing a depth-first traversal of the backtrack tree. Viewing backtracking as a depth
     * first search on an implicit graph yields a natural recursive implementation of the
     * basic algorithm.
     */
    /*-
     * Backtrack-DFS(A, k)
     *   if A = (a1, a2, ..., ak) is a solution, report it.
     *   else
     *     k = k + 1
     *     compute Sk
     *     while Sk is not empty do
     *       ak = an element in Sk
     *       Sk = Sk − ak
     *       Backtrack-DFS(A, k)
     */
    public void backtrack(int[] a, int k, Input input, BackTrackCallback<Input> callback) {
        if (k == 0) finished = false;

        if (callback.isaSolution(a, k, input)) {
            finished = callback.processSolution(a, k, input);

        } else {
            k++;
            List<Integer> candidates = callback.candidates(a, k, input);
            for (Integer candidate : candidates) {
                a[k] = candidate;
                callback.move(a, k, input);
                backtrack(a, k, input, callback);
                if (finished) return;
                callback.undo(a, k, input);
            }
        }
    }
}
