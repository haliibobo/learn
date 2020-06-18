package com.github.haliibobo.learn.java.inherit;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-11 11:29
 * @description describe what this class do
 */
public class Apple extends Fruit {

    private int id ;

    public Apple() {
    }

    public  synchronized Apple setId(int id) {
        this.id = id;
        return this;
    }
}
