package edu.princeton.cs.algs4.strings.search;


import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

public class BruteForceSubstringSearch implements SubstringSearch {

    @Override
    public int search(String pat, String txt) {
        checkArgument(pat != null && pat.length() > 0, "Empty pattern.");
        checkArgument(txt != null && txt.length() > 0, "Empty text.");

        int M = pat.length();
        int N = txt.length();

        for (int i = 0; i <= N - M; i++) {
            int j = 0;
            while (j < M && txt.charAt(i + j) == pat.charAt(j)) {
                j++;
            }
            if (j == M) return i; // index in text where pattern starts
        }
        return N; // Not found
    }
}
