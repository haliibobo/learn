package com.github.haliibobo.learn.java.tree;

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
        traversal(node1);
    }

    public static void traversal(TreeNode root) {
        if (root != null){
            System.out.print(root.value + " ");
            traversal(root.left);
            traversal(root.right);
        }
    }

    public static void traversal2(TreeNode root) {
        while (root != null){
            System.out.print(root.value + " ");
            traversal(root.left);
            traversal(root.right);
        }
    }
}
