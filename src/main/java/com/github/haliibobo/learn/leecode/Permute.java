package com.github.haliibobo.learn.leecode;

import org.junit.Test;

import java.util.*;

public class Permute {


    @Test
    public void  test (){
        int[] nums = {1,2,3};
        System.out.println(permute(nums));
    }




    public List<List<Integer>> permute(int[] nums) {

        if (nums == null ){
            return new ArrayList<>();
        }
        int sum =1;
        int n = nums.length;
        List<List<Integer>>  res =  new ArrayList<>(sum);
        if (nums.length == 1 ){
            List<Integer> r1  =  new ArrayList<>(1);
            r1.add(nums[0]);
            res.add(r1);
            return res;
        }


        int[] index = new int[nums.length];
        for(int i=0;i<index.length;i++){
            index[i] = i;
            sum *=i+1;
        }





        for (int i =0 ; i< sum;i++){
            res.add(null);
        }


        for (int i =0;i < sum/2;i++){
            List<Integer> r1  =  new ArrayList<>(n);
            List<Integer> r2  =  new ArrayList<>(n);
            exchange(index,i);
            for(int m: index){
                r1.add(nums[m]);
                r2.add(nums[n-m-1]);
            }
            res.set(i,r1);
            res.set(sum -i-1,r2);
        }
        return res;
    }
    public void exchange (int[] index ,int count){
        if (count >0){
            int hi = 0;
            for (int i = index.length -1; i >0;i --){
                if (index[i] > index[i-1]){
                    hi = i-1;
                    break;
                }
            }
            int lo = hi +1;
            for (int i = index.length -1; i > hi;i --){
                if (index[i] > index[hi]){
                    if ( index[lo] > index[i]){
                        lo = i;
                    }
                }
            }
            Map<String,Integer> m = new HashMap<>();
            //m.keySet()
            //ClassLoader
            m.entrySet().removeIf(e-> e.getValue()>1);

            int temp = index[hi];
            index[hi] = index[lo];
            index[lo] = temp;
            Arrays.sort(index,hi+1,index.length);
            System.out.println();
        }
    }
}
