package edu.princeton.cs.algs4.searching.hashtable;

import edu.princeton.cs.algs4.searching.st.ST;
import edu.princeton.cs.algs4.searching.st.STBaseTest;

public class SeparateChainingHashSTTest extends STBaseTest {

    @Override
    protected ST<Character, Integer> createST() {
        return new SeparateChainingHashST<>();
    }

}