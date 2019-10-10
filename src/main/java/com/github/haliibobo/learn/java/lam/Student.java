package com.github.haliibobo.learn.java.lam;

/**
 * @Auther: lizibo
 * @Date: 2018/8/21 21:17
 * @Description:
 */
public class Student {

    private int id ;
    private String name;

    public Student(int id,String name){
        this.id=id;
        this.name=name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "{id:"+this.id+",name:"+this.name+"}";
    }
}
