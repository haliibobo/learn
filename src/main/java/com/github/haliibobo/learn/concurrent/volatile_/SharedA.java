package com.github.haliibobo.learn.concurrent.volatile_;

public class SharedA {

    public   long sharedLong = 6;
    public  volatile long sharedVolLong = 6;
    public  Long sharedLongObj = 6L;
    public  volatile Long sharedVolLongObj = 6L;
    public  SharedB[] sharedBS;
    public volatile SharedB[] sharedVolBS;


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
