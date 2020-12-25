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
}
