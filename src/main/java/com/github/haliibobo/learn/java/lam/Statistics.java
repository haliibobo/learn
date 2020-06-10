package com.github.haliibobo.learn.java.lam;

import com.google.common.collect.ImmutableList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-08 10:27
 * @description describe what this class do
 */
public class Statistics {


    @Test
    public void TestCountSimple(){
        List<String> list = ImmutableList.of("a","a","b","c","g","0","0","0");
        Map<String,Long> map = list.stream().filter(Objects::nonNull).collect
            (Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(map);
    }
    @Test
    public void TestAvgSimple(){
        List<String> list = ImmutableList.of("a#1.23","a#6.99","b#2.4","c#3.66","g#2","0#0.69","0#6.45","0#6.21");
        Map<String,Double> map =
            list.stream().filter(Objects::nonNull).collect(
                Collectors.groupingBy(
                    v->v.split("#")[0], Collectors.averagingDouble(v->Double.parseDouble(v.split("#")[1]))
                )
            );
        System.out.println(map);
        List<Double> lists = ImmutableList.of(1.23,6.99,2.4,3.66,2.47,0.69,6.45,6.21);
        double avg = lists.stream().mapToDouble(s->s).average().orElse(0.00);
        System.out.println(avg);
    }
    @Test
    public void TestGatherSimple(){
        Student student11 = new Student(1,"1",60,"语文");
        Student student12 = new Student(1,"1",73,"数学");
        Student student13 = new Student(1,"1",56,"英语");
        Student student21 = new Student(2,"2",60,"语文");
        Student student22 = new Student(2,"2",79,"数学");
        Student student23 = new Student(2,"2",98,"英语");
        Student student31 = new Student(3,"3",45,"语文");
        Student student32 = new Student(3,"3",87,"数学");
        Student student33 = new Student(3,"3",66,"英语");

        List<Student> list = ImmutableList.of(student11,student12,student13,
            student21,student22,student23,
            student31,student32,student33);

            Map<Integer, List<String>> gather = list.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Student::getId,
                    Collectors.mapping(Student::getSub, Collectors.toList())
                ));
        Map<Integer,Integer> sum =list.stream().filter(Objects::nonNull).collect(
            Collectors.groupingBy(Student::getId,Collectors.summingInt(Student::getScore)));
        Map<Integer,Double> avg =list.stream().filter(Objects::nonNull).collect(
            Collectors.groupingBy(Student::getId,Collectors.averagingDouble(Student::getScore)));
        Map<Integer, Student> maxPerStu = list.stream().filter(Objects::nonNull).collect(
            Collectors.groupingBy(Student::getId,
                Collectors.maxBy(Comparator.comparing(Student::getScore)))).entrySet().stream().collect(Collectors.toMap(
            Entry::getKey,e->e.getValue().get(),(s1,s2) -> s1
        ));
        Map<String, Student> maxPerSub = list.stream().filter(Objects::nonNull).collect(
            Collectors.groupingBy(Student::getSub,
                Collectors.maxBy(Comparator.comparing(Student::getScore)))).entrySet().stream().collect(Collectors.toMap(
            Entry::getKey,e->e.getValue().get(),(s1,s2) -> s1
        ));
        System.out.println(gather);
        System.out.println(sum);
        System.out.println(avg);
        System.out.println(maxPerStu);
        System.out.println(maxPerSub);
    }

}
