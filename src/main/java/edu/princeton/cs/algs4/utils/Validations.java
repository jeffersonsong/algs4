package edu.princeton.cs.algs4.utils;

import java.util.NoSuchElementException;

public class Validations {
    private Validations() {
    }

    // check if index is in range [lo, hi)
    public static void checkIndexInRange(int index, int lo, int hi) {
        PreConditions.checkArgument (index >= lo && index < hi,
                "index %s is not in range [%s, %s)", index, lo, hi);
    }

    public static void noSuchElement(boolean condition) {
        if (condition) {
            throw new NoSuchElementException();
        }
    }

    public static void noSuchElement(boolean condition, String message) {
        if (condition) {
            throw new NoSuchElementException(message);
        }
    }
}
