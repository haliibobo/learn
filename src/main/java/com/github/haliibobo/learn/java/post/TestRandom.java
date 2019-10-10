package com.github.haliibobo.learn.java.post;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: weiwenwen
 * @Date: 2018/9/19 00:11
 * @Description:
 */
public class TestRandom {

    static final int size=100;

    float[] weights=new float[size];

    public TestRandom(){
            float sumWt = 0;
            for (int i = 0; i < size; ++i) {
                sumWt += 1;
                weights[i] = sumWt;
            }
            for (int i = 0; i < size; ++i) {
                weights[i] = weights[i] / sumWt;
            }
    }

    public static void main(String args[]){
        TestRandom testRandom = new TestRandom();
        System.out.println(Arrays.toString(testRandom.weights));
        int idx;
        HashMap<Integer,Integer> hashMap=new HashMap<Integer,Integer>();

        for (int i = 0; i <100 ; i++) {
            float random = (float) Math.random();
            for (idx = 0; idx < size; ++idx) {
                if (random < testRandom.weights[idx]) {
                    break;
                }
            }
            if (idx >= size) {
                idx = size - 1;
            }
            hashMap.compute(idx ,(k,count) -> ((count == null)? 0 : count+1));
        }

        List<Double> doubles = hashMap.values()
            .parallelStream()
            .map(i -> (double)i).collect(Collectors.toList());
        double avg = doubles.stream().mapToDouble(d -> d).average().getAsDouble();//平均值
        System.out.println(avg);
        double std = doubles.stream().map(i -> Math.pow(avg - i, 2)).mapToDouble(d -> d).average().getAsDouble();//方差
        System.out.println(Math.sqrt(std));//标准差（均方差）
        System.out.println(Math.sqrt(std)/avg); //  标准差/平均值

        for(Map.Entry<Integer,Integer> entry: hashMap.entrySet())
        {
            System.out.println(entry.getKey()+ "   "+entry.getValue());
        }
    }
}
