package com.github.haliibobo.learn.metric;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Demo {

    @Test
    public void test(){
        Long currTmKey =  LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        int index = (int)(System.currentTimeMillis() / 1000);
        System.out.println(currTmKey);
        System.out.println(index);
    }
}
