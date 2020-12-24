package edu.princeton.cs.algs4.strings.search;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public abstract class SubstringSearchBaseTest {
    private SubstringSearch search;

    @Before
    public void setUp() {
        search = createSubstringSearch();
    }

    protected abstract SubstringSearch createSubstringSearch();

    @Test
    public void testSearchNeedleInAHay() {
        String text = "INAHAYSTACKNEEDLEINA";
        String pat = "NEEDLE";
        assertThat(search.search(pat, text), is(11));
    }

    @Test
    public void test1() {
        String text = "ABACADABRAC";
        String pat = "ABRA";
        assertThat(search.search(pat, text), is(6));
    }

    @Test
    public void test2() {
        String text = "AAAAAAAAAB";
        String pat = "AAAAB";
        assertThat(search.search(pat, text), is(5));
    }

    @Test
    public void test3() {
        String text = "ABAAAABAAAAAAAAA";
        String pat = "BAAAAAAAAA";
        assertThat(search.search(pat, text), is(6));
    }

    @Test
    public void testKMP() {
        String text = "AABACAABABACAA";
        String pat = "ABABAC";
        assertThat(search.search(pat, text), is(6));
    }
}