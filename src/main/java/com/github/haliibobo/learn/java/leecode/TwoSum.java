package com.github.haliibobo.learn.java.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-06-16 19:27
 * @description https://leetcode-cn.com/problems/two-sum/
 */
public class TwoSum {

    private static void sum (int target,int[] ints){
        Map<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i <ints.length; i++) {
            if(hashMap.containsKey(target - ints[i])) {
                System.out.println(hashMap.get(target - ints[i]) + "," + i);
                return;
            }else {
                hashMap.put(ints[i],i);
            }
        }
    }

    public static void main(String[] args) {
        sum(6,new int[]{3,2,4});
    }

}
