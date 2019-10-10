package com.github.haliibobo.learn.java.leecode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-06-16 19:27
 * @description describe what this class do
 */
public class TwoLinkSum {

    private static void sum (int[] a,int[] b){
        String s1 = StringUtils.join(a,',').replaceAll(",","");
        String s2 = StringUtils.join(b,',').replaceAll(",","");
        int sum = Integer.sum(Integer.parseInt(s1),Integer.parseInt(s2));
        System.out.println(sum);
    }

    public static void main(String[] args) {
        sum(new int[]{1,2,3},new int[]{5,7,2});
    }

}
