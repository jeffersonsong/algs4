package edu.princeton.cs.algs4.searching.st;

public class STImplTest extends STBaseTest {

    @Override
    protected ST<Character, Integer> createST() {
        return new STImpl<>();
    }

}