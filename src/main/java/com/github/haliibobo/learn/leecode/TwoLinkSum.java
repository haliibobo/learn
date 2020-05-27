package com.github.haliibobo.learn.leecode;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-06-16 19:27
 * @description describe what this class do
 */
public class TwoLinkSum {
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
         int carry =0;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null?0:l1.val;
            int v2 = l2 == null?0:l2.val;
            int sum = v1 + v2 + carry;
            carry = sum/10;
            ListNode sumNode = new ListNode(sum % 10);
            cursor.next = sumNode;
            cursor = sumNode;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        if (carry > 0) {
            cursor.next = new ListNode(carry);
        }
        return root.next;
    }

    public static void main(String[] args) {

        ListNode new1 = new ListNode(123);
        ListNode new2 = new ListNode(877);
        System.out.println(new1);
        System.out.println(new2);
        System.out.println(addTwoNumbers(new1,new2));
    }

}
