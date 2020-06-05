package com.github.haliibobo.learn.leecode;

/**
 * 顺时针打印数组.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-05 10:07
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralOrder {

    public int[] spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0|| matrix[0].length == 0){
            return new int[0];
        }
        int rows = matrix.length,columns = matrix[0].length;
        int[] order = new int[rows * columns];
        int index =0 ,left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order[index++] = matrix[top][column];
            }
            for (int row = top + 1; row <= bottom; row++) {
                order[index++] = matrix[row][right];
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order[index++] = matrix[bottom][column];
                }
                for (int row = bottom; row > top; row--) {
                    order[index++] = matrix[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}
