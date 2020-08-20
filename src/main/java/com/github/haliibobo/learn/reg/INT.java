package com.github.haliibobo.learn.reg;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class INT {


    @Test
    public void test(){
        for (int i = 0; i < 10 ; ++i) {
            System.out.print(i + " " );
            System.out.println(i + " " );
        }

        //System.out.print(myAtoi("2147483648"));
    }


    public int myAtoi(String str) {
        if (str == null){
            return 0;
        }
        String s = str.trim();

        if (s.length() == 0) {
            return 0;
        }

        String regex = "^[-+]?[0-9]\\d*";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(s);
        if (matcher.find()) {
            String num = matcher.group();
            boolean neg = num.charAt(0) == '-';
            num = num.replaceFirst("^[+-]?0*","");
            char[] chars = num.toCharArray();
            int min = neg?0x80000000: 0 - 0x7fffffff;
            long res = 0;
            for (char c : chars){

                if (res<min ) {
                    return neg?min:-min;
                }
                res = res*10;
                res = res - (c-'0');
            }
            if (res<min ) {
                return neg?min:-min;
            }
            return (int) (neg ?res:-res);
        }else {
            return 0;
        }
    }

}
