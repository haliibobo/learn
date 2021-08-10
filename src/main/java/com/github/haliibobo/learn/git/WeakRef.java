package com.github.haliibobo.learn.git;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakRef {


    public static void main(String[] args) throws InterruptedException {
        Map<K,Integer> m = new WeakHashMap<>();
        for (int i = 0; i <30000000 ; i++) {
            m.put(new K(), i);
        }
        /*System.out.println("m.size:" + m.size());
        List<Byte[]> list = new ArrayList<>();
        for (int i = 0; i < 7000; i++) {
            list.add(new Byte[1024]);
        }*/
        Thread.sleep(1000);
        System.out.println("m.size:" + m.size());
    }


    public static class K {
        private byte[] b = new byte[1024];
    }
}
