package com.github.haliibobo.learn.offer;

import java.util.ArrayList;
import java.util.List;

public class PrintMatrix {

    /*
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
     * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
     * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     *
     * 假设这个矩阵的行数是rows，列数是columns。
     * 打印第一个圈是左上角元素的坐标是（0，0），第二圈的左上角的坐标是（1，1），以此类推。
     * 我们注意到，左上角的坐标中行标和列标总是相同的，于是可以在矩阵中选取左上角为（start，start）的一圈为我们分析的目标。
     * 对一个5*5的矩阵而言，最后一圈只有一个数字，对应的坐标为（2，2）。5>2*2.
     * 对于一个6*6的矩阵而言，最后一圈有4个数字，其左上角的坐标仍然为（2，2）。
     * 发现6>2*2依然成立。于是我们可以得出继续循环的条件是columns>startX*2并且rows>startY*2.
     */

    public static void main(String[] args){
        int[][] matrix = new int[][]{
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16}
        };
        int[][] matrix1 = new int[][]{{1}};
        int[][] matrix2 = new int[][]{
            {1,2,3,4},
            {5,6,7,8}
        };
        int[][] matrix3 = new int[][]{
            {1,2},
            {3,4},
            {5,6},
            {7,8},
            {9,10}
        };
        int[][] matrix4 = new int[][]{
            {1,2,3,4,5}
        };
        ArrayList<Integer> list = printMatrix(matrix4);
        System.out.println(list);
    }

    private static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return list;
        }
        //列数
        int columns = matrix[0].length;
        //行数
        int rows = matrix.length;
        //开始坐标
        int start = 0;
        while (columns > start * 2 && rows > start * 2) {
            printMatrixInCircle(list,matrix,rows,columns,start);
            start++;
        }
        return list;
    }

    private static void printMatrixInCircle(List<Integer> list,int[][] matrix, int rows, int columns, int start) {
        //结束行号
        int endX = rows - 1 - start;
        //结束列号
        int endY = columns - 1 - start;

        //从左至右打印一行
        int tmp = start;
        while (tmp <= endY){
            list.add(matrix[start][tmp++]);
        }

        System.out.println(tmp);

        //从上到下打印一列(多于1行时)
        if (endX > start) {
            tmp = start + 1;
            while (tmp <= endX) {
                list.add(matrix[tmp++][endY]);
            }
        }

        System.out.println(tmp);

        //TODO 从右到左打印一行(多于1行1列时)
        if (endY > start && endX > start) {
            tmp = endY - 1;
            while (tmp >= start) {
                list.add(matrix[endX][tmp--]);
            }
        }

        //TODO 从下到上打印一列(多于1行1列时)
        if (endX - 1 > start && endY > start) {
            tmp = endX - 1;
            while (tmp > start) {
                list.add(matrix[tmp--][start]);
            }
        }
    }
}
