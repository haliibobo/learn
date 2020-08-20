package com.github.haliibobo.learn.offer;

import com.github.haliibobo.learn.java.tree.TreeNode;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-11-10 17:05
 * @description describe what this class do
 */
public class ConvertTree {


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        node5.left = node3;
        node5.right = node7;
        node7.left = node6;
        node7.right = node8;
        node3.left = node2;
        node3.right = node4;

        node2.left = node1;
        Convert(node5);
    }
    /*
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
     * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
     * 先将左子树变为有序的排序链表，再将右子树变为有序的链表，
     * 然后将当前结点插入在两个链表中间就可以了
     * 需要注意左子树和右子树为空的情况
     */

    private static TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }

        //转换左右子树，转换后返回左右子树的最左子节点
        //TODO 为什么转换后left是左子树最左子节点 因为每一次调用函数返回的是最左子节点
        TreeNode left = Convert(pRootOfTree.left);
        TreeNode right = Convert(pRootOfTree.right);

        //找到左子树的最右子节点：
        TreeNode tmp = pRootOfTree.left;
        if (tmp != null) {
            while (tmp.right != null) {
                tmp = tmp.right;
            }

            //最右子节点的右指针指向根节点
            tmp.right = pRootOfTree;
        }

        //根结点的右指针指向右子树
        pRootOfTree.right = right;

        if (right != null) {
            //右子树的最左子节点指向根节点
            right.left = pRootOfTree;
        }

        return left == null ? pRootOfTree : left;
    }

    //中序遍历
    private static TreeNode convertIn(TreeNode pRootOfTree,TreeNode pre){
        if (pRootOfTree == null) {
            return null;
        }

        //TreeNode pre = null;
        convertHelper(pRootOfTree,pre);

        TreeNode head = pRootOfTree;
        if(head.left !=null){
            while (head.left !=null){
                head = head.left;
            }
        }
        return head;
    }

    private static void convertHelper(TreeNode current,TreeNode pre) {
        if (current == null) {
            return;
        }

        convertHelper(current.left,pre);
        current.left = pre;
        if (pre != null) {
            pre.right = current;
        }
        pre = current;

        convertHelper(current.right,pre);
    }
}
