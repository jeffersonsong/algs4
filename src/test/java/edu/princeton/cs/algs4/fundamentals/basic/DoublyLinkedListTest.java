package edu.princeton.cs.algs4.fundamentals.basic;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.algs4.fundamentals.basic.DoublyLinkedList.Node;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DoublyLinkedListTest {
    @Test
    public void test() {
        DoublyLinkedList<Integer> dlist = new DoublyLinkedList<>();

        assertTrue(dlist.isEmpty());
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Map<Integer, Node<Integer>> map = new HashMap<>();
        for (Integer num : arr) {
            Node<Integer> node = dlist.addLast(num);
            map.put(num, node);
        }

        assertFalse(dlist.isEmpty());

        List<Integer> list = toList(dlist);
        assertThat(list, is(asList(arr)));

        dlist.remove(map.get(5));
        list = toList(dlist);
        assertThat(list, is(asList(1, 2, 3, 4, 6, 7, 8, 9, 10)));

        dlist.removeFirst();
        list = toList(dlist);
        assertThat(list, is(asList(2, 3, 4, 6, 7, 8, 9, 10)));
    }
}