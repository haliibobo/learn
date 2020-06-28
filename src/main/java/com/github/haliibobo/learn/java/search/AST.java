package com.github.haliibobo.learn.java.search;

/**
 * 禁止kv为null
 * @param <K> 不可变类型
 * @param <V>
 */
public abstract class AST<K extends Comparable<K>,V> implements ST<K,V>{
    @Override
    public boolean contains(K k){
        return get(k) !=null;
    }
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void deleteMin() {
        delete(min());
    }

    @Override
    public void deleteMax() {
        delete(max());
    }

    @Override
    public int size(K lo, K hi) {
        if(hi.compareTo(lo) <0){
            return 0;
        }
        if(contains(hi)){
            return rank(hi) -rank(lo) +1;
        }
        return rank(hi) -rank(lo);
    }

    @Override
    public Iterable<K> keys() {
        return keys(min(),max());
    }

     class Node<K,V> {
        final K k;
        V v;
        Node<K,V> next;
          public Node(K k ,V v,Node<K,V> next){
              this.k=k;
              this.v=v;
              this.next=next;
          }
     }
}
