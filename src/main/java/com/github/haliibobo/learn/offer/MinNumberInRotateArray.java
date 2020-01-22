package com.github.haliibobo.learn.offer;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-01-22 16:51
 * @description describe what this class do
 */
public class MinNumberInRotateArray {
    @Test
    public void solution (){
        int[] array = {3,4,5,1,2};
        int [] a = {1, 1, 1, 1, 1};
        Assert.assertEquals(0,minNumberInRotateArray(a));
    }

    private int minNumberInRotateArray(int [] array) {

        if (array.length == 0){
            return 0;
        }
        if (array.length == 1) {
            return array[0];

        }
        for (int i = 1; i < array.length; i++) {
             if (array[i] < array[i-1]){
                 return array[i];
             }
        }
        return array[0];
    }

}
