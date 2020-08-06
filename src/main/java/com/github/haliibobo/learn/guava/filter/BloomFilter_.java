package com.github.haliibobo.learn.guava.filter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-05 18:02
 * @description describe what this class do
 */
public class BloomFilter_ {


    @Test
    public void test(){
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),Integer.MAX_VALUE,0.33);
        bloomFilter.put(234242423);
        System.out.println(bloomFilter.test(Integer.MAX_VALUE));
    }

}
