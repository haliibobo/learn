package com.github.haliibobo.learn.java.sort;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public abstract class Sort {

    int exchNum =0;
    //abstract 关键字不可与static 共同修饰
    public  void  sort (Comparable [] a){
        System.out.print("[0]:");
        show(a,-1,-1);
    }
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
        show(a,i,j);
    }

    void show (Comparable[] a,int i,int j ) {
        List<? extends Comparable> list = ImmutableList.copyOf(a);
        for (int m = 0; m < list.size(); m++) {
            if (m == i || m ==j){
                System.out.print(" [" + list.get(m) +"]");
            }else{
                System.out.print(" " + list.get(m));
            }
        }
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
        Integer[]b = {7,3,1,5,2};
        this.sort(b);
        Assert.assertTrue (isSorted(b));
    }
}
