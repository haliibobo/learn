package com.github.haliibobo.learn.offer;

import java.util.Arrays;

public class JumpFloor {

    public static void main(String[] args) {
        fib_3(6);
    }

    //递归形式：斐波那契数列:aN = a(N-1)+ a(N-2) N>=3
    private static int fab(int num) {
        if (num <= 0) {
            return -1;
        }
        if (num == 1 || num == 2) {
            return 1;
        }
        return fab(num - 1) + fab(num - 2);
    }

    //TODO 非递归形式：斐波那契数列
    private static int fab_2(int num) {
        if (num <= 0) {
            return -1;
        }
        if (num == 1 || num == 2) {
            return 1;
        }

        int f1 = 1;
        int f2 = 1;
        int f3 = 1;
        for (int i = 3; i <= num; i++) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }

    private static void fib_3(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n && i < 2; i++) {
            arr[i] = 1;
        }
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        System.out.println(Arrays.toString(arr));
    }

    /*
     *扩展：如果要上一个N级台阶的楼梯，每次只能走1格或者2格，那么一共有多少种走法呢？
     *由于一步最多走连两个台阶，因此要到达第N级台阶，有两种方案：
     *走到第N-1级台阶上，然后走1级台阶跨到最上方；
     *走到第N-2级台阶上，然后一步走两级台阶跨到最上方。
     *注意，从第N-2级台阶走1级到N-1级台阶这种情况已经计算在第一种情况中计算过了。
     *我们用a(N-1)和a(N-2)分别表示走到第N-1级和第N-2级台阶的方法数，那么走到第N级台阶的方法数就是：
     *aN= a(N-1)+ a(N-2)
     */
    private static int jumpFloor(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return jumpFloor(n - 2) + jumpFloor(n - 1);
    }

    /*
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
     * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     */
    private static int rectCover(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int f1 = 1;
        int f2 = 2;
        for (int i = 3; i <= n; i++) {
            f2 = f1 + f2;
            f1 = f2 - f1;
        }
        return f2;
    }
}
