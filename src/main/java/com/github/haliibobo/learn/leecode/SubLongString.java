package com.github.haliibobo.learn.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-06-17 19:06
 * @description describe what this class do
 */
public class SubLongString {

    public static void main(String[] args) {
        System.out.println(sub("abbcbf"));
    }


    public static int  sub(String s) {
        int res=0;
        List<Character> charSet = new ArrayList<>();
        int j=0;
        while(j < s.length()){
            if (!charSet.contains(s.charAt(j))){
                charSet.add(s.charAt(j));
                j++;
                res = Math.max(res,charSet.size());
            }else{
                charSet.remove(0);
            }
        }
        return res;

    }
}
