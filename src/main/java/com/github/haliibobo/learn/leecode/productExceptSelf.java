package com.github.haliibobo.learn.leecode;

import java.util.Arrays;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-04 10:21
 * @description describe what this class do
 */
public class productExceptSelf {


    @Test
    public void test(){
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }


    public int[] productExceptSelf(int[] nums) {

        int l = nums.length;
        int[] res =  new int[l];
        int[] left =  new int[l];
        left[0]=1;
        int[] right =  new int[l];
        right[l-1]=1;

        for (int i = 1; i < l; i++) {
            left[i] = left[i-1]*nums[i-1];
        }

        for (int i = l-2; i >= 0; i--) {
            right[i] = right[i+1]*nums[i+1];
        }
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        for (int i = 0; i < l; i++) {
            res[i] =left[i]*right[i];
        }

    return res;
    }
}
