package com.github.haliibobo.learn.leecode;

import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-01 17:27
 * @description describe what this class do
 */
public class AToI {

    @Test
    public void test(){
        System.out.println(myAtoi("-91283472332"));
    }


    public int myAtoi(String str) {

        str = str.trim();

        char[] chars = str.toCharArray();

        int l = chars.length;
        int limit =  -Integer.MAX_VALUE;
        int digit=0;
        boolean negative = false;

        int res =0;

        int i =0;

        if(l > 0){

            char fc = chars[0];

            if (fc < '0'){
                if (fc == '-'){
                    negative =true;
                    limit =  Integer.MIN_VALUE;
                }else if (fc != '+'){
                    return 0;
                }
                if (l == 1) {
                    return 0;
                }
                i++;
            }

        }

        while (i< l && chars[i] >= '0' && chars[i] <= '9'){
            digit = chars[i++] - '0';
            if (res < limit / 10) {
                return negative? Integer.MIN_VALUE:Integer.MAX_VALUE;
            }
            res *=10;
            if (res < limit + digit) {
                return negative? Integer.MIN_VALUE:Integer.MAX_VALUE;
            }
            res -=digit;

        }

       return negative? res:-res;



    }

}
