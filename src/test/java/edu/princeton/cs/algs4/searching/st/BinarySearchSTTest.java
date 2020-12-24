package edu.princeton.cs.algs4.searching.st;

public class BinarySearchSTTest extends OrderedSTBaseTest {
    @Override
    protected OrderedST<Character, Integer> createOrderedST() {
        return new BinarySearchST<>();
    }

}