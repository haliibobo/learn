package com.github.haliibobo.learn.java.tree;

import java.util.Stack;

public class NLR {
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

        TreeNode node1 = new TreeNode<>(1);
        TreeNode node2 = new TreeNode<>(2);
        TreeNode node3 = new TreeNode<>(3);
        TreeNode node4 = new TreeNode<>(4);
        TreeNode node5 = new TreeNode<>(5);
        TreeNode node6 = new TreeNode<>(6);
        TreeNode node7 = new TreeNode<>(7);
        TreeNode node8 = new TreeNode<>(8);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.right = node5;
        node4.right = node6;
        node6.left = node7;
        node6.right = node8;
        traversalRecursion(node1);
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
        Stack<Integer>  stack = new Stack<>();
        TreeNode<Integer> curNode = root;
        stack.push(root.value);
        while (curNode != null || !stack.empty()){
                if (curNode.left !=null) {
                    stack.push(root.value);
                }else {

                }

        }
    }
}
