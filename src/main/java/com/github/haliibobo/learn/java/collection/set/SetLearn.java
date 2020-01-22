package com.github.haliibobo.learn.java.collection.set;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @Auther: lizibo
 * @Date: 2018/7/11 20:41
 * @Description:
 */
public class SetLearn {

    public static void main(String[] args){


        long a =283;
        System.out.println(a*(0.8D));

        Set<Long> set = new HashSet<>();
        set.add(10000001L);
        set.add(10000002L);
        set.add(10000003L);
        set.add(10000004L);
        set.add(10000005L);
        set.add(10000006L);
        set.add(10000007L);
        set.add(10000008L);
        set.add(10000009L);
        set.add(10000010L);
        set.add(10000011L);
        set.add(10000012L);
        System.out.println(set.toString());

        System.out.println((new Long(10000008).hashCode()) ^ (new Long(10000008).hashCode() >>> 16));
        System.out.println((new Long(10000009).hashCode()) ^ (new Long(10000009).hashCode() >>> 16));
        System.out.println((new Long(10000001).hashCode()) ^ (new Long(10000001).hashCode() >>> 16));
        /*Collection books = new HashSet<String>();
        books.add("不能在该死的时候去死，要在能死的时候去死");
        books.add("快来快来数一数 二四六七八");
        books.add("single dog single dog single all the way");
        books.add("你们把这场残酷的游戏玩得很好 那是你们的责任");
        books.add("对不起 上校 在战场上 你说了算 但是在我家 我说了算");

        System.out.println(callAll(books,ele ->ele.toString().contains("single")));*/


    }

    public static int callAll(Collection books, Predicate p){
        int total=0;
        for (Object obj:books) {
            if(p.test(obj)){
                total++;
            }
        }
        return total;
    }
}
