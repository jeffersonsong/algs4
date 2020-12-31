package edu.princeton.cs.algs4.backtrack;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class NQueens implements BackTrack.BackTrackCallback<Integer> {
    private int solutionCount;            /* how many solutions are there? */

    @Override
    public boolean processSolution(final int[] a, final int k, final Integer n) {
        solutionCount++;
        return false;
    }

    @Override
    public boolean isaSolution(final int[] a, final int k, final Integer n) {
        return k == n;
    }

    @Override
    public List<Integer> candidates(final int[] a, final int k, final Integer n) {
        final List<Integer> c = new ArrayList<>(n + 1);
        for (int i = 1; i <= n; i++) {
            if (isLegalMove(a, k, i)) {
                c.add(i);
            }
        }

        return c;
    }

    @Override
    public void move(int[] a, int k, Integer integer) {
    }

    @Override
    public void undo(int[] a, int k, Integer integer) {
    }

    /**
     * might the move be legal?
     */
    private boolean isLegalMove(final int[] a, final int row, final int col) {
        for (int j = 1; j < row; j++) {
            if (checkMate(row, col, j, a[j])) {
                return false;
            }
        }
        return true;
    }

    /**
     * diagonal threat or column threat
     */
    private boolean checkMate(final int r1, final int c1, final int r2, final int c2) {
        return abs(r1 - r2) == abs(c1 - c2) || c1 == c2;
    }

    public void setSolutionCount(final int solutionCount) {
        this.solutionCount = solutionCount;
    }

    public int solutionCount() {
        return this.solutionCount;
    }
}
