package com.github.haliibobo.learn.design.patterns.creational.singleton;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-24 11:21
 * @description describe what this class do
 */
public class DemoSingleton {

    private static final long serialVersionUID = 1L;

    private DemoSingleton() {
        // private constructor
    }

    private static class DemoSingletonHolder {
        private static final DemoSingleton INSTANCE = new DemoSingleton();
    }

    public static DemoSingleton getInstance() {
        return DemoSingletonHolder.INSTANCE;
    }

    protected Object readResolve() {
        return getInstance();
    }

}
