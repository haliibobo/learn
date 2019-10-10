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

        Object o = 266787889;
        Object d = 266787889D;
        Integer o2 = (Integer)o;
        Double d1 = (Double)d;
        Integer i = 266787889;

        System.out.println((double) i);
        System.out.println((double) o2);
        System.out.println((double) d1);
        //System.out.println((double) o);
        String s ="-04e67695be9c\u0001100007817140\u000112345\u000112347\u00018557\u00011000000904\u0001-100\u00011\u00011570618141\u00010\u0001app\u0001660000\u0001\u0001";
        s.trim();//TODO
        String[] array = s.split(String.valueOf((char) 0x01),2);
        String uuid =  array[0];

        String[] values = array[1].split(String.valueOf((char) 0x01),-1);
        System.out.println(values.length);

    }

}
