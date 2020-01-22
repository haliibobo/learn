package com.github.haliibobo.learn.offer;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-01-22 14:19
 * @description describe what this class do
 */
public class ListFromTailToHead {

    ArrayList<Integer> l = new ArrayList<>();

    @Test
    public void solution (){
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        Assert.assertEquals("[3, 2, 1]",printListFromTailToHead(listNode1).toString());
        printListFromTailToHead2(listNode1);
        Assert.assertEquals("[3, 2, 1]",l.toString());
    }

    private ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        while (listNode !=null) {
            list.add(0,listNode.val);
            listNode =listNode.next;

        }
        return list;
    }

    private ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        if (listNode !=null) {
            printListFromTailToHead2(listNode.next);
            l.add(listNode.val);
        }
        return l;
    }

}
