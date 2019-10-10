package com.github.haliibobo.learn.java.leecode;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-06-17 14:31
 * @description describe what this class do
 */
public class ReverseInt {


    public static int reverse(int x) {
        int res=0;
        while ( x != 0){
            int newRes = res *10 + x%10;
            if((newRes- x%10)/10 != res){
                return 0;
            }
            res = newRes;
            x/=10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
    }

}
