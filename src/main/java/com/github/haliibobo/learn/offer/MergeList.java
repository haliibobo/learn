package com.github.haliibobo.learn.offer;

public class MergeList {

    /*
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     * 1、递归
     * 2、非递归
     */

    static class ListNode {

        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode listNode1 = new ListNode(3);
        head1.next = listNode1;
        listNode1.next = new ListNode(5);

        ListNode head2 = new ListNode(2);
        ListNode listNode3 = new ListNode(4);
        head2.next = listNode3;
        listNode3.next = new ListNode(6);

        ListNode result = Merge_1(head1,head2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    //递归
    private ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head;
        if (list1.val <= list2.val) {
            head = list1;
            head.next = Merge(list1.next, list2);
        } else {
            head = list2;
            head.next = Merge(list1, list2.next);
        }
        return head;
    }

    //非递归
    private static ListNode Merge_1(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }


        ListNode current = new ListNode(-1);//有序链表的最后一个结点
        ListNode head = new ListNode(-1);//保存合并以后的第一个结点
        boolean isFirst = true;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                current = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                current =list2;
                list2 = list2.next;
            }
            if (isFirst) {
                head = current;
                isFirst = false;
            }
        }
        if (list1 != null) {
            current.next = list1;
        }
        if (list2 != null) {
            current.next = list2;
        }
        return head;
    }
}
