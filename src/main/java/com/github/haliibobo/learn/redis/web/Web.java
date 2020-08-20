package com.github.haliibobo.learn.redis.web;

import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Web {

    final ScheduledExecutorService es  =Executors.newSingleThreadScheduledExecutor();
    public final static long LIMIT = 10000000;
    //scheduleAtFixedRate 设定间隔时间和业务运行时间取最长，作为下一次运行间隔时间
    //scheduleWithFixedDelay 每次完成后 间隔一段时间
    /**
     * 使用散列存储登陆cookie令牌与已登陆用户的映射关系
     */
    public String checkToken (Jedis conn,String token){
        return  conn.hget("login:" ,token);
    }

    public void updateToken (Jedis conn,String token,String user,String item){
        long now =System.currentTimeMillis();
        //更新token
        conn.hset("login:",token,user);
        //更新最近用户登陆记录
        conn.zadd("recent:",now ,user);
        if (item != null){
            //最近一次浏览商品
            conn.zadd("viewed" + token,now,item);
            //删除从排名最低到201名的数据，保留200条数据，使用zset结构，完美
            conn.zremrangeByRank("viewed" + token,0,-201);
        }

    }

    public void clearSession(Jedis conn){

        es.scheduleWithFixedDelay(()->{
            long size = conn.zcard("recent:");
            if (size <= LIMIT){
                return;
            }
            long end_index = Math.min(size -LIMIT,100);
            Set<String> tokens = conn.zrange("recent:",0,end_index -1);
            Set<String> session_keys = new HashSet<>();
            int i =0;
            for (String token : tokens) {
                session_keys.add("viewed:" + token);
                session_keys.add("cart:" + token) ;
            }
            conn.del(session_keys.toArray(new String[1]));
            conn.hdel("login:", tokens.toArray(new String[1]));
            conn.zrem("recent:" ,tokens.toArray(new String[1]));
        },0,1, TimeUnit.SECONDS);
    }
    public void  addToCart(Jedis conn,String session,String item,int count){
        if (count <= 0){
            conn.hdel("cart:"+session,item);
        }else {
            conn.hset("cart:"+session,item,String.valueOf(count));
        }
    }
}
