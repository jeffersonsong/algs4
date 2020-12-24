package edu.princeton.cs.algs4.searching.st;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class STBaseTest {

    protected ST<Character, Integer> st;

    @Before
    public void setUp() throws Exception {
        st = createST();
    }

    protected abstract ST<Character, Integer> createST();

    @Test
    public void test() {
        assertTrue(st.isEmpty());
        assertThat(st.size(), is(0));

        String text = "SEARCHEXAMPLE";
        for (int i = 0; i < text.length(); i++) {
            st.put(text.charAt(i), i);
        }

        assertFalse(st.isEmpty());
        assertThat(st.size(), is(10));

        List<Character> keys = toList(st.keys());
        assertThat(keys, hasItems('A', 'C', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'X'));

        assertThat(st.get('A'), is(8));
        assertThat(st.get('C'), is(4));
        assertThat(st.get('E'), is(12));
        assertThat(st.get('H'), is(5));
        assertThat(st.get('L'), is(11));
        assertThat(st.get('M'), is(9));
        assertThat(st.get('P'), is(10));
        assertThat(st.get('R'), is(3));
        assertThat(st.get('S'), is(0));
        assertThat(st.get('X'), is(7));

        st.put('R', 13);
        assertThat(st.get('R'), is(13));

        st.put('R', null);
        assertThat(st.get('R'), is(nullValue()));
        assertThat(st.size(), is(9));

        st.delete('X');
        assertThat(st.size(), is(8));

        st.delete('A');
        st.delete('C');
        st.delete('E');
        st.delete('H');
        st.delete('L');
        st.delete('M');
        st.delete('P');

        assertThat(st.size(), is(1));
        st.delete('S');

        assertTrue(st.isEmpty());
        assertThat(st.size(), is(0));
    }
}