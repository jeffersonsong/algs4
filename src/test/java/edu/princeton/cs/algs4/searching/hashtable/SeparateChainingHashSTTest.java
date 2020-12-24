package edu.princeton.cs.algs4.searching.hashtable;

import edu.princeton.cs.algs4.searching.st.ST;
import edu.princeton.cs.algs4.searching.st.STBaseTest;

import static org.junit.Assert.*;

public class SeparateChainingHashSTTest extends STBaseTest {

    @Override
    protected ST<Character, Integer> createST() {
        return new SeparateChainingHashST<>();
    }

}