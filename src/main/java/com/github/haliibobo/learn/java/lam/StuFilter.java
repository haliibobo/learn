package com.github.haliibobo.learn.java.lam;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Auther: lizibo
 * @Date: 2018/8/21 21:21
 * @Description:
 */
public class StuFilter {

    static String regexString ="((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";
    static  String s ="(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    public static void main(String[] args){
        Student p1 = new Student(1, "22");
        Student p2 = new Student(2, "23");
        Student p3 = new Student(3, "24");
        /*List<Student> persons = Arrays.asList(p1,p2,p3);
        persons.stream().filter(distinctByKey(p -> p.getId())).forEach(p -> System.out.println(p));
        *//*List<Student> unique = persons.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new)
        );
        unique.forEach(p -> System.out.println(p));*//*
        String IPString="http://192.168.172.5:9090".replaceAll(s,"$1");
        System.out.println(IPString);*/

        Map<Student,String> m = new HashMap<>();
        m.put(p1,"1");
        m.put(p2,"3");
        m.put(p3,"3");
        Student student = m.keySet().stream().max(Comparator.comparing(
            student1 -> student1.getName())).orElseGet(null);
        System.out.println(student.getId());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static List<String> extractString(String text,String regularExpression,String separator){
        String[] wordsArr = text.split(separator);
        List<String> extractList = new ArrayList<>();
        Pattern pattern = Pattern.compile(regularExpression);
        for (String s : wordsArr) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                extractList.add(s);
            }
        }
        return extractList;
    }
}
