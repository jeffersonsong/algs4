package edu.princeton.cs.algs4.strings.tries;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.*;

public abstract class TrieBaseTest {
    protected Trie<Integer> st;

    @Before
    public void setUp() {
        st = createTrie();
    }

    protected abstract Trie<Integer> createTrie();

    @Test
    public void test() {
        assertTrue(st.isEmpty());
        assertThat(st.size(), is(0));

        st.put("shells", 3);
        st.put("by", 4);
        st.put("sells", 1);
        st.put("sea", 6);
        st.put("she", 0);
        st.put("shore", 7);
        st.put("the", 5);
        assertThat(st.size(), is(7));


        assertThat(st.get("by"), is(4));
        assertThat(st.get("sea"), is(6));
        assertThat(st.get("sells"), is(1));
        assertThat(st.get("she"), is(0));
        assertThat(st.get("shells"), is(3));
        assertThat(st.get("shore"), is(7));
        assertThat(st.get("the"), is(5));

        List<String> keys = toList(st.keys());
        assertThat(keys.size(), is(st.size()));
        assertThat(keys, hasItems("by", "sea", "sells", "she", "shells", "shore", "the"));

        keys = toList(st.keysWithPrefix("sh"));
        assertThat(keys.size(), is(3));
        assertThat(keys, hasItems("she", "shells", "shore"));

        assertThat(st.longestPrefixOf("she"), is("she"));
        assertThat(st.longestPrefixOf("shell"), is("she"));
        assertThat(st.longestPrefixOf("shellsort"), is("shells"));

        assertFalse(st.contains("shell"));
        assertFalse(st.contains("shel"));
        assertFalse(st.contains("shelter"));

        st.delete("she");
        assertThat(st.size(), is(6));
        assertFalse(st.contains("she"));
        assertTrue(st.contains("shells"));

        st.put("shells", null);
        assertFalse(st.contains("shells"));
        assertThat(st.size(), is(5));

        st.delete("by");
        st.delete("sea");
        st.delete("by");
        st.delete("sells");
        st.delete("shore");
        st.delete("the");

        assertTrue(st.isEmpty());
        assertThat(st.size(), is(0));
    }
}
