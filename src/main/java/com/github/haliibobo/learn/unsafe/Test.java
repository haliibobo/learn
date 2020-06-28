package com.github.haliibobo.learn.unsafe;

import com.typesafe.config.Config;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {



    @org.junit.Test
    public void test () throws NoSuchFieldException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
       Unsafe unsafe = Unsafe.getUnsafe();
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
       // Unsafe unsafe = (Unsafe) f.get(null);

        A o1 = new A(6); // constructor
        // prints 1
        System.out.println( o1.a());


        B o2 = B.class.newInstance(); // reflection
        System.out.println( o2.b());
        o2.b(); // prints 1
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.remove( 1);
        for (int i = 0; i < copyOnWriteArrayList.size(); i++) {
            copyOnWriteArrayList.get(i);
        }


        //TODO 非静态内部类无法使用反射获取实例
        Test test = new Test();
        Class c = Class.forName("com.github.haliibobo.learn.unsafe.Test$A");
        Constructor constructor = c.getConstructor(Test.class);
        A o5 = (A) constructor.newInstance(test);
        System.out.println( o5.a());


        Class cc = Class.forName("com.github.haliibobo.learn.unsafe.Test$A");
        for (Constructor ccConstructor : cc.getConstructors()) {
            System.out.println(ccConstructor);
        }
        Constructor con = cc.getConstructor(Test.class,long.class);
        A o6 = (A) con.newInstance(test,88);
        System.out.println( o6.a());

        /*A o4 = A.class.newInstance(); // reflection
        System.out.println( o4.a());
        o4.a(); // prints 1*/

        A o3 = (A) unsafe.allocateInstance(A.class); // unsafe
        System.out.println( o3.a());
        o3.a(); // prints 0
    }

    public class A {
        private long a; // not initialized value
        //private String s;

        public A() {
            this.a = 1; // initialization
            //this.s ="s";
        }
        public A(long a) {
            this.a = a; // initialization
            //this.s ="s";
        }

        public long a() {
            return this.a;
        }
        /*private String s(){
            return this.s;
        }*/
    }
}
