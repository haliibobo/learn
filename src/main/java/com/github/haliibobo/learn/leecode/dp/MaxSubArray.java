package com.github.haliibobo.learn.leecode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-01 15:01
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class MaxSubArray {


    @Test

    public void test(){
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        Assert.assertEquals(6,maxSubArray(nums));
    }


    public int maxSubArray(int[] nums) {
        int max =nums[0];
        int[] maxs = new int[nums.length];
        maxs[0]=nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxs[i] = Math.max(maxs[i-1]+ nums[i],nums[i]);
            max = Math.max(max, maxs[i]);

        }
        return max;
    }

}
