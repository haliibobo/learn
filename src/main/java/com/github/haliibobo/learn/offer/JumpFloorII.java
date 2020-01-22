package com.github.haliibobo.learn.offer;

import org.junit.Assert;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-01-22 19:01
 * @description describe what this class do
 */
public class JumpFloorII {


    public   int JumpFloorII(int n) {
        if (n  <=1) {
            return 1;
        }
        return  2*JumpFloorII(n-1);
    }
    @Test
    public void solution(){
        Assert.assertEquals(1,JumpFloorII(1));
        Assert.assertEquals(2,JumpFloorII(2));
        Assert.assertEquals(4,JumpFloorII(3));
    }
}
