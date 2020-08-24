package com.github.haliibobo.learn.config;

import org.junit.Test;

public class BitCal {


    @Test
    public void Test(){
        System.out.println(0b10&0b10);
        System.out.println(0b10|0b10);
        System.out.println(0b10^0b10);
        System.out.println(0b0^0b10);
        System.out.println(0b11&0b10);
        System.out.println(0b11|0b10);
        System.out.println(0b11^0b10);

    }


    public int missingNumber(int[] nums) {

        int n = nums.length;
        int sum = n*(n +1)/2;

        int s = 0;
        for (int num : nums) {
            s += num;
        }

        return sum - s;

    }
}
