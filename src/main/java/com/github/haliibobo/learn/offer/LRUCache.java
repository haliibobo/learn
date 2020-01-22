package com.github.haliibobo.learn.offer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lru 算法的实现.
 * @param <K>
 * @param <V>
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private final int maxCapacity;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private final Lock lock = new ReentrantLock();

    public LRUCache(int maxCapacity) {
        //1. set access-order, not insertion-order
        super(maxCapacity,DEFAULT_LOAD_FACTOR,true);
        this.maxCapacity = maxCapacity;
    }

    @Override
    public V get (Object k){
        try {
            lock.lock();
            return super.get(k);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public V put(K k,V v) {
        try {
            lock.lock();
            return super.put(k,v);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public int size(){
        try {
            lock.lock();
            return super.size();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void clear(){
        try {
            lock.lock();
            super.clear();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty(){
        try {
            lock.lock();
            return super.isEmpty();
        }finally {
            lock.unlock();
        }
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
        return size() > maxCapacity;
    }


    public static void main(String[] args) {
        int[]  a={1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        LRUCache<String,Integer> cache = new LRUCache<>(3);
        for (int i : a) {
            cache.put(i+"",i);
            System.out.println(cache);
        }
    }

}
