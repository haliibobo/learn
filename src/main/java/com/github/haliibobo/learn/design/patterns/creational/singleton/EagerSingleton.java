package com.github.haliibobo.learn.design.patterns.creational.singleton;

/**
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-24 10:34
 * @description 饿汉模式
 */
public class EagerSingleton {

    private  static final EagerSingleton  instance = new EagerSingleton();

    private EagerSingleton(){

    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
