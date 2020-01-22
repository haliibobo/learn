package com.github.haliibobo.learn.java.sort;

import com.google.common.collect.ImmutableList;

public abstract class Sort {

private static int exchNum =0;
 //abstract 关键字不可与static 共同修饰
    public  abstract void  sort (Comparable [] a);
    public static boolean less (Comparable a,Comparable b) {
        return a.compareTo(b) < 0;
    }

    protected static void exch (Comparable[] a ,int i,int j){
        if ( i == j){
            return;
        }
        exchNum ++;
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
        System.out.print("[" + exchNum +"]:");
        show(a);
    }

    protected static void show (Comparable[] a ) {
        ImmutableList.copyOf(a).stream().map( c ->c + " ").forEach(System.out::print);
        System.out.println();
    }

    protected static boolean isSorted (Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if(less(a[i],a[i-1])) {
                return false;
            }
        }
        return true;
    }
}
