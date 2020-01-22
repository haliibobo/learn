package com.github.haliibobo.learn.java.leecode;

import java.util.HashSet;
import java.util.Set;

public class MergeString {

    public static void main(String[] args) {
        String s1 ="abc";
        String s2 ="bbca";
        combination(s1,s2);
    }

    private static int cal (int start ,int end){
        if (start == end) {
            return start;
        }
        return  start * cal(start -1,end);
    }

    private static void combination(String s1, String s2) {
        Set<String> set = new HashSet<>();
        String s,l;
        if (s1.length() <= s2.length()) {
            s=s1;
            l=s2;
        }else {
            s=s2;
            l=s1;
        }
        int all = s.length() + l.length();
        int min = s.length();
        for (int i = (1 << min) -1; i < (((1 << min) -1) << (all -min))+1; i++) {
            StringBuilder ss = new StringBuilder();
            if (count1(i) != min){
                continue;
            }
            int m=0,n=0;
            for (int j = 0; j < all; j++) {
                int tmp = 1 << j; // 由0到n右移位
                if ((tmp & i) != 0) { // 与运算，同为1时才会是1
                    ss.append(s.charAt(m++));
                }else{
                    ss.append(l.charAt(n++));
                }
            }
            set.add(ss.toString());
        }
        System.out.println("toal:" + cal(all,all-min +1)/cal(min,1));
        System.out.println(set);
    }

    private static int count1(int n) {
        int cout =0;
        while (n!=0){
            n = n &(n-1);
            cout++;
        }
        return cout;
    }
}
