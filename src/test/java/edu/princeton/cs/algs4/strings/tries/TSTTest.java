package edu.princeton.cs.algs4.strings.tries;

public class TSTTest extends TrieBaseTest {

    @Override
    protected Trie<Integer> createTrie() {
        return new TST<>();
    }

}