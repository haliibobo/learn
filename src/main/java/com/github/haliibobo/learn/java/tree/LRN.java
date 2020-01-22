package com.github.haliibobo.learn.java.tree;

import java.util.Stack;

public class LRN {
    /**
     *            1
     *           / \
     *          2   3
     *         /     \
     *         4     5
     *          \
     *          6
     *         / \
     *         7 8
     * @param args
     */
    public static void main(String[] args) {

        TreeNode<Integer> node1 = new TreeNode<>(1);
        TreeNode<Integer> node2 = new TreeNode<>(2);
        TreeNode<Integer> node3 = new TreeNode<>(3);
        TreeNode<Integer> node4 = new TreeNode<>(4);
        TreeNode<Integer> node5 = new TreeNode<>(5);
        TreeNode<Integer> node6 = new TreeNode<>(6);
        TreeNode<Integer> node7 = new TreeNode<>(7);
        TreeNode<Integer> node8 = new TreeNode<>(8);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.right = node5;
        node4.right = node6;
        node6.left = node7;
        node6.right = node8;
        traversalRecursion(node1);
        System.out.println();
        traversal(node1);
    }

    /**
     * 递归
     * @param root
     */
    public static void traversalRecursion(TreeNode root) {
        if (root != null){
            System.out.print(root.value + " ");
            traversalRecursion(root.left);
            traversalRecursion(root.right);
        }
    }

    /**
     * 非递归
     * @param root
     */
    public static void traversal(TreeNode<Integer> root) {
        Stack<TreeNode<Integer>>  stack = new Stack<>();
        TreeNode<Integer> curNode = root;
        while (curNode != null || !stack.empty()){
            if (curNode != null) {
                System.out.print(curNode.value + " ");
                if (curNode.right != null) {
                    stack.push(curNode.right);
                }
                curNode = curNode.left;
            } else if (!stack.empty()) {
                curNode = stack.pop();
            }
        }
    }
}
