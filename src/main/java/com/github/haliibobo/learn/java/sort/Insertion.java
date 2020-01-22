package com.github.haliibobo.learn.java.sort;

import org.junit.Assert;
import org.junit.Test;

public class Insertion extends Sort {

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
        Sort.show(a);
        for (int i = 1; i < a.length; i++) {
            for ( int j =i;j >= 1 &&less(a[j],a[j-1]);j-= 1){
                exch(a,j,j-1);
            }
        }
    }
}
