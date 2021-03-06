package com.github.haliibobo.learn.java.collection.map;

import com.github.haliibobo.learn.java.lam.Student;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.apache.commons.collections4.MapUtils;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-08-01 22:27
 * @description describe what this class do
 */
public class Test {


    @org.junit.Test
    public void test (){
        Map<Long,Map<String,Object>> maps = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("1111",new Student(1,"1"));
        maps.put(1111L,map);

        Map<String, Object> newMap = (Map<String, Object>) MapUtils.getMap(maps, 1111L);

        Student stu =  (Student )MapUtils.getMap(newMap, "1111");
        System.out.println(stu);

        System.out.println(newMap.get("1111"));

        /*Map<String,Integer> old = new HashMap<>();
        old.put("aaa",111);
        old.put("ccc",333);
        old.put("bbb",222);
        old.put("dddd",null);
        System.out.println(old.containsKey("dddd"));
        System.out.println(old.containsKey("ffff"));*/
       /* System.out.println(old);
        List<Entry<String,Integer>> new1 = old.entrySet().stream().sorted(Comparator.comparingInt(Entry::getValue))
            .collect(Collectors.toList());
        Map<String,Integer> new2 = new LinkedHashMap<>();
        System.out.println(new1);
        new1.forEach(e -> new2.put(e.getKey(),e.getValue()));
        System.out.println((ArrayList & Serializable) new1);*/

    }
    public static <K, V extends Comparable<? super V>> Comparator<Map.Entry<K,V>> comparingByValue() {
        return (Comparator<Map.Entry<K, V>> & Serializable)
            (c1, c2) -> c1.getValue().compareTo(c2.getValue());
        //return Comparator.comparing(Entry::getValue);
    }
}
