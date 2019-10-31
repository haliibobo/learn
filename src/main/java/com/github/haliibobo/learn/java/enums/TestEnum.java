package com.github.haliibobo.learn.java.enums;

import java.util.Arrays;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-10-30 10:16
 * @description describe what this class do
 */
public class TestEnum {

    public static void main(String[] args) {
        System.out.println(Sex.FEMALE.value);
        System.out.println(Sex.FEMALE.name());
        System.out.println(Arrays.asList(Sex.values()));
        //System.out.println(Sex.valueOf("222"));

        for (Sex value : Sex.values()) {
            System.out.println(value);
        }
    }




public   enum Sex {
    MALE(0),
    FEMALE(1);
    int value;

    Sex(int i){
       this.value=i;
    }

    @Override
    public String toString() {
        return this.value +"";
    }
}

    public   enum Sex2 {
        MALE,
        FEMALE
    }
}
