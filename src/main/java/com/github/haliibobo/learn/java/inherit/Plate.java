package com.github.haliibobo.learn.java.inherit;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-11 11:35
 * @description describe what this class do
 */
public class Plate<T> {

    private T item;

    public Plate(){
    }

    public Plate(T t){
        item=t;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +"["+toString()+"]";
    }
}
