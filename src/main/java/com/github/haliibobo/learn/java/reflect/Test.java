package com.github.haliibobo.learn.java.reflect;

import java.lang.reflect.AnnotatedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-11-29 18:15
 * @description describe what this class do
 */
public class Test {

    public static void main(String[] args) {
        Map<String,String> m = new HashMap<>();
        Class<?> c = m.getClass();
        System.out.println(m.getClass());
        Arrays.asList(c.getAnnotatedInterfaces()).forEach(AnnotatedType::getType);
        c.getGenericInterfaces();
    }

}
