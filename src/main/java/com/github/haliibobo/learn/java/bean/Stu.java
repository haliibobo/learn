package com.github.haliibobo.learn.java.bean;

public class Stu {
     public String name ;

     public Stu (String name) {
         this.name = name;
     }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
// TODO Auto-generated method stub
        Stu s1 = new Stu("小张");
        Stu s2 = new Stu("小李");
        swap(s1, s2);
        System.out.println("s1:" + s1.getName());
        System.out.println("s2:" + s2.getName());
    }

    public static void swap(Stu x, Stu y) {
        Stu temp = x;
        x = y;
        y = temp;
        System.out.println("x:" + x.getName());
        System.out.println("y:" + y.getName());
    }
}
