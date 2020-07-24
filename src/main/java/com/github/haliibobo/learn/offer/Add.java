package com.github.haliibobo.learn.offer;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-07-23 13:22
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号
 */
public class Add {

    public int add(int num1,int num2) {

        while(num2 !=0){
            int temp =num1^num2;
            num2 = (num1&num2) <<1;
            num1 =temp;
        }
        return num1;
    }

}
