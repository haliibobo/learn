package com.github.haliibobo.learn.java.search;

public interface ST<K extends Comparable<K>,V> {
    void put(K k, V v);
    V get(K k);
    void delete(K k);
    boolean contains(K k);
    boolean isEmpty();
    int size();
    Iterable<K> keys();
    K min();
    K max();
    K floor(K k); // 小于等于k的最大键
    K ceiling(K k);//大于等于k的最小键
    int rank(K k);//小于k的键的数量
    K select(int k); //排名为k的键
    void deleteMin();
    void deleteMax();
    int size(K lo,K hi);//[lo..hi]之间键的数量
    Iterable<K> keys(K lo,K hi);//[lo..hi]之间所有的键，已排序
}
