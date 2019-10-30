package com.github.haliibobo.learn.java.leecode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-06-16 19:27
 * @description describe what this class do
 */
public class TwoLinkSum {
    /**
     * Definition for singly-linked list.
     */
     public static class ListNode {
     int val;
      ListNode next;
     ListNode(int x) { val = x; }
     }
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
        return root.next;
    }

    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(2);
        ListNode l13 = new ListNode(3);
        ListNode l14 = new ListNode(4);
        ListNode l21 = new ListNode(5);
        ListNode l22 = new ListNode(8);
        ListNode l23 = new ListNode(2);

        l11.next=l12;
        l12.next=l13;
        l13.next=l14;

        l21.next=l22;
        l22.next=l23;
        ListNode res =addTwoNumbers(l11,l21);
        while (res!=null) {
            System.out.print(res.val);
            res=res.next;
        }
    }

}
