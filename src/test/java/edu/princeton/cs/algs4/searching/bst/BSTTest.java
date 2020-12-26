package edu.princeton.cs.algs4.searching.bst;

import edu.princeton.cs.algs4.searching.st.OrderedST;
import edu.princeton.cs.algs4.searching.st.OrderedSTBaseTest;

public class BSTTest extends OrderedSTBaseTest {
    @Override
    protected OrderedST<String, Integer> createOrderedST() {
        return new BST<>();
    }
}