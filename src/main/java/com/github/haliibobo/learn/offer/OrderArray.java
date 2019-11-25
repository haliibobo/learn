package com.github.haliibobo.learn.offer;

public class OrderArray {

    /*
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序。
     * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分
     * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     * 偶数相对位置不变 排序算法具有稳定性
     * 利用插入排序的思想 O(n方)
     * 1234567 1224567 1124567 1324567
     */

    public void reOrderArray(int[] array) {
        if (array == null || array.length == 0 || array.length == 1) {
            return;
        }

        for (int i = 1; i < array.length; i++) {

            //奇数 奇数向前移动
            if (array[i] % 2 != 0) {
                int tmp = array[i];//奇数
                int j = i - 1;
                while (j >= 0 && array[j] % 2 == 0) {
                    //依次拷贝直到遇到第一个奇数
                    array[j + 1] = array[j];
                }
                //将tmp值放在遇到的第一个奇数后面
                array[j + 1] = tmp;
            }
        }
    }
}
