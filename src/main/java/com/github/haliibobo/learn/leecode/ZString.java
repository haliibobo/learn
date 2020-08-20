package com.github.haliibobo.learn.leecode;

import org.junit.Test;

public class ZString {


    @Test
    public void test(){
        System.out.println(convert("PAYPALISHIRING",4));
    }


    public String convert(String s, int numRows) {

        StringBuilder sb = new StringBuilder();

        for(int i = 1 ;i <= numRows;i++){
            int gap = 2*numRows - (2*i +1 );
            if (gap  == -1){
                gap = 2*numRows -3;
            }
            for (int j = i -1;j < s.length(); j = j + gap +1){
                sb.append(s.charAt(j));
                System.out.println("index:" +j + ", char:" +s.charAt(j) );
            }

        }

        return sb.toString();

    }
}
