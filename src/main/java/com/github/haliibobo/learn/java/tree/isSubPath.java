package com.github.haliibobo.learn.java.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class isSubPath {


    @Test
    /**
     * [1,4,2,6,8]
     * [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
     */
    public void test(){
        ListNode  head1 = new ListNode(1);
        ListNode  head4 = new ListNode(4);
        ListNode  head2 = new ListNode(2);
        ListNode  head6 = new ListNode(6);
        ListNode  head8 = new ListNode(8);

        List<Integer> l1 = new ArrayList<>();

        head1.next = head4;
        head4.next = head2;
        head2.next =head6;
        head6.next = head8;
        TreeNode root1 =  new TreeNode(1);
        TreeNode root2 =  new TreeNode(4);
        TreeNode root3 =  new TreeNode(4);
        TreeNode root4 =  new TreeNode(2);
        TreeNode root5 =  new TreeNode(2);
        TreeNode root6 =  new TreeNode(1);
        TreeNode root7 =  new TreeNode(6);
        TreeNode root8 =  new TreeNode(8);
        TreeNode root9 =  new TreeNode(1);
        TreeNode root10 =  new TreeNode(3);
        root1.left =root2;
        root1.right = root3;
        root2.right = root4;
        root3.left = root5;
        root4.left = root6;

        root5.left =root7;
        root5.right =root8;
        root8.left = root9;
        root8.right = root10;

        HashMap<Long,String> m = new HashMap();
        m.put(1L,"1");
        System.out.print(m.get(1L));

       // System.out.print(isSubPath(head1,root1));



    }



        public boolean isSubPath(ListNode head, TreeNode root) {

            if (head == null || root == null) {
                return false;
            }

            return  isSubPath2(head,root) ||isSubPath2(head,root.left) ||isSubPath2(head,root.right);

        }

        boolean isSubPath2(ListNode head, TreeNode root){
            if (head == null) {
                return true;
            }
            if (root == null) {
                return false;
            }
            if (head.val != root.val) {
                return false;
            }
            return isSubPath2(head,root.left) || isSubPath2(head,root.right);

        }

}
