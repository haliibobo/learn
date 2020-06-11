package com.github.haliibobo.learn.java.sort;

import org.junit.Test;

public class Insertion extends Sort {

    @Override
    public void  sort (Comparable [] a){
        /*for (int i = 1; i < a.length; i++) {
            for ( int j =i;j >= 1 &&less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
            }
        }*/
        super.sort(a);
        for (int i = 1; i < a.length; i++) {
            int j =i;
            while ( j >0 && less(a[i],a[j-1])){
                j--;
            }
            Comparable small =a[i];
            for (int k = i; k > j; k--) {
                a[k]=a[k-1];
            }
            a[j] =small;
            show(a,i,j);
        }
    }
}
