package com.github.haliibobo.learn.java.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-11-22 21:00
 * @description describe what this class do
 */
public class SortTest {

    public static void main(String[] args) {
        int[] a = new int[1<<17];
        a[0] = 0;
        a[1<<17 -1] = 1<<18;

        for (int i = 1; i <1<<17 -1 ; i++) {
            a[i] = new Random().nextInt(1<<18);
        }
        Arrays.sort(a);
    }

}
