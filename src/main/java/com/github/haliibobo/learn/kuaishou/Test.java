package com.github.haliibobo.learn.kuaishou;

public class Test {



    @org.junit.Test
    public void test(){
        System.out.println(SingleClass.getInstance() == SingleClass.getInstance());
        System.out.println(SingleEnum.INSTANCE);
    }
}
