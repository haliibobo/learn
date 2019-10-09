package com.github.haliibobo.learn.java;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-10-08 20:10
 * @description describe what this class do
 */
public class LearnString {

    String s;
    StringBuilder stringBuilder;
    StringBuffer stringBuffer;
    //TODO contact 和 + 的区别
    //TODO 为什么 int 和long 的包装类型不能强转

    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        String c = a +b +"c";
        System.out.println(c);

        Object integer = 266787889;
        System.out.println((double) integer);
        System.out.println((Double) integer);
    }

}
