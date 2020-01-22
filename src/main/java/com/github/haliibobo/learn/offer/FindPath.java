package com.github.haliibobo.learn.offer;

import com.github.haliibobo.learn.java.tree.TreeNode;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-11-04 20:04
 * @description describe what this class do
 */
public class FindPath {
    /*
     * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
     * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
     * (注意: 在返回值的list中，数组长度大的数组靠前)
     *      10
     *      / \
     *     5   12
     *     /\
     *     4 7
     */

    public static void main(String[] args){
        TreeNode<Integer> root= new TreeNode<>(10);
        TreeNode<Integer> node2 = new TreeNode<>(5);
        TreeNode<Integer> node3 = new TreeNode<>(12);
        TreeNode<Integer> node4 = new TreeNode<>(4);
        TreeNode<Integer> node5 = new TreeNode<>(7);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        System.out.println(FindPath(root,22));
    }

    private static List<List<Integer>> FindPath(TreeNode root, int target){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        ArrayList<Integer> aPath = new ArrayList<>();
        FindAPath(result, aPath, root, target);
        result.sort((o1, o2) -> o2.size() - o1.size());
        return result;
    }

    private static void FindAPath(List<List<Integer>> result, List<Integer>
        list, TreeNode<Integer> root, int target)  {

        list.add(root.value);

        if (root.left == null && root.right == null) {
            if(root.value == target){
                result.add(list);
            }
            return;
        }
        List<Integer> tmpList = new ArrayList<>(list);
        if(root.left !=null){
            FindAPath(result, list, root.left, target - root.value);
        }

        if(root.right !=null){

            FindAPath(result, tmpList, root.right, target - root.value);
        }
    }

}
