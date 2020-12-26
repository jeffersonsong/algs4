package edu.princeton.cs.algs4.searching.st;

public class BinarySearchSTTest extends OrderedSTBaseTest {
    @Override
    protected OrderedST<String, Integer> createOrderedST() {
        return new BinarySearchST<>();
    }

}