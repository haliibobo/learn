package com.github.haliibobo.learn.java.sort;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

public abstract class Sort {

private static int exchNum =0;
 //abstract 关键字不可与static 共同修饰
    public  abstract void  sort (Comparable [] a);
      boolean less (Comparable a,Comparable b) {
        return a.compareTo(b) < 0;
    }

      void exch (Comparable[] a ,int i,int j){
        if ( i == j){
            return;
        }
        exchNum ++;
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
        System.out.print("[" + exchNum +"]:");
        show(a);
    }

      void show (Comparable[] a ) {
        ImmutableList.copyOf(a).stream().map( c ->c + " ").forEach(System.out::print);
        System.out.println();
    }



      boolean isSorted (Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if(less(a[i],a[i-1])) {
                return false;
            }
        }
        return true;
    }
    @Test
    public void test() {
        Integer[]b = {79,48,6,5,4,3,2,1};
        this.sort(b);
        Assert.assertTrue (isSorted(b));
    }
}
