package com.github.haliibobo.learn.leecode;

import org.junit.Test;

public class KthSmallest {


    @Test
    public void test(){
        int[][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
        int k =8;


    }

    private  void kthSmallest(int[][] matrix,int k){
        int lo = matrix[0][0];
        int hi = matrix[matrix.length-1][matrix[0].length-1];
        while (lo < hi){
            int mid = lo + (hi -lo)/2;
             if(k >mid);


        }
    }
}
