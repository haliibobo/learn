package com.github.haliibobo.learn.offer;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrToInt {


    public static void main(String[] args) {
        //System.out.println(strToInt("-2222"));
        System.out.println("vai".hashCode());
        System.out.println(Integer.toBinaryString("vai".hashCode()));


        System.out.println(Integer.toBinaryString(hash("vai")));

        System.out.println(Integer.toBinaryString(15 & hash("vai")));
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    public static int strToInt(String s) {

       /* Pattern pattern = Pattern.compile("[+-]?[0-9]*(\\.[0-9]+)?([eE][+-]?[0-9]+)?");
        Matcher matcher = pattern.matcher(String.valueOf(str));
        return matcher.matches();*/
        int result = 0;
        boolean negative = false;
        int i = 0, len = s.length();
        int limit = -Integer.MAX_VALUE;
        int multmin;
        int digit;
        if (len > 0) {
            char firstChar = s.charAt(0);
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+')
                    return 0;

                if (len == 1) // Cannot have lone "+" or "-"
                    return 0;
                i++;
            }
            multmin = limit / 10;
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = s.charAt(i++) - '0';
                if (digit < 0||digit > 9) {
                    return 0;
                }
                if (result < multmin) {
                    return 0;
                }
                result *= 10;
                if (result < limit + digit) {
                    return 0;
                }
                result -= digit;
            }
        } else {
            return 0;
        }
        return negative ? result : -result;
    }
}
