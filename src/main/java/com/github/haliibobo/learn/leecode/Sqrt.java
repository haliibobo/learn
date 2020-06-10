package com.github.haliibobo.learn.leecode;

import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-10 14:31
 * @description f(x) = x^3 + 10x -20
 * f`(x) = 3x^2 +10
 *
 * f(x) = x^2=6
 * f`(x)=2x
 */
public class Sqrt {

      final double E = 0.0000000000000001;
      double pre;
    @Test
    public void test(){
        System.out.println(Math.pow(3,1/2.0));

        System.out.println(sqrt(3));
    }

    public double sqrt(int x){
        double x1 = x;
        while (Math.abs(pre-x1) >  E ){
            pre =x1;
            x1 = x1 -(x1 -x/x1)/2;
        }
        return x1;
    }


}
