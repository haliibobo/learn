package com.github.haliibobo.learn.tantan;

import org.junit.Test;

import java.util.Arrays;

public class FormateString {


    @Test
    public void test(){
        String s = "         i am a  good boy    ?    sorry you really    break     my  heart !  !  !      !";
        System.out.println(s.replaceAll("[ ]+"," "));
        char[] chars = s.toCharArray();
        boolean lastIsSpace = chars[0] == ' ';
        int index = chars.length-1;
        for (int i =1;i<chars.length;){
            if (lastIsSpace &&chars[i] == ' ' ){
                for (int j=i;j<index;j++){
                    chars[j] = chars[j+1];
                }
                chars[index--] = '\t';
            }else {
                lastIsSpace = chars[i++] == ' ';
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars) {
            if (c != '\t'){
                stringBuilder.append(c);

            }        }
        System.out.println(stringBuilder);
    }
}
