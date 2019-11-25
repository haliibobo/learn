package com.github.haliibobo.learn.offer;

public class ReverseList {

    /*
     * 输入一个链表，反转链表后，输出新链表的表头。
     * 1、利用堆栈 空间复杂度n
     * 2、利用三个指针 一边遍历一边修改链表
     * 1->2->3->4->5
     * 1<-2<-3  4->5
     */
    static class ListNode {

        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        head.next = listNode1;
        ListNode listNode2 = new ListNode(3);
        listNode1.next = listNode2;
        ListNode tmp = head;
        while (tmp != null) {
            System.out.println(tmp.val);
            tmp = tmp.next;
        }
        ListNode result = reverseList(head);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    private static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next; //将head的下一个结点暂时保存起来
            head.next = pre; //使head的指向前一个结点
            pre = head; //pre指针后移
            head = next; //head指针后移
        }
        //循环结束，head为null pre指针指向链表的最后一个结点
        return pre;
    }
}
