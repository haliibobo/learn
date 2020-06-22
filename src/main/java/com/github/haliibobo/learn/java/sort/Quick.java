package com.github.haliibobo.learn.java.sort;

/**
 * 快速排序
 */
public class Quick extends Sort {

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
        // 如果高位指针小于等于低位指针，中止递归
        if(hi <=lo) return;
        //将数组拆分为 a[lo..i-1] a[i] a[i+1..hi]
        int i =lo,j=hi+1;
        //这一步是按照基准数a[lo]将 lo和hi的数组分成两个部分，不大于a[lo]，不小于a[lo],
        // 然后找到碰撞指针 j
        while (true){
            //扫描左右，检查扫描是否结束并交换元素
            //从左往右 找大与等于的元素
            while (less(a[++i],a[lo])) if(i == hi) break;
            //从右往左 找小于等于的元素
            while (less(a[lo],a[--j]));
            if(i >= j) break;
            exch(a,i,j);
        }
        //将基准数 lo 和碰撞指针j进行交换 左子数组最右侧元素a[j]
        exch(a,lo,j);
        //继续递归 前半部分
        sort(a,lo,j-1);
        //继续递归后半部分
        sort(a,j+1,hi);
    }
}
