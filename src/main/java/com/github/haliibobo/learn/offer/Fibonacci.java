package com.github.haliibobo.learn.offer;

import org.junit.Assert;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-01-22 17:08
 * @description describe what this class do
 */
public class Fibonacci {

    public int Fibonacci(int n) {
        if (n <=1){
            return n;
        }
        return Fibonacci(n-1) +Fibonacci(n-2);
    }

    @Test
    public void solution(){
        Assert.assertEquals(13,Fibonacci(7));
    }

}
