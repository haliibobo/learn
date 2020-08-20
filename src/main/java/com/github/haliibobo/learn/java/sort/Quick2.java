package com.github.haliibobo.learn.java.sort;

/**
 * 快速排序
 */
public class Quick2 extends Sort {

    @Override
    public void  sort (Comparable [] a){
        super.sort(a);
        sort(a,0,a.length-1);
    }

    /**
     *
     * @param a 待排序数组
     * @param lo 低位指针 a[lo] 是比较基准数
     * @param hi 高位指针
     */
    private void sort(Comparable[] a,int lo,int hi){
       if (lo >= hi) return;
       int i =lo; int j =hi +1;
       while (true){
           //从左到右扫描找大的
           while (less(a[++i],a[lo])) if (i>=hi) break;
           //从右到左扫描找小的
           while (less(a[lo],a[--j]));
           if (i >=j) break;
           exch(a,i,j);
       }
       exch(a,lo,j);
       sort(a,lo,j-1);
       sort(a,j+1,hi);
    }
}
