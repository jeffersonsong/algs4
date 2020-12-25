package edu.princeton.cs.algs4.fundamentals.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static java.util.Collections.emptyList;

public class TraversalIterative {

    // In Order - left, root, right
    public static <T> List<T> inOrder(TreeNode<T> root) {
        if (root == null) return emptyList();
        List<T> result = new LinkedList<>();
        Stack<TreeNode<T>> stack = new Stack<>();
        TreeNode<T> curr = root;
        // push left most into stack
        // as the stack unwind, pop the node, add to result
        // push right to the stack
        // repeat.
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.data);
            curr = curr.right;
        }

        return result;
    }

    // pre order - root, left, right
    public static <T> List<T> preOrder(TreeNode<T> root) {
        if (root == null) return emptyList();
        List<T> result = new LinkedList<>();
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            result.add(root.data);
            if (root.right != null) stack.push(root.right);
            if (root.left != null) stack.push(root.left);
        }

        return result;
    }

    public static <T> List<T> postOrder(TreeNode<T> root) {
        if (root == null) return emptyList();
        LinkedList<T> result = new LinkedList<>();
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            result.addFirst(root.data);
            if (root.left != null) stack.push(root.left);
            if (root.right != null) stack.push(root.right);
        }

        return result;
    }
}
