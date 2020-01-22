package com.github.haliibobo.learn.offer;

import org.junit.Assert;
import org.junit.Test;

public class Find {


    @Test
    public void solution (){
        int[][] array = {{1,2,5,8},{3,4,7,9},{5,8,11,13}};
        Assert.assertFalse(find1(0,array));
        Assert.assertTrue(find2(1,array));
    }

    private boolean find1 (int target, int [][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean find2 (int target, int [][] array) {
        int rows = array.length;
        int cols = array[0].length;

        if (rows == 0 || cols == 0){
            return false;
        }

        if (2*target < (array[0][0] +array[rows -1][cols-1])){
            int row =0;
            int col = cols -1;
            // 临界值 是>=0 不是>0
            while (row<rows && col >=0){
                if (array[row][col] < target) {
                    row ++;
                } else if (array[row][col] > target){
                    col --;
                }else {
                    return true;
                }
            }
        } else {
            int row = rows -1;
            int col = 0;
            while (row >=0 && col <cols){
                if (array[row][col] < target) {
                    col ++;
                } else if (array[row][col] > target){
                    row --;
                }else {
                    return true;
                }
            }
        }

        return false;

    }

}
