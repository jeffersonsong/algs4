package edu.princeton.cs.algs4.sorting.pq;

public class UnorderedMaxPQTest extends MaxPQBaseTest {

    @Override
    protected UnorderedMaxPQ<Character> createMaxPQ() {
        return new UnorderedMaxPQ<>(Character::compareTo);
    }

}