package edu.princeton.cs.algs4.fundamentals.basic;

public class SingleyLinkedListUtils {
    private SingleyLinkedListUtils() {
    }

    /**
     * Reverse singly linked list.
     * @param head singly linked list.
     * @param <Item> value type.
     * @return inplace reversed linked list.
     */
    public static <Item> Node<Item> reverse(Node<Item> head) {
        Node<Item> prev = null, current = head, next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }
}
