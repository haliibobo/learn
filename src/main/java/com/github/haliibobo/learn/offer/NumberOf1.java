package com.github.haliibobo.learn.offer;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-01-22 19:40
 *  正数的原码、反码、补码 是同一个，
 * 负数的原码是 对应正数原码的符号位变成1,反码就是原码除符号位外其余取反，补码就是反码+1
 * 计算机中 正数 原码，负数用补码
 */
public class NumberOf1 {
    public int NumberOf1(int n) {
        int cout =0;
        while (n!=0){
            n = n &(n-1);
            cout++;
        }
        return cout;
    }
}
