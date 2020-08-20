package com.github.haliibobo.learn.spring.aop;

public class PerformanceMonitor {
    private static final ThreadLocal<MethodPerformance> record = new ThreadLocal<>();

    public static void begin(String method){
        System.out.println("begin monitor...");
        MethodPerformance m = new MethodPerformance(method);
        record.set(m);
    }
    public static void end(){
        System.out.println("end monitor...");
        record.get().logPerformance();
    }
}
