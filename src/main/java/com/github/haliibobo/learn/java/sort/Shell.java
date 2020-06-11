package com.github.haliibobo.learn.java.sort;

import org.junit.Assert;
import org.junit.Test;

public class Shell extends Sort {

    @Test
    public void test() {
        Integer[]a = {5,4,1,48,2,6,79,3};
        Integer[]b = {79,48,6,5,4,3,2,1};
        Shell sort = new Shell();
        sort.sort(b);
        Assert.assertTrue(isSorted(b));
    }

    @Override
    public void  sort (Comparable [] a){
        System.out.print("[0]:");
        show(a);
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
