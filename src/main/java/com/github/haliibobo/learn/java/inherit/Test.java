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
public class Test {


    /**
     * 苹果 IS-A 水果
     * 装苹果的盘子 NOT-IS-A 装水果的盘子
     * 上界通配符 Plate<？ extends Fruit> 能放 包含自身和所有子类
     * https://stackoverflow.com/questions/2723397/what-is-pecs-producer-extends-consumer-super
     * https://www.zhihu.com/question/20400700
     * PECS is for list
     * 编译阶段检查，为了绝对安全，全部向上转型为T
     */
    @org.junit.Test
    public void testExtend(){
        List<Food> foodList = new ArrayList<>();
        foodList.add(new RedApple());
        foodList.add(new Beef());
        foodList.add(new Banana());
        List<? extends Food> foods = foodList;
        Food food=foods.get(foods.size()-1);
        foodList.add(new Banana());
        Food food_=foods.get(foods.size()-1);
        System.out.println(food);
        System.out.println(food_);
    }

    /**
     * 下界通配符 Plate<？ super Fruit> 能放 包含自身和所有超类.
     * 编译阶段检查，为了绝对安全，仅允许 add T 和子类
     */
    @org.junit.Test
    public void testSuper(){
        List<? super Food> foods = new ArrayList<>();
        foods.add(new RedApple());
        foods.add(new Beef());
        foods.add(new Banana());

    }
}
