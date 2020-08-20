package com.github.haliibobo.learn.leecode.backtracking;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CombinationSumII {

    @Test
    public void test(){
        int[] candidates = {7,5,2,3,9,1,1,3};
        int target =8;
        combinationSum2(candidates,target).forEach(System.out::println);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        sort(candidates,0,candidates.length-1);
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        find(candidates,candidates.length,0,target,path,res);
        return res;
    }

    private void find(int[] candidates, int len, int begin, int residue, Deque<Integer> path, List<List<Integer>> res) {
        if (residue == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i =begin;i <len;i++){
            if (residue -candidates[i] < 0) {
                break;
            }
            if (i > begin && candidates[i] == candidates[i -1]) {
                continue;
            }
            path.addLast(candidates[i]);
            find(candidates,len,i+1,residue - candidates[i],path,res);
            path.removeLast();

        }
    }

    public void sort (int[] a,int lo,int hi ){
        if (lo >= hi) return;
        int i =lo,j=hi+1;
        while(true){
            while(a[++i]<a[lo]) if(i >=hi) break;
            while(a[lo]<a[--j]);
            if (i >=j) break;
            int tmp =a[i];
            a[i] =a[j];
            a[j]=tmp;
        }
        int tmp = a[lo];
        a[lo] =a[j];
        a[j] =tmp;
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }
}
