package com.github.haliibobo.learn.java.inte.consumer;

/**
 * @Auther: lizibo
 * @Date: 2018/10/25 22:07
 * @Description:
 */
@FunctionalInterface
public interface InterfaceEx {

    void test();
    static void test1(){};
    default void test2(){};
    default void test3(){};

}
