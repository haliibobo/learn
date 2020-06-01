package com.github.haliibobo.learn.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1431.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-01 14:22
 * @description 给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。
 *
 * 对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有 最多 的糖果数目。
 * 链接：https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies
 */
public class KidsWithCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean>  res = new ArrayList<>();
        int max =0;
        for (int candy : candies) {
            if(candy > max){
                max = candy;
            }
        }
        for (int candy : candies) {
            res.add(candy + extraCandies >= max);
        }
        return res;
    }
}
