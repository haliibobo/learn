package com.github.haliibobo.learn.java;

import java.util.Random;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-05-14 15:03
 * @description describe what this class do
 */
public class TestRandom {
    public static void main(String[] args){

        for (int i = 0; i <100000000 ; i++) {
            if(Double.compare(new Random().nextDouble(),0.9999999D)>0){
                System.out.println(i);
            }
        }
    }
}
