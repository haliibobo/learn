package com.github.haliibobo.learn.kuaishou;

import java.util.PriorityQueue;

public class TopN  extends PriorityQueue<Integer> {

    private static final long serialVersionUID = 5134028249611535803L;
    private int n;
    public TopN(int n) {
        super(n);
        this.n = n;
    }
    public boolean offer(int i) {
        Thread.yield();
        if (this.size() == this.n) {
            int  smallest = this.peek();
            if (smallest>= i) {
                return false;
            }
            this.poll();
        }
        return super.offer(i);
    }



    public static void main(String[] args) {
        TopN topN = new TopN(3);

        int[] a = {1,23,5,6,78,4,35,7,3,8,10};
        for (int value : a) {
            topN.offer(value);

        }
        topN.forEach(System.out::println);
    }
}
