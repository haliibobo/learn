package com.github.haliibobo.learn.java.sort;

/**
 * 选择排序法-原地排序法
 * 思想：不断选择剩余元素中的最小者
 * 交换次数：N次，即数组大小
 * 运行时间和输入无关
 */
public class Selection extends Sort {
    @Override
    public void  sort (Comparable [] a){
        super.sort(a);
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min =i;
            for (int j=i+1;j < N;j++) {
                if(less(a[j] ,a[min])){
                    min = j;
                }
            }
            exch(a,i,min);
        }
    }
}
