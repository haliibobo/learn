package com.github.haliibobo.learn.design.patterns.creational.singleton;

/**
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-24 11:08
 */
public class BillPughSingleton {

    private BillPughSingleton(){

    }

    private static class LazyHolder{
        private final static BillPughSingleton instance = new BillPughSingleton();

    }

     public static BillPughSingleton getInstance() {
        return LazyHolder.instance;
    }

}
