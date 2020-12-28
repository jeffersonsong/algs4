package edu.princeton.cs.algs4.fundamentals.list;

import edu.princeton.cs.algs4.fundamentals.basic.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static edu.princeton.cs.algs4.utils.Validations.checkIndexInRange;
import static edu.princeton.cs.algs4.utils.Validations.noSuchElement;

public class LinkedList<Item> implements List<Item> {
    private Node<Item> first, last;
    private int n;

    public LinkedList() {
        first = null;
        last = null;
        n = 0;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void addFront(Item item) {
        first = new Node<>(item, first);
        n++;
        if (last == null) last = first;
    }

    @Override
    public void addBack(Item item) {
        if (last == null) {
            addFront(item);

        } else {
            last.next = new Node<>(item);
            last = last.next;
            n++;
        }
    }

    @Override
    public Item deleteFront() {
        noSuchElement(isEmpty(), "Empty list");
        Node<Item> oldFirst = first;
        first = first.next;
        oldFirst.next = null;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return oldFirst.item;
    }

    @Override
    public Item deleteBack() {
        noSuchElement(isEmpty(), "Empty list");
        Item result = last.item;

        if (first == last) {
            first = null;
            last = null;
        } else {
            Node<Item> prev = first;
            while (prev.next != last) {
                prev = prev.next;
            }
            prev.next = null;
            last = prev;
        }

        n--;
        return result;
    }

    @Override
    public void delete(Item item) {
        noSuchElement(isEmpty(), "Empty list");

        if (Objects.equals(first.item, item)) {
            deleteFront();

        } else {
            Node<Item> prev = first;

            while (prev.next != null && !Objects.equals(prev.next.item, item)) {
                prev = prev.next;
            }

            if (prev.next == null) throw new NoSuchElementException("No item found.");

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

        if (i == 0) {
            addFront(item);
        } else {
            Node<Item> prev = getNode(i - 1);
            Node<Item> node = new Node<>(item, prev.next);
            prev.next = node;
            n++;
            if (i == size() - 1) last = node;
        }
    }

    private Node<Item> getNode(int i) {
        assert i >= 0 && i < size();

        Node<Item> node = first;
        for (int k = 0; k < i && node != null; k++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public Item delete(int i) {
        noSuchElement(isEmpty(), "Empty list");
        noSuchElement(i >= size(), "No such item.");

        if (i == 0) {
            return deleteFront();

        } else {
            Node<Item> prev = getNode(i - 1);
            Node<Item> next = prev.next;
            prev.next = next.next;
            next.next = null;
            n--;
            if (i == size()) last = prev;
            return next.item;
        }
    }

    @Override
    public boolean contains(Item item) {
        Node<Item> node = first;

        while (node != null && !Objects.equals(node.item, item)) {
            node = node.next;
        }
        return node != null;
    }


    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(first);
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
