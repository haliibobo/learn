package com.github.haliibobo.learn.leecode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 无序数组查找中位数  时间复杂度 约是O（logn)
 */
public class MedianFinder {

    private PriorityQueue<Integer> low;
    private PriorityQueue<Integer> high;

    /** initialize your data structure here. */
    private MedianFinder() {
        //储存较小的一半数字 大根堆 堆顶元素为最大值
        this.low = new PriorityQueue<>((o1, o2) -> o2-o1);

        //储存较大的一半数字 小根堆 堆顶元素最小值
        this.high = new PriorityQueue<>(Comparator.comparingInt(o -> o));
    }

    //元素一开始全都入堆small,然后将堆small的最大元素加入到堆large
    //这样可以保证堆small存放的是较小的一半元素，堆large存放的是较大的一半元素

    private void addNum(int num) {
        low.offer(num);
        high.offer(low.remove());

        //平衡两个堆的个数
        if(high.size() > low.size()){
            low.offer(high.remove());
        }
    }

    private double findMedian() {
        if(low.size() ==high.size()){
            //double x =  (low.peek() + high.peek())/2 导致小数点缺失;
            return (low.peek() + high.peek())/2.0;
        }
        return low.peek();
    }


    public static void main(String[] args){
        int[] tmp = new int[]{
                41,35,62,5,97,108,79
        };

        MedianFinder medianFinder = new MedianFinder();

        for (int value : tmp) {
            medianFinder.addNum(value);
        }

        System.out.println(medianFinder.findMedian());
    }
}
