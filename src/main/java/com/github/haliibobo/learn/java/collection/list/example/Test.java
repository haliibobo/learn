package com.github.haliibobo.learn.java.collection.list.example;

import java.util.LinkedList;

/**
 * @Auther: lizibo
 * @Date: 2018/11/26 18:18
 * @Description:
 */
public class Test {

    public static void main(String[] args){
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
//        queue.add(3);
//        queue.add(2);
//        queue.add(1);
//        queue.add(3);
       // for (int i=0;i<8;i++){
            System.out.println(queue.pollLast() + ", len: " + queue.size());
      //  }
    }


}
