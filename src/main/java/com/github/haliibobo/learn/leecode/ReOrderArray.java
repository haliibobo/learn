package com.github.haliibobo.learn.leecode;

import java.util.Arrays;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-28 14:29
 * @description describe what this class do
 */
public class ReOrderArray {
    @Test
    public void reOrderArray(){
        int[] a = {5,8,7,6,9,13,2,1};
        reOrderArray1(a);
        System.out.println(Arrays.toString(a));
    }


    public void reOrderArray1(int[] a){
        if(a.length <=1){
            return;
        }
        int i=0;
        int j = a.length-1;
        while (true){
            while (a[i]%2 ==1) if(i++ >=j) return;
            while (a[j]%2 ==0) if(j--<=i) return;
            int tmp = a[i];
            a[i]=a[j];
            a[j]=tmp;
        }

    }

    public void reOrderArray2(int[] a){
        if(a.length <=1){
            return;
        }
        int i=0;
        int j = a.length-1;
        while (true){
            while (a[i]%2 ==1) if(i++ >=j) return;
            while (a[j]%2 ==0) if(j--<=i) return;
            int tmp = a[i];
            a[i]=a[j];
            a[j]=tmp;
        }

    }
//Long
}
