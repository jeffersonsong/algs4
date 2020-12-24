package edu.princeton.cs.algs4.fundamentals.unionfind;

public class UFTestUtils {
    private UFTestUtils() {
    }

    public static boolean withinSameComponent(final UF uf, final int[] c) {
        final int id = uf.find(c[0]);
        for (int i = 1; i < c.length; i++) {
            if (uf.find(c[i]) != id) {
                return false;
            }
        }
        return true;
    }
}
