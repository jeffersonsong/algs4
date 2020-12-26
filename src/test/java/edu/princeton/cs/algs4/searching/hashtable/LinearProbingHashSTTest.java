package edu.princeton.cs.algs4.searching.hashtable;

import edu.princeton.cs.algs4.searching.st.ST;
import edu.princeton.cs.algs4.searching.st.STBaseTest;

public class LinearProbingHashSTTest extends STBaseTest {

    @Override
    protected ST<String, Integer> createST() {
        return new LinearProbingHashST<>();
    }
}