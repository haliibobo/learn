package com.github.haliibobo.learn.leecode;

import java.util.Arrays;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-17 18:31
 * @description describe what this class do
 */
public class PlusOne {


    @Test
    public void test(){
        int[] o = {9,9,9,9};
        System.out.println(Arrays.toString(plusOne(o)));

    }

    public  int[] plusOne (int[] o){
        for (int i = o.length - 1; i >= 0; i--) {
            o[i] += 1;
            if(o[i] == 10){
                o[i] =0;
            }else{
                return o;
            }
        }
        int[] r = new int[o.length+1];
        r[0]=1;
        return r;
    }
}
