package com.github.haliibobo.learn.java.collection.list.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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



    @org.junit.Test
    public void testRemove(){
        Integer[] a = {1,2,3,4,5};
        List<Integer> skuList;
        List<Integer> skuList2;
        skuList = Arrays.stream(a).filter(i -> i> 3).collect(Collectors.toList());

        skuList2 = Arrays.asList(a);
        Arrays.sort(a, (o1, o2) -> 0);
        skuList.removeIf(i-> i ==1);
        skuList2.removeIf(i-> i ==1);
    }

    @org.junit.Test
    public void test22(){
        final int COUNT_BITS = Integer.SIZE - 3;
         final int CAPACITY   = (1 << COUNT_BITS) - 1;
        System.out.println(CAPACITY);
         System.out.println(~CAPACITY);
    }
}
