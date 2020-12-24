package edu.princeton.cs.algs4.strings.search;

public interface SubstringSearch {
    /**
     * Search pattern inside text
     *
     * @param pat pattern.
     * @param txt text.
     * @return index in text where pattern starts if found, otherwise return string length;
     */
    int search(String pat, String txt);
}
