package com.github.haliibobo.learn.offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class PrintStack {

    /*
     * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
     * 面试的时候遇到这样的题可以跟面试官交流，可不可以改变链表的结构，
     * 如果可以的话可以用改变链表指针指向的方式去做还可以问可不可以使用辅助空间。
     * 这样的交流不但可以进一步弄清楚面试官的意思，还可以给面试官留下积极思考，主动交流的印象。
     */
    static class ListNode {

        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        PrintStack s = new PrintStack();
        ListNode ln4 = null;
        System.out.println(s.printListFromTailToHead(ln4));
        ListNode ln1 = new ListNode(1);
        ListNode ln2 = new ListNode(2);
        ln1.next = ln2;
        ListNode ln3 = new ListNode(3);
        ln2.next = ln3;
        System.out.println(s.printListFromTailToHead(ln1));
    }

    /*头插法
    * 头插的实现使用了System.arraycopy()方法
    * 每一次头插都相当于将原来的元素向后移动，如果数据的规模很大的话，效率就会降低。
    */
    public List<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode == null) {
            return null;
        }

        List<Integer> list = new ArrayList<>();
        //TODO 循环条件判断错误
        while (listNode!= null) {
            list.add(0, listNode.val);
            listNode = listNode.next;
        }
        return list;
    }

    /*借助栈 自己想到的方法
     *先将数据入栈然后依次出栈存入list
     */
    private List<Integer> printListFromTailToHead_1(ListNode listNode) {
        if (listNode == null) {
            return null;
        }

        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (listNode!= null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.empty()){
            //TODO 出栈方法错误
            //TODO peek()函数返回栈顶的元素，但不弹出该栈顶元素。
            //TODO pop()函数返回栈顶的元素，并且将该栈顶元素出栈。
            list.add(stack.pop());
        }
        return list;
    }

    /*
     *借助collections reverse函数
     */
    public List<Integer> printListFromTailToHead_2(ListNode listNode) {
        if (listNode == null) {
            return null;
        }

        List<Integer> list = new ArrayList<>();
        while (listNode!= null) {
            list.add(listNode.val);
            listNode = listNode.next;
        }
        Collections.reverse(list); //性能接近线性 时间复杂度O(n)
        return list;
    }

    //借助递归
    /*public class Solution{
        ArrayList<Integer> arrayList=new ArrayList<Integer>();
        public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
            if(listNode!=null){
                this.printListFromTailToHead(listNode.next);
                arrayList.add(listNode.val);
            }
            return arrayList;
        }
    }*/
}
