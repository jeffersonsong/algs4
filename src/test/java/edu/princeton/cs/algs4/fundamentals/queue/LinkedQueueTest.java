package edu.princeton.cs.algs4.fundamentals.queue;

public class LinkedQueueTest extends QueueBaseTest {

    @Override
    protected Queue<String> createQueue() {
        return new LinkedQueue<>();
    }

}