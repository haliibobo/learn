package com.github.haliibobo.learn.offer;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-07-23 11:07
 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度
 */
public class TreeDepth {



    public int treeDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        return Math.max(treeDepth(root.left)+1,treeDepth(root.right)+1);
    }

}