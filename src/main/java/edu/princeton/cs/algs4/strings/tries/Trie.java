package edu.princeton.cs.algs4.strings.tries;

import edu.princeton.cs.algs4.searching.st.ST;

public interface Trie<Value> extends ST<String, Value> {
    Iterable<String> keysWithPrefix(String prefix);

    Iterable<String> keysThatMatch(String pattern);

    String longestPrefixOf(String query);
}
