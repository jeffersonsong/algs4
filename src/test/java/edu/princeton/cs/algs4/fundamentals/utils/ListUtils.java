package edu.princeton.cs.algs4.fundamentals.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    private ListUtils() {
    }

    public static <Item> List<Item> toList(final Iterable<Item> iterable) {
        final List<Item> list = new ArrayList<>();
        for (final Item item : iterable) {
            list.add(item);
        }
        return list;
    }
}
