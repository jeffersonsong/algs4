package edu.princeton.cs.algs4.backtrack;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class NQueensTest {
    @Test
    public void eightQueensProblem() {
        BackTrack<Integer> backTrack = new BackTrack<>();
        NQueens nQueens = new NQueens();

        int[] a= new int[9];
        backTrack.backtrack(a, 0, 8, nQueens);
        assertThat(nQueens.solutionCount(), is(92));
    }
}