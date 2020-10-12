package com.github.haliibobo.learn.pinduoduo;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-09-23 10:27
 * @description describe what this class do
 */
public class Buy {


    @Test
    public void test(){
        int[] m = {1,2,5};
        int[] c = {3,2,1};
        System.out.println(buy(m,c));
    }



    /**
     *
     * @param m
     * @param c
     * @return
     */
    private Set<Integer> buy(int[] m, int[] c) {
        Set<Integer>  set=new HashSet<>();
        set.add(0);

        for (int i = 0; i <c.length ; i++) {
            int count = c[i];
            Set<Integer>  s=new HashSet<>();
            for (int j = 0; j <=count ; j++) {
                s.add(m[i]*j);
            }
            Set<Integer> collect = set.stream()
                .map(t -> s.stream().map(t1 -> t + t1).collect(Collectors.toSet())).flatMap(Collection::stream)
                .collect(
                    Collectors.toSet());
            set.addAll(collect);
            set.addAll(s);
        }
    return set;
    }

}
