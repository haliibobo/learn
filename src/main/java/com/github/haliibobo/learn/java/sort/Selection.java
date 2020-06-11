package com.github.haliibobo.learn.java.sort;

public class Selection extends Sort {

    @Override
    public void  sort (Comparable [] a){
        System.out.print("[0]:");
        show(a);
        for (int i = 0; i < a.length; i++) {
            int min =i;
            for (int j=i+1;j < a.length;j++) {
                if(less(a[j] ,a[min])){
                   min = j;
                }
            }
            exch(a,i,min);
        }
    }
}
