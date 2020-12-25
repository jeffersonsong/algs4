package edu.princeton.cs.algs4.fundamentals.tree;

import edu.princeton.cs.algs4.fundamentals.queue.LinkedQueue;
import edu.princeton.cs.algs4.fundamentals.queue.Queue;
import edu.princeton.cs.algs4.fundamentals.stack.LinkedStack;
import edu.princeton.cs.algs4.fundamentals.stack.Stack;

import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.emptyList;

public class TraversalIterative {

    // In Order - left, root, right
    public static <T> Iterable<T> inOrder(TreeNode<T> root) {
        if (root == null) return emptyList();
        Queue<T> result = new LinkedQueue<>();
        Stack<TreeNode<T>> stack = new LinkedStack<>();
        TreeNode<T> node = root;
        // push left most into stack
        // as the stack unwind, pop the node, add to result
        // push right to the stack
        // repeat.
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            result.enqueue(node.data);
            node = node.right;
        }

        return result;
    }

    // pre order - root, left, right
    public static <T> Iterable<T> preOrder(TreeNode<T> root) {
        if (root == null) return emptyList();
        Queue<T> result = new LinkedQueue<>();
        Stack<TreeNode<T>> stack = new LinkedStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<T> node = stack.pop();
            result.enqueue(node.data);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return result;
    }

    public static <T> List<T> postOrder(TreeNode<T> root) {
        if (root == null) return emptyList();
        LinkedList<T> result = new LinkedList<>();
        Stack<TreeNode<T>> stack = new LinkedStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<T> node = stack.pop();
            result.addFirst(node.data);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }

        return result;
    }

    public static <T> Iterable<T> levelOrder(TreeNode<T> root) {
        Queue<T> keys = new LinkedQueue<>();
        Queue<TreeNode<T>> queue = new LinkedQueue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.dequeue();
            if (node == null) continue;
            keys.enqueue(node.data);
            queue.enqueue(node.left);
            queue.enqueue(node.right);
        }
        return keys;
    }
}
