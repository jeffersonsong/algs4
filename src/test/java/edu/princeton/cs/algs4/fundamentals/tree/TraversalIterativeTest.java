package edu.princeton.cs.algs4.fundamentals.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.tree.TraversalIterative.*;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
        List<Integer> list = inOrder(root);
        assertThat(list, is(asList(1, 3, 2, 5, 4)));
    }

    @Test
    public void testInOrder2() {
        root = new TreeNode<>(5);
        root.left = new TreeNode<>(3);
        root.right = new TreeNode<>(4);
        root.left.left = new TreeNode<>(1);

        List<Integer> list = inOrder(root);
        assertThat(list, is(asList(1, 3, 5, 4)));
    }

    @Test
    public void testPreOrder() {
        List<Integer> list = preOrder(root);
        assertThat(list, is(asList(5, 3, 1, 2, 4)));
    }

    @Test
    public void testPostOrder() {
        List<Integer> list = postOrder(root);
        assertThat(list, is(asList(1, 2, 3, 4, 5)));
    }
}