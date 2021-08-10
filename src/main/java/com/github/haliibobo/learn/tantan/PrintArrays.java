package com.github.haliibobo.learn.tantan;

import org.junit.Test;

import java.util.Arrays;

public class PrintArrays {
    @Test
    public void test(){
        int[][] a = {{1,2,3},{4,5},{6,7,8},{3,4,5,7}};
        printArrays(a);
    }

    private void printArrays (int[][] a){

        int colMax = a[0].length;
        int total = 0 ;
        for (int[] ints : a) {
            total += ints.length;
            colMax = Math.max(colMax, ints.length);
        }
        int cur = 0;
        int [] res = new int[total];
                System.out.println(colMax);
        for (int i = 0;i< colMax;i ++){
            for (int[] ints : a) {
                if (ints.length > i) {
                    res[cur++] = ints[i];
                   // System.out.print(ints[i] + " ");
                }
            }
        }
        System.out.println(Arrays.toString(res));
    }
}
