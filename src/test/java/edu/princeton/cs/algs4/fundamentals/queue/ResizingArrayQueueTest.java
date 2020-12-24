package edu.princeton.cs.algs4.fundamentals.queue;

public class ResizingArrayQueueTest extends QueueBaseTest {
    @Override
    protected Queue<String> createQueue() {
        return new ResizingArrayQueue<>();
    }
}