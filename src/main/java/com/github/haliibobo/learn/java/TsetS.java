package com.github.haliibobo.learn.java;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.commons.lang3.StringUtils;

/**
 * @author lizibo
 * @version 1.0
 * @date 2019-02-18 18:32
 * @description TODO
 */
public class TsetS {
    public static void main(String[] args) {
        String path ="/user/recsys/recpro/unifiedfeed/promotion/address_sensitive/201902181817";
        String offTime = path.substring(path.lastIndexOf("/")+1);
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        int tmp =0;
        for (int i = 0; i <3; i++) {
            System.out.println(tmp++);;
        }


        try {
            if(System.currentTimeMillis() > sdf.parse(offTime).getTime()){
                System.out.println("hao");
               System.out.println(new TsetS().getClass().getSimpleName());
            }
        } catch (ParseException e) {
           e.printStackTrace();
        }

        System.out.println(StringUtils.isNumeric(null));

    }
}
