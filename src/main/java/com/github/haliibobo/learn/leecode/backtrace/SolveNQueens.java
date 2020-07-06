package com.github.haliibobo.learn.leecode.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-07-06 10:29
 * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线
 * 链接：https://leetcode-cn.com/problems/eight-queens-lcci
 */
public class SolveNQueens {

    @Test
    public void test(){
        System.out.println(solveNQueens(4));
    }


    private List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] colmns = new int[n];
        queen(0,n,colmns,res);
       return  res;
    }

    //放置第 n+1行皇后位置
    private void  queen(int n,int qSize,int[] colmns,List<List<String>> res){
        if(n == qSize){
            List<String> list = new ArrayList<>();
            for (int colmn : colmns) {
                StringBuilder s = new StringBuilder(qSize);
                for (int i = 0; i <qSize ; i++) {
                    if(i == colmn){
                        s.append("Q");
                    }else {
                        s.append(".");
                    }
                }
                list.add(s.toString());
            }
            res.add(list);
            System.out.println(Arrays.toString(colmns));
            return ;
        }
        for (int i =0;i<qSize;i++){
           colmns[n] =i;// 第(n+1)行皇后位置 放置到（i+1）列
            if(isOk(n,colmns)){
                queen(n+1,qSize,colmns,res);
            }
        }
    }

    private boolean isOk(int n, int[] colmns) {
        for (int i = 0; i <n ; i++) {
            if(colmns[i] == colmns[n]){
                return false;
            }
            if (Math.abs( colmns[i] -colmns[n]) == n-i){
                return false;
            }
        }
        return true;
    }
}
