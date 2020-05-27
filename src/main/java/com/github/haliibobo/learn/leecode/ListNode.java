package com.github.haliibobo.learn.leecode;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-05-27 17:54
 * @description describe what this class do
 */
public class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
        val = x;
        // 拿个位数
        if (x/10 > 0){
            val =x%10;
            x /=10;
            next = new ListNode(x);
        }
    }
    private  static   ListNode generateListNode(int x){
        if(x >0 && x <9){
            return new ListNode(x);
        }
        ListNode head = new ListNode(x%10);
        head.next = generateListNode(x/10);
        return head;
    }

    @Override
    public String toString() {
        // StringBuilder s = new StringBuilder();
        //ListNode tmp = this;
        //StringBuilder 使用int 的话会被调用构造器
        StringBuilder s = new StringBuilder(val+"");
        ListNode tmp = next;
        while (tmp !=null){
            s.append(tmp.val);
            tmp=tmp.next;
        }
        return s.toString();
    }

    public static void main(String[] args) {
        ListNode.generateListNode(109);
       System.out.println(new ListNode(109));
    }
}
