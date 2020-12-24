package edu.princeton.cs.algs4.utils;

public class PreConditions {
    private PreConditions() {
    }

    public static void requiresNotNull(Object val, String msg) {
        checkArgument(val != null, msg);
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
}
