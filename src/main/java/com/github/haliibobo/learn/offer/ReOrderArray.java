package com.github.haliibobo.learn.offer;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ReOrderArray {

    @Test
    public void solution (){
        //System.out.println(Arrays.asList(new Integer[] {1,2,3,4,5,6,7}));
        //System.out.println(Arrays.asList(new int[]{1, 2}));
       // System.out.println(Arrays.asList(new String[]{"1", "2"}));

        int [] array = {1,2,3,4,5,6,7};
        reOrderArray2(array);
        for (int value : array) {
            System.out.print(value);
        }
    }

    public void reOrderArray(int [] array) {
        int[] odd = new int[array.length];
        int[] even = new int[array.length];
        int oddSize = 0;
        int evenSize = 0;
        for (int v : array) {
            if (v % 2 == 1) {
                odd[oddSize++] = v;
            } else {
                even[evenSize++] = v;
            }
        }
        System.arraycopy(odd,0,array,0,oddSize);
        System.arraycopy(even,0,array,oddSize,evenSize);
    }

    public void reOrderArray2(int[] array) {
        if (array == null || array.length == 0 || array.length == 1) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            //奇数 奇数向前移动
            if (array[i] % 2 == 1) {
                int tmp = array[i];//奇数
                int j = i - 1;
                while (j >= 0 && array[j] % 2 == 0) {
                    //依次拷贝直到遇到第一个奇数
                    array[j + 1] = array[j];
                    j--;
                }
                //将tmp值放在遇到的第一个奇数后面
                array[j + 1] = tmp;
            }
        }
    }
}
