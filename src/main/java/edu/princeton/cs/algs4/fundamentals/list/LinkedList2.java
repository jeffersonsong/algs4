package edu.princeton.cs.algs4.fundamentals.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static edu.princeton.cs.algs4.utils.Validations.checkIndexInRange;
import static edu.princeton.cs.algs4.utils.Validations.noSuchElement;

public class LinkedList2<Item> implements List<Item> {
    private final Node<Item> dummyHead;
    private Node<Item> last;
    private int n;

    private static class Node<Item> {
        final Item item;
        Node<Item> next;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }

    public LinkedList2() {
        dummyHead = new Node<>(null);
        last = dummyHead;
        n = 0;
    }

    @Override
    public boolean isEmpty() {
        return last == dummyHead;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void addFront(Item item) {
        dummyHead.next = new Node<>(item, dummyHead.next);
        n++;
        if (size() == 1) last = dummyHead.next;
    }

    @Override
    public void addBack(Item item) {
        last.next = new Node<>(item);
        last = last.next;
        n++;
    }

    @Override
    public Item deleteFront() {
        noSuchElement(isEmpty(), "Empty list");

        Node<Item> first = dummyHead.next;
        dummyHead.next = first.next;
        first.next = null;
        n--;
        if (size() == 0) last = dummyHead;

        return first.item;
    }

    @Override
    public Item deleteBack() {
        noSuchElement(isEmpty(), "Empty list");

        Item result = last.item;
        Node<Item> prev = dummyHead;
        while (prev.next != last) {
            prev = prev.next;
        }
        prev.next = null;
        last = prev;
        n--;
        return result;
    }

    @Override
    public void delete(Item item) {
        noSuchElement(isEmpty(), "Empty list");

        Node<Item> prev = dummyHead;
        while (prev.next != null && !Objects.equals(prev.next.item, item)) {
            prev = prev.next;
        }

        if (prev.next == null) {
            throw new NoSuchElementException("Item not found");

        } else {
            if (prev.next == last) {
                last = prev;
            }
            Node<Item> next = prev.next;
            prev.next = next.next;
            next.next = null;
            n--;
        }
    }

    @Override
    public void add(int i, Item item) {
        checkIndexInRange(i, 0, size() + 1);
        Node<Item> prev = getPrevNode(i);

        prev.next = new Node<>(item, prev.next);
        n++;
        if (last == prev) last = last.next;
    }

    private Node<Item> getPrevNode(int i) {
        checkIndexInRange(i, 0, size() + 1);
        Node<Item> prev = dummyHead;
        for (int k = 0; k < i; k++) {
            prev = prev.next;
        }
        return prev;
    }

    @Override
    public Item delete(int i) {
        noSuchElement(isEmpty(), "Empty list");
        noSuchElement(i >= size(), "No such item.");

        Node<Item> prev = getPrevNode(i);
        Node<Item> next = prev.next;

        if (next == last) {
            last = prev;
        }
        prev.next = next.next;
        next.next = null;
        n--;

        return next.item;
    }

    @Override
    public boolean contains(Item item) {
        Node<Item> node = dummyHead.next;

        while (node != null && !Objects.equals(node.item, item)) {
            node = node.next;
        }
        return node != null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(dummyHead.next);
    }

    private static class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            noSuchElement(!hasNext());
            Item result = current.item;
            current = current.next;
            return result;
        }
    }
}
