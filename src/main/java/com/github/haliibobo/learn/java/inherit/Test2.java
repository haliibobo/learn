package com.github.haliibobo.learn.java.inherit;

import java.util.ArrayList;
import java.util.List;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-11 11:39
 * @description 上界下界是针对继承链的
 *                  food
 *                /        \
 *           fruit          meat
 *           /     \        /   \
 *         apple   banana  beef  pork
 *         /    \
 *   redApple   greenApple
 *
 * 频繁往外读取内容的，适合用上界Extends。
 * 经常往里插入的，适合用下界Super
 */
public class Test2 {



    public void test(){
        Apple apple = new Apple();
        apple.setId(1);
    }
}
