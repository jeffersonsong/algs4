package edu.princeton.cs.algs4.fundamentals.stack;

public class LinkedStackTest extends StackBaseTest {
    @Override
    protected Stack<String> createStack() {
        return new LinkedStack<>();
    }
}