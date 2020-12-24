package edu.princeton.cs.algs4.strings.sort;

/**
 * Key-indexed counting
 */
public class KeyIndexedCounting {
    private static final int R = 256;

    // sort char array of alphabets
    public static void sort(char[] a) {
        int[] count = new int[R + 1];
        char[] aux = new char[a.length];

        keyIndexedCounting(a, aux, count);
    }

    private static void keyIndexedCounting(char[] a, char[] aux, int[] count) {
        // Compute frequency counts.
        for (char c : a) count[c + 1]++;

        // Transform counts to indices.
        for (int r = 0; r < R; r++)
            count[r + 1] += count[r];

        // Distribute the data.
        for (char c : a) aux[count[c]++] = c;

        System.arraycopy(aux, 0, a, 0, a.length);
    }
}
