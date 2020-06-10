package com.github.haliibobo.learn.java.lam;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * @Auther: lizibo
 * @Date: 2018/8/21 21:17
 * @Description:
 */
public class Student {

    private int id ;
    private String name;
    private int score;
    private String sub;

    public Student(int id,String name){
        this.id=id;
        this.name=name;
    }
    public Student(int id,String name,int score,String sub){
        this.id=id;
        this.name=name;
        this.score =score;
        this.sub =sub;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
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
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .add("name", name)
            .add("score", score)
            .add("sub", sub)
            .toString();
    }
}
