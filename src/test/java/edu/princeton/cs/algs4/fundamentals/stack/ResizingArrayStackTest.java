package edu.princeton.cs.algs4.fundamentals.stack;

public class ResizingArrayStackTest extends StackBaseTest {
    @Override
    protected ResizingArrayStack<String> createStack() {
        return new ResizingArrayStack<>();
    }
}