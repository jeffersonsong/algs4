package edu.princeton.cs.algs4.fundamentals.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static edu.princeton.cs.algs4.utils.PreConditions.checkState;

public class DoublyLinkedList<Item> implements Iterable<Item> {
    public static class Node<Item> {
        final Item data;
        Node<Item> next, prev;

        public Node(Item data) {
            this.data = data;
        }
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator() {
            current = head.next;
        }

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item data = current.data;
            current = current.next;
            return data;
        }
    }

    // sentinel node. Not used for storing data.
    // greatly simplify the boundary condition handling.
    private final Node<Item> head;
    private final Node<Item> tail;

    public DoublyLinkedList() {
        head = new Node<>(null);
        tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
    }

    public Node<Item> addLast(Item data) {
        Node<Item> node = new Node<>(data);
        node.prev = tail.prev;
        tail.prev.next = node;
        node.next = tail;
        tail.prev = node;
        return node;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("List is empty");

        Node<Item> node = head.next;
        remove(node);
        return node.data;
    }

    public void remove(Node<Item> node) {
        checkState(node.prev != null && node.next != null, "Invalid node");

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public boolean isEmpty() {
        return head.next == tail;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
}
