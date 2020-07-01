package com.github.haliibobo.learn.exception;

import com.github.haliibobo.learn.java.lam.Student;
import java.io.IOException;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-30 19:04
 * @description describe what this class do
 */
public class ExceptLearn {

//-XX:BiasedLockingStartupDelay=0

    @Test
    public void test() throws Throwable {


        Thread.sleep(10000);
        Student o = new Student(1,"1");
       // System.out.println(o.hashCode());
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());

           // System.out.println(ClassLayout.parseInstance(o).toPrintable());

        }

        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        e1();
        e2();
        e3();
        e4();
        e5();
        e6();
    }

    private void e1() throws Exception{

    }
    private void e2() throws NullPointerException{

    }
    private void e3() throws Throwable{

    }
    private void e4() throws Error{

    }
    private void e5() throws RuntimeException{

    }

    private void e6() throws IOException{

    }

}
