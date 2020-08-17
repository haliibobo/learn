package com.github.haliibobo.learn.leecode;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-13 15:00
 * @description describe what this class do
 */
public class GetPermutation {


    /**
     * 47 25413
     * 48 25431
     * 0  12345
     * 1  12354
     */
    @Test
    public void test(){
        System.out.println(getPermutation(5,47));
        System.out.println(getPermutation(5,48));
        System.out.println(getPermutation(5,1));
        System.out.println(getPermutation(5,2));
    }




    public String getPermutation(int n,int k){

        k = k-1;

        if (n == 1){
            return "1";
        }
        int [] bkt = new int[n-1];
        bkt[0] =1;

        List<Integer> comb = new ArrayList<>();
        comb.add(0);
        for (int i = 1; i <n-1 ; i++) {
            bkt[i] = bkt[i-1]*(i+1);
            comb.add(0);
        }

        int start = bkt.length-1;

        while ( k > 0  ){
                int c = k/bkt[start];
                k = k - c*bkt[start];
                if(c >0) {
                    comb.set(bkt.length-1-start,c);
               }
                start--;
        }

        return cal(n,comb);
    }

    private String cal (int n , List<Integer> comb) {

        StringBuilder s = new StringBuilder();

                List<Integer> list = new ArrayList<>();

        for (int i = 1; i <=n ; i++) {
            list.add(i);
        }

        comb.forEach(v -> {
            s.append(list.get(v));
            list.remove((int) v);
        });
        s.append(list.get(0));
        return s.toString();

    }


}
