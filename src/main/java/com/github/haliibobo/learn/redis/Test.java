package com.github.haliibobo.learn.redis;

import com.google.gson.Gson;
import java.lang.management.ManagementFactory;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import redis.clients.jedis.Jedis;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-04-01 18:17
 * @description describe what this class do
 */
public class Test {

    private static final Charset CHARSET = Charset.forName("UTF-8");
    private static Gson gson = new Gson();

    public static Map<byte[],byte[]> last;

    public static void main(String[] args) {

        System.out
            .println(ManagementFactory.getRuntimeMXBean().getInputArguments().stream().anyMatch(
                arg -> arg.startsWith("-Xrunjdwp") || arg.startsWith("-agentlib:jdwp")));

        try {
            Map map =null;
           // map.isEmpty();
            List list =new ArrayList();
            list.get(9);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }



/*
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("182.92.98.63");
        jedis.auth("19911205_Lzb");
        System.out.println("连接成功");

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("300000403", "300000403");
        hashMap.put("300000404", "300000404");
        hashMap.put("300000406", "300000406");
        hashMap.put("300000407", "300000407");
        Map<String, byte[]> resultMap = getRedisValue(hashMap);
       // jedis.hmset("test",resultMap);
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("300000403", "400000403");
        hashMap2.put("300000404", "400000404");
        hashMap2.put("300000406", "400000406");
        hashMap2.put("300000407", "400000407");
        Map<String, byte[]> newMap = getRedisValue(hashMap2);
        newMap.forEach(resultMap::put);
        resultMap.forEach((k,v)-> System.out.println(new String(k) +":" +new String(v)));
       // jedis.hmset("test".getBytes(),resultMap);


    }

    private static <T> Map<String, byte[]> getRedisValue(Map<String, T> redisValue) {
        Map<String, byte[]> byteMap = new HashMap<>();
        redisValue.forEach((k, v) -> {
            if (v != null) {
                byteMap.put(k, gson.toJson(v).getBytes(CHARSET));
            }
        });
        return byteMap;
    }*/
    }
}
