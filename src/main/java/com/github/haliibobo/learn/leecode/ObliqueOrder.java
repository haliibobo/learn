package com.github.haliibobo.learn.leecode;

import org.junit.Test;

/**
 * 斜打印数组.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-05 10:07
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：4
 * 38
 * 2 7 12
 * 1 6 11
 * 5 10
 * 9
 */
public class ObliqueOrder {

    @Test
    public void test(){
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}} ;
        obliqueOrder(matrix);
    }

    public void obliqueOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0|| matrix[0].length == 0){
            return ;
        }
        int rows = matrix.length,columns = matrix[0].length;
        int right = columns - 1, top = 0;
        while (top <rows&&right>-1){
            int i =top;
            int j =right;
            while (i<rows&&j<columns){
                System.out.print(matrix[i++][j++]);
                System.out.print(",");
            }
            System.out.println();
            if(right == 0){
                top ++;
            }
            if(top ==0){
                right--;
            }

        }
    }
}
