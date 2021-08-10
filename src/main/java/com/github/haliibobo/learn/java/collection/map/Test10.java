package com.github.haliibobo.learn.java.collection.map;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test10 {

    public static void main(String[] args) {
        List<String> cols = new ArrayList<>();
        cols.add("col1");
        cols.add("col2");
        List<Map<String,Object>> data = new ArrayList<>();
        Map<String,Object> data1 = new HashMap<>();
        Map<String,Object> data2 = new HashMap<>();
        Map<String,Object> data3 = new HashMap<>();
        Map<String,Object> data4 = new HashMap<>();
       // data1.put("row",1);
        data1.put("col1",1);
        data1.put("col2","2");
        data1.put("col3",3.6);
        //data2.put("row",2);
        data2.put("col1",1);
        data2.put("col2","2");
        data2.put("col3",3.6);
        //data3.put("row",3);
        data3.put("col1",2);
        data3.put("col2","2");
        data3.put("col3",3.6);
       // data4.put("row",4);
        data4.put("col1",2);
        data4.put("col2","6");
        data4.put("col3",3.6);
        data.add(data1);
        data.add(data2);
        data.add(data3);
        data.add(data4);

        Map<Map<String,Object>,Long> res= data.stream().map(m-> {
            Map<String,Object> d = new HashMap<>();
            cols.forEach(c->d.put(c,m.get(c)));
            return  d;
        }).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Map<String,Object>> res1= res.entrySet().stream().filter(e->e.getValue()>1).map(Map.Entry::getKey).collect(Collectors.toList());


        System.out.println(res1);
        System.out.println(res);


    }

    public  static int count( String s,String target ) {
        int count = 0;
        StringBuilder tmp = new StringBuilder();
        for (char c :s.toLowerCase().toCharArray()){
            if (c >= 97 &&  c <= 122 ){
                tmp.append(c);
            }else {
                if (target.equals(tmp.toString())) {
                    count++;
                }
                tmp = new StringBuilder();
                }
            }
       /* for (String ss : tmp.toString().split("#")){
            if (target.equals(ss)) {
                count++;
            }
        }*/
        return count;
    }


}
