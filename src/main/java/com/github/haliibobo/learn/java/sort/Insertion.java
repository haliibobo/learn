package com.github.haliibobo.learn.java.sort;

public class Insertion extends Sort {

    @Override
    public void  sort (Comparable [] a){
        System.out.print("[0]:");
        show(a);
        for (int i = 1; i < a.length; i++) {
            for ( int j =i;j >= 1 &&less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
            }
        }
    }
}
