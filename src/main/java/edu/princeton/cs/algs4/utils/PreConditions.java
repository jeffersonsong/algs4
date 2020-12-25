package edu.princeton.cs.algs4.utils;

public class PreConditions {
    private PreConditions() {
    }

    public static void requiresNotNull(Object val) {
        checkArgument(val != null);
    }

    public static void requiresNotNull(Object val, String msg) {
        checkArgument(val != null, msg);
    }

    public static void checkArgument(boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean condition, String template, Object... params) {
        if (!condition) {
            if (params != null && params.length > 0) {
                throw new IllegalArgumentException(String.format(template, params));
            } else {
                throw new IllegalArgumentException(template);
            }
        }
    }

    public static void checkState(boolean condition, String template, Object... params) {
        if (!condition) {
            if (params != null && params.length > 0) {
                throw new IllegalStateException(String.format(template, params));
            } else {
                throw new IllegalStateException(template);
            }
        }
    }

    // check if index is in range [lo, hi)
    public static void checkIndexInRange(int index, int lo, int hi) {
        checkArgument (index >= lo && index < hi,
                "index %s is not in range [%s, %s)", index, lo, hi);
    }
}
