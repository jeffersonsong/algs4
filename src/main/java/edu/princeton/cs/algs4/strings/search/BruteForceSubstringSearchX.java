package edu.princeton.cs.algs4.strings.search;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

public class BruteForceSubstringSearchX implements SubstringSearch {

    @Override
    public int search(String pat, String txt) {
        checkArgument(pat != null && pat.length() > 0, "Empty pattern.");
        checkArgument(txt != null && txt.length() > 0, "Empty text.");

        int M = pat.length();
        int N = txt.length();
        int i, j;

        for (i = 0, j = 0; i < N && j < M; i++) {
            if (txt.charAt(i) == pat.charAt(j)) j++;
            else {
                i -= j;
                j = 0;
            }
        }

        if (j == M) return i - M;
        else return N;
    }
}
