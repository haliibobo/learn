package com.github.haliibobo.learn.leecode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * 返回 true.
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * 返回 false.
 * 示例 3：
 * s = "cbg", t = "ahbgdc"
 * 返回 false.
 * 后续挑战 :
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-01 16:21
 * @description https://leetcode-cn.com/problems/is-subsequence/
 */
public class IsSubsequence {
    @Test

    public void test(){
        String t ="ahbgdc";

       Assert.assertTrue(isSubsequence("abc",t));
       Assert.assertFalse(isSubsequence("axc",t));
       Assert.assertFalse(isSubsequence("cbg",t));
       Assert.assertFalse(isSubsequence("aaaaaa","ddaaaa"));
        Assert.assertFalse(isSubsequence("aaaaaa","aa"));
    }

    public boolean isSubsequence(String s, String t) {

        char[] sc = s.toCharArray();
        if (sc.length == 0){
            return true;
        }
        char[] tc = t.toCharArray();
        boolean res =false;
        int next=0;
        for (int i = 0; i < sc.length; i++) {
            char c = sc[i];
            res = false;
            for (int j = next; j < tc.length; j++) {
                if (j == tc.length - 1) {
                    if( i !=sc.length-1){
                        return false;
                    }
                }
                if (c == tc[j]) {
                    next = j+1;
                    res = true;
                    break;
                }
            }
        }
    return res;
    }

}
