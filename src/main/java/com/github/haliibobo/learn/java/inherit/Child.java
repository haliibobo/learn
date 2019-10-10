package com.github.haliibobo.learn.java.inherit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lizibo
 * @version 1.0
 * @date 2019-02-27 19:04
 * @description TODO
 */
public class Child extends Father {

    private static int a =99;

    private Map<String,Object> b = new HashMap<>();

    static {
        System.out.println(a);
        System.out.println("c:"+77777);
    }

    {
        a=5;
        b.put("c7777","c77777");
        System.out.println(b);
        System.out.println(77777);
    }





    public  Child(){

}


   // protected int n;
    public static void main(String[] args) {
        Child c = new Child();
       c.setN(8);
        List<String> ss  =c.getS();
        c.setS(ss.stream().filter(s -> s.equalsIgnoreCase("2")).collect(Collectors.toList()));
        //ss.removeIf(s -> s.equalsIgnoreCase("2"));
        c.print();
    }
}
