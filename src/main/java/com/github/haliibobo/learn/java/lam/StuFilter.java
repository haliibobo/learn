package com.github.haliibobo.learn.java.lam;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author lizibo
 * @date 2018/8/21 21:21
 * @description
 */
public class StuFilter {
    public static void main(String[] args){
        Student p1 = new Student(9, "22");
        Student p2 = new Student(2, "22");
        Student p3 = new Student(7, "22");
        List<Student> persons = Arrays.asList(p1,p2,p3);
        persons.stream().filter(distinctByKey(Student::getName)).forEach(System.out::println);
        List<Student> unique = persons.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new)
        );
        unique.forEach(System.out::println);

        Map<Student,String> m = new HashMap<>();
        System.out.println(p3.hashCode() >p2.hashCode());
        System.out.println(p1.hashCode() >p3.hashCode());
        m.put(p1,"1");
        m.put(p2,"2");
        m.put(p3,"3");
        // 按照hash值从大到小打印
        System.out.println(m.keySet().toString());
        Student student = m.keySet().stream().max(Comparator.comparing(
                Student::getName)).orElseGet(null);
        System.out.println(student.getId());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
