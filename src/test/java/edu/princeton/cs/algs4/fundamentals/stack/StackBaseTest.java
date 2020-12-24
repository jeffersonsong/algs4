package edu.princeton.cs.algs4.fundamentals.stack;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public abstract class StackBaseTest {
    protected Stack<String> stack;

    @Before
    public void setUp() {
        this.stack = this.createStack();
    }

    protected abstract Stack<String> createStack();

    @Test
    public void test() {
        this.stack.push("to");
        assertFalse(this.stack.isEmpty());
        assertThat(this.stack.size(), is(1));
        this.stack.push("be");
        this.stack.push("or");
        this.stack.push("not");
        this.stack.push("to");

        assertThat(this.stack.peek(), is("to"));
        assertThat(this.stack.pop(), is("to"));
        this.stack.push("be");
        assertThat(this.stack.pop(), is("be"));
        assertThat(this.stack.pop(), is("not"));
        this.stack.pop();
        this.stack.pop();
        this.stack.pop();
    }

    @Test(expected = NoSuchElementException.class)
    public void testPopEmptyStack() {
        assertTrue(this.stack.isEmpty());
        this.stack.pop();
    }

    @Test(expected = NoSuchElementException.class)
    public void testPeekEmptyStack() {
        assertTrue(this.stack.isEmpty());
        this.stack.peek();
    }

    @Test
    public void testIterable() {
        for (int i = 0; i < 10; i++) {
            this.stack.push(Integer.toString(i));
        }

        int i = 9;
        for (final String item : this.stack) {
            assertThat(item, is(Integer.toString(i--)));
        }
    }
}