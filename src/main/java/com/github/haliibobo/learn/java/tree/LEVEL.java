package com.github.haliibobo.learn.java.tree;

import java.util.*;

/**
 * 层次遍历
 */
public class LEVEL {
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
        nonRecurInTraverse(node1);
    }

    /**
     * 递归
     * @param root
     */
    public static void traversalRecursion(TreeNode root) {
        if (root != null){
            traversalRecursion(root.left);
            System.out.print(root.val + " ");
            traversalRecursion(root.right);
        }
    }

    //非递归层次遍历二叉树
    private static void nonRecurInTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> treeNodes = new ArrayDeque<>();
        treeNodes.push(root);
        while (!treeNodes.isEmpty()){
            int size = treeNodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = treeNodes.pop();
                list.add(node.val);
                if (node.left !=null) {
                    treeNodes.add(node.left);
                }
                if (node.right !=null) {
                    treeNodes.add(node.right);
                }
            }
        }
        System.out.print(Arrays.toString(list.toArray()));
    }
}
