package com.github.haliibobo.learn.offer;


import java.util.Objects;

/**
 * 2<< 32
 */
public class ConsistentHash<E> {

    public static final int MAX_CAP = 2 << 32;


    public int hash(E e){
        return Objects.hash(e) & (MAX_CAP -1);
    }
}
