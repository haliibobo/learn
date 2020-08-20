package com.github.haliibobo.learn.spring.aop;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class CglibProxy  implements MethodInterceptor {
    //
    private Enhancer enhancer = new Enhancer() ;

    public <T> T getProxy(Class<T> clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术创建子类
        for (Constructor constructor :clazz.getConstructors()){}
        return (T) enhancer.create(new Class[]{String.class},new Object[]{"222"});

    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("current:" + this.getClass().getSimpleName());
        PerformanceMonitor.begin(o.getClass().getName()+"."+method.getName());
        //通过代理类调用父类中的方法
        Object result = proxy.invokeSuper(o,args);
        PerformanceMonitor.end();
       // ProxyFactory
        return result;
    }
}
