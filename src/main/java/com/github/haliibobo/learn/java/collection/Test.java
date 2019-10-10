package com.github.haliibobo.learn.java.collection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-05-21 11:39
 * @description describe what this class do
 */
public class Test {

    public static void main(String[] args) throws ParseException, IOException {
        String s ="12426196\t1\t1566799616245";
        String dateTime =s.split("\t")[2];
         final String fomat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(fomat);
        String timeStamp = sdf.format(new Date(Long.parseLong(dateTime)));

        final Map<String,String> priceMinMap = new HashMap<>(4);

        priceMinMap.put("skuId",s.split("\t")[0]);
        priceMinMap.put("priceMin",s.split("\t")[1]);
        priceMinMap.put("calculateTime",timeStamp);
        Map<String,String> m = new HashMap<>(1);
        m.put("v",GsonUtil.toJson(priceMinMap,false));

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bo);
        os.writeObject(priceMinMap);
        ;
        System.out.println("price_min~~~12426196".getBytes().length);
        System.out.println(bo.toByteArray().length);

        List<Bean> srcList = new ArrayList<>();
        srcList.add(new Bean("1","1"));
        srcList.add(new Bean("2","2"));
        srcList.add(new Bean("3","3"));
        List<Bean> copyList = new ArrayList<>(Arrays.asList( new Bean[srcList.size()]));
        Collections.copy(copyList, srcList);
        System.out.println("srcList : " + srcList);
        System.out.println("copyList : " + copyList);
        srcList.remove(0);
        srcList.get(0).setId("2222222");
        System.out.println("srcList : " + srcList);
        System.out.println("copyList : " + copyList);
        copyList.remove(2);
        System.out.println("srcList : " + srcList);
        System.out.println("copyList : " + copyList);
    }

}

