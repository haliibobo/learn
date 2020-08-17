package com.github.haliibobo.learn.java.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-17 16:21
 * @description describe what this class do
 */
public class Test {

    volatile int v = 100;
    volatile String s = "hello";
    AtomicInteger atomicInteger = new AtomicInteger(12);
    AtomicIntegerFieldUpdater<Test> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Test.class,"v");


    /**
     * AtomicInteger object
     *
     * valueoffset value 偏移量
     *
     * 使用unsafe.compareAndSwapInt    cas 进行 do while 循环
     *
     *
     * int pre
     * do{
     * 	pre = unsafe.getVolatileInt(object,valueoffset);
     * }while(! unsafe.compareAndSwapInt(object,valueoffset,pre,pre + 1))
     */
    @org.junit.Test
    public void testAtomicInteger(){
       System.out.println(atomicInteger.getAndIncrement()); ;
    }

    /**
     * compareAndSwapInt
     */
    @org.junit.Test
    public void testAtomicIntegerFieldUpdater(){
        atomicIntegerFieldUpdater.compareAndSet(this,100,120);
        atomicIntegerFieldUpdater.compareAndSet(this,100,130);
        System.out.println(this.v);
    }

    /**
     * unsafe.getAndSetObject(this, valueOffset, newValue)
     */
    @org.junit.Test
    public void testAtomicReference(){
        AtomicReference<String> atomicReference = new AtomicReference<>("init");
        atomicReference.getAndSet("edit1");
        atomicReference.compareAndSet("edit1","init");
        System.out.println(atomicReference.get());
    }

    /**
     * 解决ABA问题，通过设置版本号
     * 内部维护了一个 Pair<T> {
     *     final T reference;
     *     final int stamp;
     * }
     */
    @org.junit.Test
    public void testAtomicStampedReference(){
        int[] version = new int[1];
        AtomicStampedReference<String> atomicReference = new AtomicStampedReference<>("init",1);
        String s = atomicReference.get(version);
        System.out.println(s);
        atomicReference.compareAndSet(s,"edit1",version[0],version[0]+1);
        s= atomicReference.get(version);
        System.out.println(s);
        atomicReference.compareAndSet(s,"edit2",version[0],version[0]+1);
        s= atomicReference.get(version);
        System.out.println(s);
    }

    @org.junit.Test
    /**
     * 并发访问long 改成分段访问 加和
     */
    public void testLongAdder(){
        LongAdder longAdder  = new LongAdder();
        longAdder.add(1);
        System.out.println(longAdder.sum());
    }



}
