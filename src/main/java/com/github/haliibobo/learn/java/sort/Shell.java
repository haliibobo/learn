package com.github.haliibobo.learn.java.sort;

/**
 * 是优化的插入排序
 */
public class Shell extends Sort {

    @Override
    public void  sort (Comparable [] a){
        super.sort(a);
        int h = 1;
        while (h<a.length/3){
            h = 3*h + 1 ;
        }
        while (h >0) {
            //交换
            /*for (int i = h; i < a.length; i++) {
                for ( int j =i;j>=h &&less(a[j],a[j-h]);j-=h){
                    exch(a,j,j-h);
                }
            }*/
            //不交换，只移动
            for (int i = h; i < a.length; i++) {
                int j =i;
                Comparable small =a[i];
                while ( j >h-1 && less(small,a[j-h])){
                    // code 1 ********
                    a[j]=a[j-h];
                    // code 1 ********
                    j -=h;
                }
                //2 1和2都可以
                // code 2 ********
               /*
               for (int k = i; k > j; k -= h) {
                    a[k]=a[k-h];
                }*/
                // code 2 ********
                a[j] =small;
                exchNum++;
                System.out.print("[" + exchNum +"]:");
                show(a,i,j);
            }
            h=h/3;
        }
    }
}
