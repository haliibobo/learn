package com.github.haliibobo.learn.java.sort;

/**
 * 插入排序
 * 交换次数为倒置个数
 */
public class Insertion extends Sort {

    @Override
    public void  sort (Comparable [] a){
        super.sort(a);
        /**
         *多交换
         */
        /*for (int i = 1; i < a.length; i++) {
            for ( int j =i;j >= 1 &&less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
            }
        }*/
       //不交换，只移动
        for (int i = 1; i < a.length; i++) {
            int j =i;
            Comparable small =a[i];
            while ( j >0 && less(small,a[j-1])){
                // code 1 ********
                a[j]=a[j-1];
                // code 1 ********
                j--;
            }
            //2 1和2都可以
            // code 2 ********
           /* for (int k = i; k > j; k--) {
                a[k]=a[k-1];
            }*/
            // code 2 ********
            a[j] =small;
            exchNum++;
            System.out.print("[" + exchNum +"]:");
            show(a,i,j);
        }
    }
}
