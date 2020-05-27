package com.github.haliibobo.learn.leecode;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-06-17 22:32
 * @description describe what this class do
 */
public class MedianSortedArrays {

    public static void main(String[] args) {

        int[] nums1 = new int[]{1,3};
        int[] nums2 = new int[]{5,6};
       System.out.println(findMedianSortedArrays(nums1,nums2));
    }

    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1.length == 0 && nums2.length == 0){
            return 0;
        }
        int n1 =nums1.length;
        int n2 =nums2.length;
        int [] res = new int[n1 + n2];

        int i = 0;
        int j=0;
        int k =0;

        int mid = (n1 + n2)/2;

        while (i<n1&&j<n2){
            if(nums1[i] <= nums2[j]){
                res[k]=nums1[i];
                i++;
            }else {
                res[k]=nums2[j];
                j++;
            }
            k++;
        }
        if (i <= mid){
            for (int x = i; x < nums1.length;x++){
                res[k] = nums1[x];
                k++;
            }
        }

        if (j <= mid){
            for (int x = j; x < nums2.length;x++){
                res[k] = nums2[x];
                k++;
            }
        }

        if ((n1 + n2) % 2 != 0 ){
            return res[(n1 + n2)/2];
        } else {
            return ((double)(res[(n1 + n2)/2] + res[(n1 + n2)/2 -1]))/2;
        }

    }

}
