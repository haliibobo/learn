package com.github.haliibobo.learn.yuanfudao;

import java.util.Stack;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-09-27 14:15
 * @description 使用栈结构对栈进行排序
 */
public class V2 {


    @Test
    public void test(){

        Stack<Integer> o = new Stack<>();
        o.push(1);
        o.push(3);
        o.push(5);
        o.push(2);
        o.push(4);
        System.out.println(o);

        sort(o);
        System.out.print(o);

    }


    private Stack<Integer> sort( Stack<Integer> origin){
        origin.sort(Integer::compareTo);
        return origin;
    }

    private Stack<Integer> sort2( Stack<Integer> origin){
        origin.sort(Integer::compareTo);
        return origin;
    }

}
