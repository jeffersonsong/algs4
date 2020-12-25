package edu.princeton.cs.algs4.fundamentals.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public abstract class ListBaseTest {
    private List<String> list;

    protected abstract <T> List<T> createList();

    @Before
    public void setUp() throws Exception {
        this.list = createList();

    }

    @Test
    public void testAddFront() {
        list.addFront("1");
        assertFalse(list.isEmpty());
        assertThat(list.size(), is(1));
        assertTrue(list.contains("1"));

        list.addFront("2");
        assertThat(list.size(), is(2));
        assertThat(toList(list), is(asList("2", "1")));
    }

    @Test
    public void testAddBack() {
        list.addBack("1");
        assertFalse(list.isEmpty());
        assertThat(list.size(), is(1));
        assertTrue(list.contains("1"));

        list.addBack("2");
        assertThat(list.size(), is(2));
        assertThat(toList(list), is(asList("1", "2")));
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeleteFrontFromEmptyList() {
        list.deleteFront();
    }

    @Test
    public void testDeleteFront() {
        list.addFront("1");
        assertThat(list.deleteFront(), is("1"));
        assertTrue(list.isEmpty());

        list.addFront("1");
        list.addFront("2");
        assertThat(list.deleteFront(), is("2"));
        assertThat(list.size(), is(1));
        assertTrue(list.contains("1"));
    }

    @Test
    public void testDeleteBack() {
        list.addBack("1");
        assertThat(list.deleteBack(), is("1"));
        assertTrue(list.isEmpty());

        list.addBack("1");
        list.addBack("2");
        assertThat(list.deleteBack(), is("2"));
        assertThat(list.size(), is(1));
        assertTrue(list.contains("1"));

        list.addBack("2");
        list.addBack("3");
        assertThat(list.deleteBack(), is("3"));
        assertThat(list.size(), is(2));
        assertThat(toList(list), is(asList("1", "2")));
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeleteFromEmptyList() {
        list.delete(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeleteNoValueFound() {
        list.addBack("1");
        list.delete("2");
    }

    @Test
    public void testDelete() {
        list.addBack("1");
        list.delete("1");
        assertTrue(list.isEmpty());

        list.addBack("1");
        list.addBack("2");
        list.delete("2");
        assertThat(list.size(), is(1));
        assertTrue(list.contains("1"));

        list.addBack("1");
        list.addBack("2");
        list.addBack("3");
        list.delete("2");
        assertThat(list.size(), is(3));
        assertThat(toList(list), is(asList("1", "1", "3")));
        assertTrue(list.contains("3"));
    }

    @Test
    public void testAdd() {
        list.add(0, "1");
        list.add(1, "2");
        assertThat(toList(list), is(asList("1", "2")));
        list.add(1, "3");
        assertThat(toList(list), is(asList("1", "3", "2")));
        list.add(2, "4");
        assertThat(toList(list), is(asList( "1", "3", "4", "2")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNegativeIndex() {
        list.add(0, "1");
        list.add(-1, "2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddOutOfRangeIndex() {
        list.add(0, "1");
        list.add(2, "2");
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeleteIndexFromEmptyList() {
        list.delete(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteIndexOutOfRange() {
        list.add(0, "1");
        list.delete(1);
    }

    @Test
    public void testDeleteIndex() {
        list.addBack("1");
        list.addBack("2");

        list.delete(0);
        assertThat(toList(list), is(asList("2")));

        list.addBack("1");
        list.delete(1);
        assertThat(toList(list), is(asList("2")));

    }

    @Test
    public void contains() {
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void size() {
    }
}