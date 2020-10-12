package com.github.haliibobo.learn;

import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-09-16 20:13
 * @description describe what this class do
 */
public class Single {

    class Node{
        int val ;
        Node next ;

        public Node(int val){
            this.val=val;
        }
    }

    @Test
    public void test2(){
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
       Node new_ =del(node1,9);
       while (new_!=null){
           System.out.print(new_.val + " ");
           new_=new_.next;
       }

    }


    public Node del(Node node,int k){

        if (k <=0){
            return node;
        }

        if (node == null){
            return null;
        }
        Node fast = node;
        Node slow = node;
        Node pre = null;

        while (fast !=null){
            fast = fast.next;
            k--;
            if (k <0 ){
                pre = slow;
                slow = slow.next;
            }
        }

        if (k > 0){
            return node;
        }

        if (pre!= null){
            pre.next = slow.next;
        }else{
            //删除头节点
            Node newH = node.next;
            node.next = null;
            return  newH;
        }
        return node;
    }



    private static  volatile Single single;
    //   cat xx.log | awk  '{$1 == x; print $2}'
    public Single()  {

    }

    public static Single getInstance()  {
        if (single == null){
            synchronized (Single.class){
                if (single== null){
                    single = new Single();
                }
            }
        }
    return single;
    }

    public static void main(String[] args)  {
        System.out.print(Single.getInstance()  == Single.getInstance());
    }

    public void test() throws IllegalAccessException {
    }
}
