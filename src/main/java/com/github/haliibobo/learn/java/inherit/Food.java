package com.github.haliibobo.learn.java.inherit;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-11 11:29
 * @description describe what this class do
 */
public class Food {
     String name;
     public void setName(String name){
         this.name = name;

     }
     public String getName(){
         if (name == null) {
             return this.getClass().getSimpleName();
         }
         return this.name;

     }

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }
}
