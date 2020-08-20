package com.github.haliibobo.learn.leecode.tree;

import com.github.haliibobo.learn.java.tree.TreeNode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-07-20 15:06
 * @description describe what this class do
 */
public class Demo {


  //[6,2,8,0,4,7,9,null,null,2,6]
    @Test
        public void test (){
        TreeNode n1 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(8);
        TreeNode n4 = new TreeNode(0);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(7);
        TreeNode n7 = new TreeNode(9);
        TreeNode n8 = new TreeNode(2);
        TreeNode n9 = new TreeNode(6);
        n1.left =n2;
        n1.right=n3;
        n2.left = n4;
        n2.right =n5;
        n3.left = n6;
        n3.right =n7;
        n5.left =n8;
        n5.right =n9;

        System.out.println(Arrays.toString(findMode(n1)));

    }

    int max =0;
    int currCount =0;
    TreeNode pre =null;
    int retCount =0;
    int[] ret;

    public int[] findMode(TreeNode root) {
        if (root == null){
            return new int[0];
        }
        cal(root);
        pre = null;
        ret = new int[retCount];
        retCount = 0;
        currCount = 0;
        cal(root);
        return ret;


    }

    public void cal (TreeNode root){
        if (root == null){
            return;
        }

        cal(root.left);

        if (pre !=null && pre.val == root.val){
            currCount ++;
        }else{
            currCount =1;
        }

        if ( currCount > max){
            max = currCount;
            retCount =1;
        } else if (currCount == max){
            if (ret != null)
                ret[retCount] = root.val;
            retCount++;
        }

        pre = root;

        cal(root.right);
    }

}
