package com.github.haliibobo.learn.spring.aop;

import org.junit.Test;

import java.lang.reflect.Proxy;

public class Demo {

    @Test
    public void testAop(){
        final PlayService playService = new PlayServiceImpl("test");
        PerformanceHandler<PlayService> handler = new PerformanceHandler<>(playService);
        final PlayService proxyService = (PlayService)Proxy.newProxyInstance(playService.getClass().getClassLoader(),
                playService.getClass().getInterfaces(),handler);
       final PlayService cglibProxy = new CglibProxy().getProxy(PlayServiceImpl.class);
        new Thread(()->{
            try {
                playService.play("ok");
                cglibProxy.play("cglib");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                proxyService.play("proxy");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        while (Thread.activeCount()>2){
            Thread.yield();
        }
    }
}
