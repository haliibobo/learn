package com.github.haliibobo.learn.leecode;

public class IsPalindrome {
    public static void main(String[] args) {

        String s  = "0P";
        System.out.println(isPalindrome(s));
    }

    public static boolean isPalindrome(String s) {

        if (s == null || s.length() <2) {
            return true;
        }
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            
        }
        int l = 0;
        int r = chars.length -1;

        while (l < r ) {
            if (!((chars[l] >=   65 &&  chars[l] <= 90) || (chars[l] >=   97 &&  chars[l] <= 122) ||
                    (chars[l] >=   48 &&  chars[l] <= 57))){
                l++;
                continue;
            }
            if (chars[l] >= 97) {
                chars[l] -= 32;
            }
            if (!((chars[r] >=   65 &&  chars[r] <= 90) || (chars[r] >=   97 &&  chars[r] <= 122)||
                    (chars[r] >=   48 &&  chars[r] <= 57))){
                r--;
                continue;
            }
            if (chars[r] >= 97) {
                chars[r] -= 32;
            }
            if (chars[l]  == chars[r] ) {
                l++;
                r--;
            }else {
                return false;
            }
        }
        return true;

    }
}
