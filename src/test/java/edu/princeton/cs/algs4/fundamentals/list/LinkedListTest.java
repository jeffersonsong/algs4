package edu.princeton.cs.algs4.fundamentals.list;

public class LinkedListTest extends ListBaseTest {
    @Override
    protected <T> List<T> createList() {
        return new LinkedList<>();
    }
}