package com.github.haliibobo.learn.java.lam;

import com.google.common.base.Objects;

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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equal(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name);
    }

    @Override
    public String toString() {
        return "{id:"+this.id+",name:"+this.name+"}";
    }
}
