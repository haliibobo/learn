package com.github.haliibobo.learn.offer;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-10-08 20:50
 * @description describe what this class do
 */
public class MergeListNode {




    //非递归
    private ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        //1-3-5-7
        //2-4-6-8
        ListNode head = null;
        ListNode tmp = new ListNode(-1);//有序链表的最后一个结点
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tmp.next = list1;
                tmp =list1;
                list1 = list1.next;
            } else {
                tmp.next =list2;
                tmp = list2;
                list2 = list2.next;
            }
            if (head == null) {
                head = tmp;
            }
        }
        if (list1 != null) {
            tmp.next = list1;
        }
        if (list2 != null) {
            tmp.next = list2;
        }
        return head;
    }

    private class ListNode {

        public ListNode next;
        int val;

        public ListNode(int val) {
            this.val =val;
        }
    }
}
