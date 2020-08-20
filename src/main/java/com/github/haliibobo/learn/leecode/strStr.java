package com.github.haliibobo.learn.leecode;

import org.junit.Test;

public class strStr {


    @Test
    public void test(){
        System.out.println(strStr("mississippi"
                ,"issip"));
    }



    public int strStr(String haystack, String needle) {

        if (needle.length() == 0) {
            return 0;
        }
        if (needle.length() > haystack.length()){
            return -1;
        }

        int start = 0;
        char[] haystacks = haystack.toCharArray();
        char[] needles = needle.toCharArray();

        for (int i =0 ;i < haystacks.length;i ++){
            if (needles[i-start] == haystacks[i]){
                if (i-start == needles.length-1) {
                    return start;
                }
            }else {
                start= start+1;
                i = start;
                if (start == haystacks.length){
                    return -1;
                }
            }
        }
        return  -1;
    }
}
