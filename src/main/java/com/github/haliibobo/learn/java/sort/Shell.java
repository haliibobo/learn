package com.github.haliibobo.learn.java.sort;

public class Shell extends Sort {

    @Override
    public void  sort (Comparable [] a){
        int h = 1;
        while (h<a.length/3){
            h = 3*h + 1 ;
        }
        while (h >0) {
            for (int i = h; i < a.length; i++) {
                for ( int j =i;j>=h &&less(a[j],a[j-h]);j-=h){
                    exch(a,j,j-h);
                }
            }
            h=h/3;
        }
    }
}
