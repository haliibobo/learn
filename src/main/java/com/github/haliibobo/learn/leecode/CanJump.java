package com.github.haliibobo.learn.leecode;


/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-04-17 09:31
 * @description https://leetcode-cn.com/problems/jump-game/
 */
public class CanJump {


    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2,0,0}));
        System.out.println(canJump1(new int[]{2,0,0}));
    }

    public static boolean canJump(int[] nums){
        //累计能跳到的最远距离
        int k =0;

        for(int i=0;i<nums.length;i++){
            if(i>k) {
                return false;
            }
            k = Math.max(k,i+nums[i]);
        }
        return true;
    }

    public static boolean canJump1(int[] nums){
        //累计能跳到的最远距离
        int k =nums.length-1;
        for(int i=nums.length-1;i>0;i--){
            k = Math.min(k,i+nums[i]);
            if(i<k) {
                return false;
            }

        }
        return true;
    }
}
