package com.github.haliibobo.learn.leecode.tree;

public class ReBuildTree {

    public static void main(String[] args) {
        ReBuildTree tree = new ReBuildTree();
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        tree.buildTree(inorder,postorder);
    }

    public static class TreeNode {
      int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        if (inorder.length == 0 || postorder.length == 0) {
            return null;
        }
        int size = inorder.length;
        TreeNode root = new TreeNode(postorder[size - 1]);
        int cur = 0;
        int pre = 0;
        for (int i = 0; i< size;i ++){
            if (inorder[i] ==  postorder[size-1]) {
                cur = i;
                pre = cur-1;
                break;
            }
        }
        // 拆分左右子树前序遍历
        int[] inorderLeft = new int[cur];
        for (int i = 0 ; i < cur;i++) {
            inorderLeft[i] = inorder[i];
        }
        int[] inorderRight = new int[size -1 -cur];
        for (int i = 0 ; i < size -1 -cur-1;i++) {
            inorderRight[i] = inorder[i+cur+1];
        }
        // 拆分左右子树后序遍历
        int[] postorderLeft = new int[0];
        int[] postorderRight = new int[0];
        int pos = -1;
        if (pre > -1 ){
            for (int i = 0 ;i < size-2;i ++) {
                if (postorder[i] == inorder[pre]) {
                    pos = i;
                    break;
                }
            }
        }

        postorderLeft = new int[pos+1];
        for (int i = 0 ; i < pos+1;i++) {
            postorderLeft[i] = postorder[i];
        }

        postorderRight =new int[size-1-pos-1];
        for (int i = 0 ; i < size-1-pos-1-1;i++) {
            postorderRight[i] = postorder[pos+i+1];
        }
        root.left = buildTree (inorderLeft,postorderLeft);
        root.right = buildTree (inorderRight,postorderRight);
        return root;

    }
}
