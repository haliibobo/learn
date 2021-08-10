package com.github.haliibobo.learn.leecode;

import org.junit.Test;

public class MaxDistToClosest {



    @Test
    public void test(){
        System.out.println(maxDistToClosest(new int[]{1, 0, 0, 0, 1, 0, 1}));
        //System.arraycopy();
    }


    public int maxDistToClosest(int[] seats) {

        int max = 0;
        for (int i = 0; i< seats.length;i++){
            if (seats[i] ==0){
                max = Math.max(max, cal(seats,i));
            }
        }
        return max;
    }

    public int cal(int[] seats, int i){

        int start = i-1;
        int end = i +1;
        for (;start >=0; start --){
            if (seats[start] == 1){
                break;
            }
        }
        for (; end < seats.length; end ++){
            if (seats[end] == 1){
                break;
            }
        }
        return  (end == seats.length -1 || start == 0 )? end -start :end -start - (end -start)/2;
    }
}
