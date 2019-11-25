package com.github.haliibobo.learn.offer;

public class DoublePower {

    /*
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     * 保证base和exponent不同时为0
     * exponent需要区分正负
     */

    public double Power(double base, int exponent) {
        //0的多少次方都是0
        if (base == 0 && exponent != 0) {
            return 0.0;
        }
        //一个数字的0次方是1
        if (exponent == 0 && base != 0) {
            return 1.0;
        }
        //TODO 做错的地方 result初始化应为1 不为0
        double result =1.0;
        //exponent需要区分正负
        int temp = exponent;
        if (exponent < 0) {
            temp = -exponent;
        }
        while (temp > 0) {
            result *= base;
            temp--;
        }
        return exponent > 0 ? result : 1 / result;
    }
}
