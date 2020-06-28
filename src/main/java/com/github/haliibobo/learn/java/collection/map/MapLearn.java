package com.github.haliibobo.learn.java.collection.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-22 11:29
 *
 *
 * @description
 */
public class MapLearn {


    @Test
    public void hashMap(){
        Map<String,Integer> map = new HashMap<>();
        map.put("11",11);
        map.put("31",31);
        map.put("21",21);
        map.put("81",81);
        System.out.println(map.getClass().getSimpleName()+":"+ map);

    }

    @Test
    public void linkedHashMap(){
        Map<String,Integer> map = new LinkedHashMap<>();
        map.put("11",11);
        map.put("31",31);
        map.put("21",21);
        map.put("81",81);
        System.out.println(map.getClass().getSimpleName()+":"+ map);
        Collections.synchronizedMap(map);
        System.out.println(System.getProperties());
        System.out.println(System.getProperty("java.vm.name"));
        System.out.println(System.getProperty("java.vm.info"));
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void treeMap(){
        Map<String,Integer> map = new TreeMap<>();
        map.put("11",11);
        map.put("31",31);
        map.put("21",21);
        map.put("81",81);
        System.out.println(map.getClass().getSimpleName()+":"+ map);
    }

    @Test
    public void concurrentHashMap(){
        Map<String,Integer> map = new ConcurrentHashMap<>();
        map.put("11",11);
        map.put("31",31);
        map.put("21",21);
        map.put("81",81);
        System.out.println(map.getClass().getSimpleName()+":"+ map);

    }

}
