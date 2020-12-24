package edu.princeton.cs.algs4.strings.tries;

public class TrieSTTest extends TrieBaseTest {

    @Override
    protected Trie<Integer> createTrie() {
        return new TrieST<>();
    }
}