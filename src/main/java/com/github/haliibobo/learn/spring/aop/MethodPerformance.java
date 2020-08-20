package com.github.haliibobo.learn.spring.aop;

public class MethodPerformance {

    private long s;
    private long e;
    private String m;
    public MethodPerformance(String m){
        this.s = System.currentTimeMillis();
        this.m=m;
    }
    public void logPerformance(){
        this.e = System.currentTimeMillis();
        long elapse = e -s;
        System.out.println(this.m + " cost " + elapse +"ms");
    }
}
