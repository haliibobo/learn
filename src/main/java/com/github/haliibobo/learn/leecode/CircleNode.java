package com.github.haliibobo.learn.leecode;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-13 18:49
 * @description describe what this class do
 */
public class CircleNode {


    public ListNode  check (ListNode node1,ListNode node2){
        if (node1 == null || node2 == null){
            return null;

        }
        ListNode cur1 = node1;

        ListNode cur2 = node2;

        while (cur1!=cur2){

            cur1= cur1.next;
            if (cur1 == null) {
                cur1 = node2;
            }
            cur2 = cur2.next;
            if (cur2 == null){
                cur2 = node1;
            }
            if (cur1 == cur2){
                return cur1;
            }

        }

        return   null;
    }

}
