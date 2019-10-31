package com.github.haliibobo.learn.java;

import com.github.haliibobo.learn.java.lam.Student;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-10-16 15:00
 * @description describe what this class do
 */
public class LockTest {

    private Map<Integer, List<Student>> map= new ConcurrentHashMap<>();
    {
        List<Student> l1 = new ArrayList<>();
        List<Student> l2 = new ArrayList<>();
        for (int i = 0; i <128 ; i++) {
            l1.add(new Student(i,"22222"));
            l2.add(new Student(i,"22222"));
        }


        map.put(0,l1);
        map.put(1,l2);
    }
    public void test (){
        int k = new Random().nextInt(2);
        List<Student> l = new ArrayList<>(map.get(k));
        if (l == null) {
            return;
        }
        synchronized (l) {
            Iterator<Student> it = l.iterator();
            int i=0;
            while (it.hasNext()) {
                Student student = it.next();
                /**更新或者删除，不添加*/
                if (student.getId() == new Random().nextInt(128)) {
                    student.setName(Thread.currentThread().getName());
                    /**更新*/
                    if (new Random().nextInt(2) ==1 ) {
                        l.set(i, student);
                    }else {
                        it.remove();//删除
                    }
                    break;//sku不会重复
                }

                i++;
            }
        }
        System.out.println(Thread.currentThread().getName()+":" +map);
    }

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        ExecutorService executorService = Executors.newFixedThreadPool(128);
        for (int i = 0; i < 128; i++) {
            executorService.submit(lockTest::test);
        }
        executorService.shutdown();
    }
}
