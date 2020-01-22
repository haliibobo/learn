package com.github.haliibobo.learn.offer;

import org.junit.Assert;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-01-22 19:37
 * @description describe what this class do
 */
public class RectCover {

    public int RectCover(int n) {
        if (n <=2){
            return n;
        }
        return RectCover(n-1) +RectCover(n-2);
    }

    @Test
    public void solution(){
        Assert.assertEquals(3,RectCover(3));
    }
}
