package edu.princeton.cs.algs4.searching.st;

import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public abstract class OrderedSTBaseTest extends STBaseTest {

    protected OrderedST<Character, Integer> orderedST;

    @Override
    protected ST<Character, Integer> createST() {
        orderedST = createOrderedST();
        return orderedST;
    }

    protected abstract OrderedST<Character, Integer> createOrderedST();

    @Test
    public void testMinMax() {
        insert("SEXARCHM");

        assertThat(orderedST.min(), is('A'));
        assertThat(orderedST.max(), is('X'));
    }

    @Test
    public void testFloorCeiling() {
        insert("SEXARCHM");

        assertThat(orderedST.floor('O'), is('M'));
        assertThat(orderedST.ceiling('D'), is('E'));
    }

    @Test
    public void testRankSelect() {
        insert("SEXARCHM");

        assertThat(orderedST.rank('F'), is(3));
        assertThat(orderedST.select(4), is('M'));
    }

    @Test
    public void testDeleteMin() {
        insert("SEXARCHM");

        Character min = orderedST.min();
        assertTrue(orderedST.contains(min));
        orderedST.deleteMin();
        assertFalse(orderedST.contains(min));
    }

    @Test
    public void testDeleteMax() {
        insert("SEXARCHM");

        Character max = orderedST.max();
        assertTrue(orderedST.contains(max));
        orderedST.deleteMax();
        assertFalse(orderedST.contains(max));
    }

    @Test
    public void testSizeRange() {
        insert("SEXARCHM");
        assertThat(orderedST.size('A', 'M'), is(5));
        assertThat(orderedST.size('B', 'K'), is(3));
        assertThat(orderedST.size('A', 'K'), is(4));
    }

    @Test
    public void testKeys() {
        insert("SEXARCHM");
        List<Character> keys = toList(orderedST.keys('B', 'K'));
        assertThat(keys, is(asList('C', 'E', 'H')));
    }

    protected void insert(String text) {
        for (int i = 0; i < text.length(); i++) {
            st.put(text.charAt(i), i);
        }
    }
}