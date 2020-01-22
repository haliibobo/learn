package com.github.haliibobo.learn.java.hash;


public class HashTest  {

    private static final long MAX = (2 << 16) -1;

    public static void main(String[] args) {
        long hash = hash(131072L);
        int partitions = 60;
        long res =hash*partitions/(MAX+1);
        System.out.print("partition:" + res);
    }


    private static long hash(long sku) {
        System.out.print("sku:" + sku +",");
        long hash = sku & MAX;
        System.out.print("hash:" + hash +",");
        return hash;
    }
}
