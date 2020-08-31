package com.github.haliibobo.learn.concurrent.volatile_;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-31 15:47
 * @description describe what this class do
 */
public class Entity_2 {
    //使用volatile修饰共享资源i
    //类变量
    private final static int max = 10;
    private  volatile int  initValue = 0;
    public static int getMax() {
        return max;
    }
    public int getInitValue() {
        return initValue;
    }
    public void setInitValue(int initValue) {
        this.initValue = initValue;
    }
    private static class VolatileEntityHolder {
        private static Entity_2 instance = new Entity_2();
    }
    public static Entity_2 getInstance() {
        return VolatileEntityHolder.instance;
    }
}
