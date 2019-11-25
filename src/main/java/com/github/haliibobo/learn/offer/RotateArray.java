package com.github.haliibobo.learn.offer;

public class RotateArray {

    /*
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     */

    /*
     * 传进去旋转数组，注意旋转数组的特性：
     * 1.包含两个有序序列
     * 2.最小数一定位于第二个序列的开头
     * 3.前序列的值都>=后序列的值
     * 定义把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     */

    /* 全局遍历 O(n)
     * 1.数组为空
     * 2.部分旋转，例如由（1,2,3,4,5）旋转为（3,4,5,1,2），此时只需要遍历数组，找到当前数比前面的数小的数即可。
     * 3.完全旋转，例如由（1,2,3,4,5）旋转为（1,2,3,4,5），此时第一个数最小。
     */
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[i + 1]) {
                return array[i + 1];
            }
        }
        return array[0];
    }

    /* 利用二分查找的思想
     * 由（1,2,3,4,5）旋转为（3,4,5,1,2）
     * 二分查找时间复杂度 O(log2n)
     * 旋转之后的数组实际上可以划分成两个有序的子数组：前面子数组的大小都大于后面子数组中的元素
     * 注意到实际上最小的元素就是两个子数组的分界线。
     * 本题目给出的数组一定程度上是有序的，用二分查找法寻找这个最小的元素。
     */
    public int minNumberInRotateArray_1(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int low = 0;
        int high = array.length - 1;
        int mid = low;
        //low指针永远位于前一个子数组中，high指针永远位于后一个子数组中
        //最终low指针指向前一个数组的最后一个数字，high指针指向后一个数组的第一个数字 high指针指向的数字即为所求
        while (array[low] >= array[high]) {
            if (high - low == 1) {
                mid = high;
                break;
            }
            mid = (low + high) / 2;
            //中间元素大于第一个元素，中间元素位于第一个子数组内 最小数字在mid后面 移动low指针到mid位置
            if (array[mid] > array[low]) {
                low = mid;
            }
            //中间元素小于最后一个元素，中间元素位于第二个子数组内 最小数字在mid前面 移动high指针到mid位置
            if (array[mid] < array[low]) {
                high = mid;
            }
        }
        return array[mid];
    }
}
