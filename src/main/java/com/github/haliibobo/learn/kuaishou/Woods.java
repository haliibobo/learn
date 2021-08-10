package com.github.haliibobo.learn.kuaishou;

import java.util.Arrays;

public class Woods {

    public static void main(String[] args) {
        int[] a = {3,7,2,3,6,9,3};
        quickSort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
        int m = 11;
        int max =1;
        int lo =1;
        int hi = 2;
        while (lo !=hi) {
            hi = cal (a,m,max);
            if (max < hi) {
                lo= max;
            }else {
                hi = lo + (hi -lo)/2;
            }
            max =hi;
        }
        System.out.println(max);
    }

    private static int cal(int[] a, int m, int max) {
        int tmp =0;
        for (int i = a.length - 1; i >= 0&& tmp < m; i--) {
            tmp += a[i]/max;
        }
        if (tmp >=m) {
           return  max*2;
        }
        return max;
    }

    public static void quickSort (int[] a,int lo,int hi){
        if (lo >= hi) {
            return;
        }
        int p = partion (a,lo,hi);
        quickSort(a,lo,p-1);
        quickSort(a,p+1,hi);
    }

    private static int partion(int[] a, int lo, int hi) {
        int pivot = a[lo];
        int left = lo;
        int right = hi;
        while (left < right) {
            while (left < right&& pivot < a[right])  {
                right --;
            }
            while (left < right&& pivot >= a[left])  {
                left ++;
            }
            if (left < right) {
                int tmp = a[left];
                a[left] = a[right];
                a[right] = tmp;
            }
        }
        int tmp = a[lo];
        a[lo] = a[left];
        a[left] = tmp;
        return left;
    }

}



