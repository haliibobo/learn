package com.github.haliibobo.learn.offer;

import org.junit.Test;

public class CutRope {


    @Test
    public void test(){
        System.out.println(cutRope(8));
        System.out.println(cutRope23(10));
    }



    private int cutRope23(int target){
        int res=1;
        while (target -3>1){
            target =target -3;
            res *= 3;
        }
        return res*target;
    }

    public int cutRope (int target) {
        if (target < 2) {
            return 0;
        }
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        return rope(target);
    }

    public int rope (int n) {
        int[] num = new int[n+1];
        num[0] = 0;
        num[1] = 1;
        num[2] = 2;
        num[3] = 3;
        num[4] = 4;
        if (n <= 4) {
            return n;
        }
        int max = 1;
        for (int i = 1; i <= n/2; i++) {
            if (num[i] == 0) {
                num[i] = rope(i);
            }
            if (num[n-i] == 0) {
                num[n-i] = rope(n-i);
            }
            max = Math.max(max, num[i] * num[n-i]);
        }
        return max;
    }

}
