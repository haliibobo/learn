package com.github.haliibobo.learn.java.tree;

import java.util.*;

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
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        node1.right=node3;
        node1.left=node2;
        node2.left=node4;
        node3.right=node5;
        node4.right=node6;
        node6.left=node7;
        node6.right=node8;
        traversalRecursion(node1);
        System.out.println();
        nonTraversalRecursion(node1);
    }

    /**
     * 递归
     * @param root
     */
    public static void traversalRecursion(TreeNode root) {
        if (root != null){
            traversalRecursion(root.left);
            traversalRecursion(root.right);
            System.out.print(root.val + " ");
        }
    }

    /**
     * 非递归
     * @param root
     */
    public static void nonTraversalRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<Integer> deque  = new ArrayDeque<>();
        Deque<TreeNode> treeNodes = new ArrayDeque<>();
        treeNodes.push(root);
        while (!treeNodes.isEmpty()){
            int size = treeNodes.size();
            for (int i = 0; i <size ; i++) {
                TreeNode node = treeNodes.pop();
                deque.push(node.val);
                if (node.left != null) {
                    treeNodes.push(node.left);
                }
                if (node.right != null) {
                    treeNodes.push(node.right);
                }

            }
        }
        System.out.print(Arrays.toString(deque.toArray()));

    }
}
