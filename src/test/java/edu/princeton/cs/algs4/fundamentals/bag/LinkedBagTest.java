package edu.princeton.cs.algs4.fundamentals.bag;

import edu.princeton.cs.algs4.fundamentals.utils.ListUtils;
import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LinkedBagTest {
    private Bag<String> bag;

    @Before
    public void setUp() throws Exception {
        this.bag = new LinkedBag<>();
    }

    @Test
    public void testBag() {
        assertTrue(this.bag.isEmpty());
        assertThat(this.bag.size(), is(0));

        for (int i = 0; i < 10; i++) {
            this.bag.add(Integer.toString(i));
        }

        assertThat(this.bag.size(), is(10));
        assertThat(ListUtils.toList(this.bag), is(asList("9 8 7 6 5 4 3 2 1 0".split(" "))));
    }
}