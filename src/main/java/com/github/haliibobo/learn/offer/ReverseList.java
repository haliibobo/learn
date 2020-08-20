package com.github.haliibobo.learn.offer;

import org.omg.CORBA.Object;

import java.util.ArrayList;
import java.util.List;

public class ReverseList {

    /*
     * 输入一个链表，反转链表后，输出新链表的表头。
     * 1、利用堆栈 空间复杂度n
     * 2、利用三个指针 一边遍历一边修改链表
     * 1->2->3->4->5
     * 1<-2<-3  4->5
     */
    private static ListNode pre;
    static class ListNode {

        int val;
        ListNode next = null;

        ListNode(){
            System.out.println("hahhaha");
        }

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        ListNode listNode1 = new ListNode(0);
       ListNode listNode2 = new ListNode(1);

        //ListNode listNode3 = new ListNode(2);
        //ListNode listNode4 = new ListNode(1);
        head.next = listNode1;
        listNode1.next = listNode2;
        //listNode2.next =listNode3;
        //listNode3.next = listNode4;
        ListNode tmp = head;
        while (tmp!=null){
            System.out.println(tmp.val);
            tmp=tmp.next;

        }
        System.out.println("bbbbbbbb");
          System.out.println(getDecimalValue(head));
          while (head!=null){
              System.out.println(head.val);
              head=head.next;

          }
    }



    public  static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        boolean res = true;
        //快慢指针，反转后面的指针
        ListNode slow =head;
        ListNode fast =head;
        ListNode pre = null;
        //保存 断开链接的前一个节点，以便恢复链表
        ListNode sav;
        //1.找到 中间指针
        while (fast !=null && fast.next !=null){
            pre =slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 前后释放链接
        if (pre != null)
            pre.next = null;
        sav =pre;
        //2.反转之后指针
        pre =null;
        ListNode cur = slow;
        //存储临时节点
        ListNode tmp;
        while(cur !=null) {
            tmp = cur.next;
            cur.next= pre;
            pre = cur;
            cur = tmp;
        }
        //3. 遍历 两个链表
        fast =head;
        cur =pre;
        while(cur !=null&&fast!=null) {
            if (res ){
                res = cur.val == fast.val;
            }
            cur = cur.next;
            fast = fast.next;
        }
        //4. 恢复链表
        cur =pre;
        pre =null;
        while(cur !=null) {
            tmp = cur.next;
            cur.next= pre;
            pre = cur;
            cur = tmp;
        }
        if (sav!=null)
        sav.next=pre;
        return res;
    }

    public static int getDecimalValue(ListNode head) {
        int sum =0;
        if (head == null){
            return sum;
        }
        while(head!=null&& (sum += sum +head.val)>-1){
            head =head.next;
        }
        return sum;
    }

}
