package edu.princeton.cs.algs4.fundamentals.tree;

public class TreeNode<T> {
    public T data;
    public TreeNode<T> left, right;

    public TreeNode(T data) {
        this.data = data;
    }

    public String toString() {
        return String.valueOf(data);
    }
}
