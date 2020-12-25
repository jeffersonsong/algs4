package edu.princeton.cs.algs4.fundamentals.tree;

import org.junit.Before;
import org.junit.Test;

import static edu.princeton.cs.algs4.fundamentals.tree.TraversalIterative.*;
import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TraversalIterativeTest {
    private TreeNode<Integer> root;

    @Before
    public void setUp() {
        root = new TreeNode<>(5);
        root.left = new TreeNode<>(3);
        root.right = new TreeNode<>(4);
        root.left.left = new TreeNode<>(1);
        root.left.right = new TreeNode<>(2);
    }

    @Test
    public void testInOrder() {
        Iterable<Integer> result = inOrder(root);
        assertThat(toList(result), is(asList(1, 3, 2, 5, 4)));
    }

    @Test
    public void testInOrder2() {
        root = new TreeNode<>(5);
        root.left = new TreeNode<>(3);
        root.right = new TreeNode<>(4);
        root.left.left = new TreeNode<>(1);

        Iterable<Integer> result = inOrder(root);
        assertThat(toList(result), is(asList(1, 3, 5, 4)));
    }

    @Test
    public void testPreOrder() {
        Iterable<Integer> result = preOrder(root);
        assertThat(toList(result), is(asList(5, 3, 1, 2, 4)));
    }

    @Test
    public void testPostOrder() {
        Iterable<Integer> result = postOrder(root);
        assertThat(toList(result), is(asList(1, 2, 3, 4, 5)));
    }

    @Test
    public void testLevelOrder() {
        Iterable<Integer> result = levelOrder(root);
        assertThat(toList(result), is(asList(5, 3, 4, 1, 2)));
    }
}
