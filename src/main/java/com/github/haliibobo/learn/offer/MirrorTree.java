package com.github.haliibobo.learn.offer;

public class MirrorTree {
    /*操作给定的二叉树，将其变换为源二叉树的镜像。
    输入描述:
    二叉树的镜像定义：源二叉树
                  8
                /   \
                6   10
                / \  / \
                5  7 9 11
    镜像二叉树
                  8
                /   \
                10   6
                / \  / \
                11 9 7  5
    */

    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        //交换左右子节点
        TreeNode tmp;
        tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        //左子节点不空，递归的交换左子树
        if (root.left != null) {
            Mirror(root.left);
        }
        //右子节点不空，递归的交换右子树
        if (root.right != null) {
            Mirror(root.right);
        }
    }
}
