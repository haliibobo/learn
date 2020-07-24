package com.github.haliibobo.learn.offer;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-07-23 11:10
 * 给定一个数组A[0,1,...,n-1],
 * 请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 * （注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，
 * B[n-1] = A[0] * A[1] * ... * A[n-2];）
 * 思路除去对角线 上三角和下三角进行相乘
 */
public class Multiply {

    public int[] multiply(int[] A) {
        if (A == null || A.length == 1){
            return new int[1];
        }

        int[] B = new int[A.length];
        int n = B.length;
        for (int i = 0; i <n ; i++) {
            B[i]=1;
        }

        for (int i =1;i<n;i++){
            for (int j = i; j <n; j++) {
                B[i-1] *= A[j];
            }
        }
        for (int i =n-1;i>1;i--){
            for (int j = i; j >0; j--) {
                B[i] *= A[j-1];
            }
        }
        return B;

    }

}
