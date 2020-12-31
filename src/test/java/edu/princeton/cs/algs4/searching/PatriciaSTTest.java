package edu.princeton.cs.algs4.searching;

import edu.princeton.cs.algs4.searching.st.ST;
import edu.princeton.cs.algs4.searching.st.STBaseTest;

public class PatriciaSTTest extends STBaseTest {
    @Override
    protected ST<String, Integer> createST() {
        return new PatriciaST<>();
    }
}