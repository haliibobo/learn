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
        TreeNode<Integer> n1 = new TreeNode<>(6);
        TreeNode<Integer> n2 = new TreeNode<>(2);
        TreeNode<Integer> n3 = new TreeNode<>(8);
        TreeNode<Integer> n4 = new TreeNode<>(0);
        TreeNode<Integer> n5 = new TreeNode<>(4);
        TreeNode<Integer> n6 = new TreeNode<>(7);
        TreeNode<Integer> n7 = new TreeNode<>(9);
        TreeNode<Integer> n8 = new TreeNode<>(2);
        TreeNode<Integer> n9 = new TreeNode<>(6);
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
    TreeNode<Integer> pre =null;
    int retCount =0;
    int[] ret;

    public int[] findMode(TreeNode<Integer> root) {
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

    public void cal (TreeNode<Integer> root){
        if (root == null){
            return;
        }

        cal(root.left);

        if (pre !=null && pre.value == root.value){
            currCount ++;
        }else{
            currCount =1;
        }

        if ( currCount > max){
            max = currCount;
            retCount =1;
        } else if (currCount == max){
            if (ret != null)
                ret[retCount] = root.value;
            retCount++;
        }

        pre = root;

        cal(root.right);
    }

}
