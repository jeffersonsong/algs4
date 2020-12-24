package edu.princeton.cs.algs4.searching.bst;

import edu.princeton.cs.algs4.searching.st.OrderedST;
import edu.princeton.cs.algs4.searching.st.OrderedSTBaseTest;

public class AVLTreeSTTest extends OrderedSTBaseTest {
    @Override
    protected OrderedST<Character, Integer> createOrderedST() {
        return new AVLTreeST<>();
    }
}