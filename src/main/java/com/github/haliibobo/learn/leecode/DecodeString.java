package com.github.haliibobo.learn.leecode;

import java.util.Stack;
import org.junit.Assert;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-05-28 11:41
 * @description https://leetcode-cn.com/problems/decode-string/
 */
public class DecodeString {

    public static void main(String[] args) {
        Stack<String> s = new Stack<>();
        String ss ="aaa";
        s.push(ss);
        ss="";
        System.out.println(s);
    }

    @Test
public void test(){

        String s = "3[z]2[2[y]]";
        String res = "zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef";

        Assert.assertEquals(res, decodeString(s));
}

    public static String decodeString(String s) {
        Stack<String> stack= new Stack<>();
        char[] chars = s.toCharArray();
        int i = 0;
        while (i< chars.length){
            //数字字符拼接成数字以后入栈 256[a]
            if(isDigit(chars[i])){
                int num = Integer.parseInt(String.valueOf(chars[i]));
                i++;
                while (isDigit(chars[i])){
                    num = num * 10 + Integer.parseInt(String.valueOf(chars[i++]));
                }
                stack.push(String.valueOf(num));
                System.out.println(num);
                //字母或左括号直接入栈
            } else if(isLetter(chars[i]) || chars[i] =='['){
                stack.push(String.valueOf(chars[i++]));
            } else {
                //遇到']'开始出栈
                String tmp = "";
                StringBuilder stringBuilder = new StringBuilder();
                while (!stack.peek().equals("[")){
                    tmp = stack.pop() + tmp;
                }
                System.out.println(tmp);
                //TODO 错误 不应该reverse
                //String tmp = stringBuilder.reverse().toString();

                stack.pop();
                int times = Integer.parseInt(stack.pop());
                for(int k=0;k< times;k++){
                    stringBuilder.append(tmp);
                }
                stack.push(stringBuilder.toString());
                i++;
            }
        }

        String result = "";
        while (!stack.isEmpty()){
            result = stack.pop() + result;
        }

        return result;
    }

    private static boolean isDigit(char c){
        return c >='0' && c <='9';
    }

    private static boolean isLetter(char c){
        return (c >='a' && c <='z') || (c >='A' && c <= 'Z');
    }

}

