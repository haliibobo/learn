package com.github.haliibobo.learn.java.exctpiions;

import java.util.Map;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-04-16 15:42
 * @description describe what this class do
 */
public class C {

    public void print(){
        Map<String,String> map =null;
        try {
            map.put("1","1");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
