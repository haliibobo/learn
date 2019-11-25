package com.github.haliibobo.learn.offer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class PrintFromTopToBottom {

    //从上往下打印出二叉树的每个节点，同层节点从左至右打印。

    static class TreeNode {

        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        LinkedBlockingQueue<TreeNode> queue = new LinkedBlockingQueue<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return list;
    }

    //非递归前序遍历二叉树
    private void nonRecurPreTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode;
        TreeNode tmp;
        currentNode = root;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null)//一直往一个方向走
            {
                System.out.print(currentNode.val + " ");//visit
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            if (!stack.isEmpty())//变换方向
            {
                tmp = stack.pop();
                currentNode = tmp.right;
            }
        }
    }


    //非递归中序遍历二叉树
    private void nonRecurInTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode;
        TreeNode tmp;
        currentNode = root;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null)//一直往一个方向走
            {
                //System.out.print(currentNode.val + " ");//visit
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            if (!stack.isEmpty())//变换方向
            {
                tmp = stack.pop();
                System.out.print(tmp.val + " ");//visit
                currentNode = tmp.right;
            }
        }
    }

    //递归前序遍历二叉树
    private void preOrder(TreeNode root) {
        if (root == null) {
            return;//base condition
        }
        System.out.print(root.val + " ");//visit
        preOrder(root.left);
        preOrder(root.right);
    }
}
