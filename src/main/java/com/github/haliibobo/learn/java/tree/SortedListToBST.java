package com.github.haliibobo.learn.java.tree;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * say something.
 * [-10, -3, 0, 5, 9]
 * @author lizibo
 * @version 1.0
 * @date 2020-08-18 10:36
 * @description describe what this class do
 */
public class SortedListToBST {

    /**
     * Definition for singly-linked list.
     */
      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

/**
 * Definition for a binary tree node.
 */
 public class TreeNode {
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

  @Test
  public void test(){
     // [-10, -3, 0, 5, 9]
      ListNode l1 = new ListNode(-10);
      ListNode l2 = new ListNode(-3);
      ListNode l3 = new ListNode(0);
      ListNode l4 = new ListNode(5);
      ListNode l5 = new ListNode(9);
      l1.next =l2;
      l2.next = l3;
      l3.next = l4;
      l4.next = l5;
      print(sortedListToBST(l1));
  }


  public void print(TreeNode root){

     if (root == null){
         return;
     }
      print(root.left);
     System.out.print(root.val + " ");
     print(root.right);
  }


    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        return bulid(0,list.size()-1,list);



    }

    private TreeNode  bulid(int left,int right,List<Integer> list){

        if (left > right){
            return null;
        }

        int mid = left +(right -left)/2;
        TreeNode root = new TreeNode(list.get(mid));

        root.left = bulid( left,mid-1,list);

        root.right = bulid( mid+1,right,list);

        return root;


    }
}
