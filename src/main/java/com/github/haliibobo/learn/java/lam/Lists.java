package com.github.haliibobo.learn.java.lam;

import com.github.haliibobo.learn.java.collection.GsonUtil;
import com.google.gson.reflect.TypeToken;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-21 14:31
 * @description describe what this class do
 */
public class Lists {


    @Test
    public void test1(){
        String s = "[{'cid3':'1','sku':'2'},{'cid3':'1','sku':'3'}"
            + ",{'cid3':'2','sku':'4'}]";
        List<Map<String,String>> topskus = GsonUtil.fromJson(s,new TypeToken<List<Map<String,String>>>(){});
        Map<String, List<String>> tempMap = topskus.stream()
            .collect(Collectors.groupingBy(map  -> map.get("cid3"),
                Collectors.mapping(map -> map.get("sku"), Collectors.toList())
            ));
        System.out.print(tempMap);

    }

    @Test
    public void test2(){
        String s = "['1','2','3','2']";
        List<String> topskus = GsonUtil.fromJson(s,new TypeToken<List<String>>(){});
        Map<String,Long> result =
            topskus.stream().filter(Objects::nonNull).collect(
                Collectors.groupingBy(
                    Function.identity(), Collectors.counting()
                )
            );
        System.out.print(result);

    }

    @Test
    public void test3(){
        String s = "[['1'],['2','3'],['2']]";
        List<List<String>> topskus = GsonUtil.fromJson(s,new TypeToken<List<List<String>>>(){});
        List<String>  l=topskus.stream().flatMap(Collection::stream)
            .collect(Collectors.toList());
        System.out.print(l);

    }

}
