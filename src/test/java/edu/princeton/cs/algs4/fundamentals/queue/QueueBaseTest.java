package edu.princeton.cs.algs4.fundamentals.queue;

import edu.princeton.cs.algs4.fundamentals.utils.ListUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public abstract class QueueBaseTest {
    protected Queue<String> queue;

    @Before
    public void setUp() {
        this.queue = this.createQueue();
    }

    protected abstract Queue<String> createQueue();

    @Test
    public void test1() {
        assertTrue(this.queue.isEmpty());
        assertThat(this.queue.size(), is(0));

        this.queue.enqueue("this");
        assertThat(this.queue.size(), is(1));
        this.queue.enqueue("is");
        assertThat(this.queue.size(), is(2));
        this.queue.enqueue("the");
        assertThat(this.queue.size(), is(3));
        this.queue.enqueue("best");
        assertThat(this.queue.size(), is(4));
        this.queue.enqueue("of");
        assertThat(this.queue.size(), is(5));
        this.queue.enqueue("times");
        assertThat(this.queue.size(), is(6));

        assertThat(ListUtils.toList(this.queue), is(asList("this is the best of times".split(" "))));

        assertThat(this.queue.dequeue(), is("this"));
        assertThat(this.queue.dequeue(), is("is"));
        assertThat(this.queue.size(), is(4));
        assertThat(ListUtils.toList(this.queue), is(asList("the best of times".split(" "))));

        assertThat(this.queue.dequeue(), is("the"));
        assertThat(this.queue.dequeue(), is("best"));
        assertThat(this.queue.dequeue(), is("of"));
        assertThat(this.queue.dequeue(), is("times"));

        assertTrue(this.queue.isEmpty());
        assertThat(this.queue.size(), is(0));
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueEmptyQueue() {
        assertTrue(this.queue.isEmpty());
        assertThat(this.queue.size(), is(0));
        this.queue.dequeue();
    }
}