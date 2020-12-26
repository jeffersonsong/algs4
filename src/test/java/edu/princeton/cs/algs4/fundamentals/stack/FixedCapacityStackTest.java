package edu.princeton.cs.algs4.fundamentals.stack;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FixedCapacityStackTest extends StackBaseTest {
    @Override
    protected Stack<String> createStack() {
        return new FixedCapacityStack<>(10);
    }

    @Test
    public void testFillupStack() {
        for (int i = 0; i < 10; i++) {
            this.stack.push(Integer.toString(i));
        }

        assertThat(this.stack.size(), is(10));
    }

    @Test(expected = IllegalStateException.class)
    public void testStackOverflow() {
        for (int i = 0; i < 11; i++) {
            this.stack.push(Integer.toString(i));
        }
    }
}