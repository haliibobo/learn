package com.github.haliibobo.learn.offer;

public class ListNodeKthToTail {

    //输入一个链表，输出该链表中倒数第k个结点
    //链表数据结构
    static class ListNode {

        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    //1 ->2 ->3 ->4 ->5 找到倒数第五个 因为head指向链表第一个结点 走k-1步到达第k个
    //TODO 设置两个指针，p2指针先走（k-1）步，然后再一起走，当p2为最后一个时，p1就为倒数第k个
    private static ListNode findKthToTail(ListNode head, int k) {
        if (head == null || k==0) {
            return null;
        }
        ListNode pre = head;
        //先走k-1步
        while (k > 1) {
            pre = pre.next;
            //链表的长度小于k 直接返回null
            if (pre == null) {
                return null;
            }
            k--;
        }
        ListNode next = head;
        //TODO 结束循环的条件是next是最后一个结点 最终pre指针指向最后一个结点
        while (pre.next != null) {
            pre = pre.next;
            next = next.next;
        }
        return next;
    }
}
