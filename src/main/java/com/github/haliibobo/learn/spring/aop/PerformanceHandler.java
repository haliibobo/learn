package com.github.haliibobo.learn.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerformanceHandler<T>  implements InvocationHandler {
    private T o ;

    public PerformanceHandler (T o){
        this.o =o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("current:" + this.getClass().getSimpleName());
        PerformanceMonitor.begin(o.getClass().getName()+"."+method.getName());
        Object result=  method.invoke(o,args);
        PerformanceMonitor.end();
        return result;
    }
}
