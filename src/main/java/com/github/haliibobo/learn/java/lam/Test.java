package com.github.haliibobo.learn.java.lam;/*
package com.jd.java8.lam;


import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

*/
/**
 * @Auther: lizibo
 * @Date: 2018/10/25 22:19
 * @Description:
 *//*

public class Test {

    //predicate<T> 断言型接口:
    @Test
    public void test4(){
        List<String> list = Arrays.asList("Hello","Lambda","Go","java");
        list = filterStr(list, s->s.contains("o"));
        list.forEach(System.out::println);
    }
    //需求:将满足条件的字符串添加到集合中
    public List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> stringList = new ArrayList<>();
        for (String str : list) {
            if (predicate.test(str))
                stringList.add(str);
        }
        return stringList;
    }
    //-----------------------------------------------------------
    //Function<T,R>函数型接口
    @Test
    public void test3() {
        String str = strHandler("huangyichun", s -> s.toUpperCase());
        System.out.println(str);
    }
    //需求用于处理字符串
    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }
    //-------------------------------------------------
    //Supplier<T>供给型接口:
    @Test
    public void test2() {
        List<Integer> list = getNumList(10, () -> (int) (Math.random() * 100));
        list.forEach(System.out::println);
    }
    //需求：产生指定个整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(sup.get());
        }
        return list;
    }
    //-----------------------------------------------------------
    //Consumer<T> 消费型接口
    @Test
    public void test1() {
        happy(10000, m -> System.out.println(m));
    }
    public void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }

}
*/
