package com.github.haliibobo.learn.java.lam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author lizibo
 * @version 1.0
 * @date 2019-01-21 22:13
 * @description TODO
 */
public class ForEach {

    public static void main(String[] args){

        ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(1,2,3,4,5));

        Consumer<Integer> action = System.out::println;

        numberList.stream().filter(n -> n%2  == 0).forEach( action );
        numberList.stream().filter(n -> n%2  == 0).forEach( System.out::println );
        numberList.stream().filter(n -> n%2  == 0).forEach( i ->System.out.println(i) );

        Map<String,Integer>  map = new HashMap<>();
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        map.entrySet().forEach(System.out::println);
        map.keySet().forEach(System.out::println);
        map.values().forEach(System.out::println);
    }

}
