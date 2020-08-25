package com.github.haliibobo.learn.leecode;

import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-25 17:22
 * @description describe what this class do
 */
public class MoreThanHalf {


    @Test
    public void test(){
        int[] a = {0,1,2,1,3,1,1,3};
        int[] b = {0,1,2,1,3,1,1};
        System.out.print(moreThanHalf(a));
    }


    public int moreThanHalf(int [] a) {

        int count =1;

        int pre = a[0];
        for (int i =1; i<a.length;i++){
            if (a[i] != pre){
                count --;
            }else {
                count ++;
            }
            if (count <0){
                pre = a[i];
                count =1;
            }

        }
        int c = 0;
        for (int i : a) {
            if (i == pre) c++;
        }

        return c > a.length/2 ? pre:-1;
    }
}
