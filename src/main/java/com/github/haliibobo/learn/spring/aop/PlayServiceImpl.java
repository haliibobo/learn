package com.github.haliibobo.learn.spring.aop;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service("playService")
public class PlayServiceImpl  implements PlayService{


    public PlayServiceImpl(String s){

    }

    private  final Random random = new Random();
    public void play(String game) throws InterruptedException {
        Thread.sleep(random.nextInt(1000));
        System.out.println("play " +game +" done");
    }
}
