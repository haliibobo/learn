package com.github.haliibobo.learn.meituan;

import org.junit.Test;

import java.util.*;

/**
 * 回溯法，马走日
 */
public class interview2 {

    private List<Map<Integer,Integer>> res = new ArrayList<>();
    @Test
    public void test(){
        int m=3;
        int n=3;
        int x =1;
        int y =0;
        int[][] a = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j =0;j< n;j++){
                    a[i][j]=1;
            }
        }
        a[x][y]=0;
        Map<Integer,Integer> map =  new HashMap<>();
        map.put(x,y);
        res.add(map);
        judge(a,x,y);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j <a[0].length ; j++) {
                System.out.print(a[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private void judge(int[][] a, int x, int y) {
        isNoOk(a, x, y, 2, 1);
        isNoOk(a, x, y, 2, -1);
        isNoOk(a, x, y, -2, 1);
        isNoOk(a, x, y, -2, -1);
        isNoOk(a, x, y, 1, 2);
        isNoOk(a, x, y, 1, -2);
        isNoOk(a, x, y, -1, 2);
        isNoOk(a, x, y, -1, -2);
        if(!res.isEmpty())
        res.remove(res.size()-1);
        a[x][y] =1;
    }

    private boolean isNoOk(int[][]a, int x, int y, int x_, int y_){
        boolean isOK= x+x_ >-1 && y+y_>-1&&x+x_<a.length&&y+y_<a[1].length&&a[x+x_][y+y_] ==1 ;
        if(isOK){
            a[x+x_][y+y_]=0;
            Map<Integer,Integer> m =  new HashMap<>();
            m.put(x+x_,y+y_);
            res.add(m);
            System.out.println(res);
            if(res.size()<=a.length*a[0].length){
                judge(a,x+x_,y+y_);
            }
        }
        return !isOK;
    }
}
