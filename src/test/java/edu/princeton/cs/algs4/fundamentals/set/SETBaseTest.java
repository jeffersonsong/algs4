package edu.princeton.cs.algs4.fundamentals.set;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.*;

public abstract class SETBaseTest {
    protected SET<String> set;

    @Before
    public void setUp() throws Exception {
        set = createSET();
    }

    protected abstract SET<String> createSET();

    @Test
    public void test() {
        assertTrue(set.isEmpty());
        assertThat(set.size(), is(0));

        String text = "SEARCHEXAMPLE";
        for (int i = 0; i < text.length(); i++) {
            set.add(text.substring(i, i+1));
        }

        assertFalse(set.isEmpty());
        assertThat(set.size(), is(10));

        List<String> keys = toList(set);
        assertThat(keys, hasItems("A", "C", "E", "H", "L", "M", "P", "R", "S", "X"));

        set.add("R");
        assertThat(set.size(), is(10));

        set.remove("R");
        assertThat(set.size(), is(9));

        set.remove("X");
        assertThat(set.size(), is(8));

        set.remove("A");
        set.remove("C");
        set.remove("E");
        set.remove("H");
        set.remove("L");
        set.remove("M");
        set.remove("P");

        assertThat(set.size(), is(1));
        set.remove("S");

        assertTrue(set.isEmpty());
        assertThat(set.size(), is(0));
    }
}