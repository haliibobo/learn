package com.github.haliibobo.learn.encode;

import java.nio.charset.StandardCharsets;

public class Test {

    public static void main(String[] args) {
        String s = "Ð¡³Ç¹ÊÊÂ";
        String utf2Gbk2UtfString = new String(s.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        System.out.println("UTF-8转换成GBK再转成UTF-8："+utf2Gbk2UtfString);
    }
}
