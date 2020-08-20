package com.github.haliibobo.learn.kuaishou;

public class SingleClass {

    private static  volatile SingleClass instance;

    private SingleClass (){

    }

    public void echo(String s) {
        System.out.println(s);

    }

    public static SingleClass getInstance(){
        if (instance == null){
            synchronized (SingleClass.class){
                if (instance == null) {
                    instance = new SingleClass();
                }
            }
        }
    return instance;
    }
}
