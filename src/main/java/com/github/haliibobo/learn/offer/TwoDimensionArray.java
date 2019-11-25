package com.github.haliibobo.learn.offer;

public class TwoDimensionArray {

    /*
     *二维数组中的查找
     *在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到
     *下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    public static void main(String[] args) {
        int[][] array = {{1, 3, 5, 7}, {2, 4, 6, 8}, {3, 6, 9, 12}, {4, 8, 10, 16}};
        //int[][] array = {{1,2,8,9},{4,7,10,13}};
        int target = 1;
        System.out.println(find(target, array));
    }

    private static boolean find(int target, int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        //列数
        int w = array[0].length;
        //行数
        int h = array.length;
        if (target < array[0][0] || target > array[h - 1][w - 1]) {
            return false;
        }

        /*
         *取二维数组右上角的值作为base，如果target比base大向下找 如果比target小向左找
         *结束的条件是找到或者是找到左下角最后一个
         *1  2  9   10
         *3  4  11  12
         *5  7  12  14
         *6  9  14  16
         */
        int i = 0;
        int j = array[0].length - 1;
        boolean isFind = false;
        //TODO 循环条件判断错误
        while (j >= 0 && i <= array.length) {
            if (target == array[i][j]) {
                isFind = true;
                break;
            }
            if (target > array[i][j]) {
                i++;
            } else {
                j--;
            }
        }
        return isFind;
    }

    //二维数组中的每一行进行二分查找
    public boolean find_1(int[][] array, int target) {

        for (int[] ints : array) {
            int low = 0;
            int high = ints.length - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (target > ints[mid]) {
                    low = mid + 1;
                } else if (target < ints[mid]) {
                    high = mid - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
