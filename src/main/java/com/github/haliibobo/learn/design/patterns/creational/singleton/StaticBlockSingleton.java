package com.github.haliibobo.learn.design.patterns.creational.singleton;

/**
 * https://howtodoinjava.com/design-patterns/creational/singleton-design-pattern-in-java.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-24 10:34
 * @description 饿汉模式
 */
public class StaticBlockSingleton {

    private  static final StaticBlockSingleton instance;

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException("Uffff, i was not expecting this!", e);
        }
    }

    private StaticBlockSingleton(){

    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}
