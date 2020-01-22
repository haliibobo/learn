package com.github.haliibobo.learn.offer;

import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-01-22 14:50
 * @description describe what this class do
 */
public class ReConstructBinaryTree {

    @Test
    public void solution (){
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        System.out.println(reConstructBinaryTree(pre,in).val);
    }



    // 递归
    private TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length == 0){
            return null;

        }
        if (pre.length == 1){
            return new TreeNode(pre[0]);
        }
        //前序遍历：根左右，第一个为根节点
        TreeNode root = new TreeNode(pre[0]);
        //中序遍历：左根右，可以根据前序遍历的根拆分左右子树
        int rootIndex =0;
        for (int i = 0; i < in.length; i++) {
            if (pre[0] == in[i]){
                rootIndex =i;
                break;
            }
        }
        int[] leftIn = new int[rootIndex];
        int[] rightIn = new int[in.length-1-rootIndex];
        System.arraycopy(in,0,leftIn,0,leftIn.length);
        System.arraycopy(in,rootIndex+1,rightIn,0,rightIn.length);

        int[] leftPre = new int[rootIndex];
        int[] rightPre = new int[in.length-1-rootIndex];
        System.arraycopy(pre,1,leftPre,0,leftPre.length);
        System.arraycopy(pre,rootIndex+1,rightPre,0,rightPre.length);

        root.left = reConstructBinaryTree(leftPre,leftIn);
        root.right = reConstructBinaryTree(rightPre,rightIn);

        return root;
    }

}
