package edu.princeton.cs.algs4.fundamentals.basic;

public class Node<Item> {
    public final Item item;
    public Node<Item> next;

    public Node(Item item) {
        this.item = item;
    }

    public Node(Item item, Node<Item> next) {
        this.item = item;
        this.next = next;
    }
}
