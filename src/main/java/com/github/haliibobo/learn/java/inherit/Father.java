package com.github.haliibobo.learn.java.inherit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lizibo
 * @version 1.0
 * @date 2019-02-27 19:00
 * @description TODO
 */
public class Father {

    private static int a =0;

    private Map<String,Object> b;

    static {
        System.out.println(a);
        System.out.println(22222);
    }

    {
        a=5;
        b.put("11","22");
        System.out.println(b);
        System.out.println(3333);
    }


    public Father(){
        a=6;
       // b = new HashMap<>();
        System.out.println("1111");
        b.put("11","11");
        System.out.println("Father:"+b);
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public List<String> getS() {
        return s;
    }

    public void setS(List<String> s) {
        this.s = s;
    }

    private int n =5;
    private List<String> s = Arrays.asList("1","2","3");

    public  void print (){
        System.out.println(this.getN());
        System.out.println(this.getClass().getSimpleName());
        System.out.println(this.getS());

    }}
