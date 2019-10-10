package com.github.haliibobo.learn.java.collection.set;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.function.Predicate;

/**
 * @Auther: lizibo
 * @Date: 2018/7/11 20:41
 * @Description:
 */
public class SetLearn {

    public static void main(String[] args){
        Collection books = new HashSet<String>();
        books.add("不能在该死的时候去死，要在能死的时候去死");
        books.add("快来快来数一数 二四六七八");
        books.add("single dog single dog single all the way");
        books.add("你们把这场残酷的游戏玩得很好 那是你们的责任");
        books.add("对不起 上校 在战场上 你说了算 但是在我家 我说了算");

        System.out.println(callAll(books,ele ->ele.toString().contains("single")));


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
