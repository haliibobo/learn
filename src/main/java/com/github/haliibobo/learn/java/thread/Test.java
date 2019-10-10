package com.github.haliibobo.learn.java.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import javafx.scene.effect.SepiaTone;

/**
 * @Auther: lizibo
 * @Date: 2018/11/7 21:31
 * @Description:
 */
public class Test {

   private  Set<Integer> set1;

    public static void main(String[] args)  {
       final Set<Integer> set2 = new HashSet<>();
        set2.add(2);

      //  new Thread(()->set2=null).start();

    }

    public void test(){
        set1.add(2);
        new Thread(()->set1=null).start();
    }



}
