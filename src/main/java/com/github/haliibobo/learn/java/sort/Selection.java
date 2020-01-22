package com.github.haliibobo.learn.java.sort;

import org.junit.Assert;
import org.junit.Test;

public class Selection extends Sort {

    @Test
    public void test() {
        Integer[]a = {5,4,1,48,2,6,79,3};
        Selection sort = new Selection();
        sort.sort(a);
        Assert.assertTrue(isSorted(a));
    }

    @Override
    public void  sort (Comparable [] a){
        System.out.print("[0]:");
        Sort.show(a);
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
