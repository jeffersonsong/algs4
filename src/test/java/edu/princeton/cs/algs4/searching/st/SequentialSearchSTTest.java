package edu.princeton.cs.algs4.searching.st;

public class SequentialSearchSTTest extends STBaseTest {
    @Override
    protected ST<String, Integer> createST() {
        return new SequentialSearchST<>();
    }
}