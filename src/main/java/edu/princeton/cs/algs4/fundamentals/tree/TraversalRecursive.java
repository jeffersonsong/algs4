package edu.princeton.cs.algs4.fundamentals.tree;

import java.util.function.Consumer;

public class TraversalRecursive {
    public static <T> void inOrder(TreeNode<T> node, Consumer<TreeNode<T>> visitor) {
        if (node == null) return;
        inOrder(node.left, visitor);
        visitor.accept(node);
        inOrder(node.right, visitor);
    }

    public static <T> void preOrder(TreeNode<T> node, Consumer<TreeNode<T>> visitor) {
        if (node == null) return;
        visitor.accept(node);
        preOrder(node.left, visitor);
        preOrder(node.right, visitor);
    }

    public static <T> void postOrder(TreeNode<T> node, Consumer<TreeNode<T>> visitor) {
        if (node == null) return;
        postOrder(node.left, visitor);
        postOrder(node.right, visitor);
        visitor.accept(node);
    }
}
