package com.github.haliibobo.learn.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.params.ZAddParams;

import java.util.*;

public class RedisBase {
    Jedis jedis;

    @Before
    public void setup(){
        jedis = new Jedis("182.92.98.63");
        jedis.auth("19911205_Lzb");
        System.out.println("connected");
    }

    /**
     * set 命令会丢失过期时间.
     * @throws InterruptedException
     */
    @Test
    public void TestString() throws InterruptedException {
        String statusCodeReply = jedis.setex("halibobo", 60,"hello halibobo use redis");
        System.out.println(statusCodeReply);
        Thread.sleep(10000);
        System.out.println(jedis.ttl("halibobo"));
        String xx = jedis.set("halibobo","hello halibobo use redis",new SetParams().nx());
        System.out.println(xx);
        System.out.println(jedis.ttl("halibobo"));
    }
    /**
     * list 操作不会丢失过期时间
     */
    @Test
    public void TestLinkList() throws InterruptedException {
        jedis.lpush("halibobo_list","halibobo1");
        jedis.expire("halibobo_list",60);
        jedis.lpush("halibobo_list","halibobo2","halibobo3");
        Thread.sleep(1000);
        List<String> multiBulkReply = jedis.lrange("halibobo_list",0,-1);
        System.out.println(multiBulkReply);
        System.out.println(jedis.ttl("halibobo_list"));

    }

    /**
     *set 操作不会丢失过期时间
     */
    @Test
    public void TestSet() throws InterruptedException {
        jedis.sadd("halibobo_set","halibobo1");
        jedis.expire("halibobo_set",60);
        jedis.sadd("halibobo_set","halibobo1","halibobo2");
        Thread.sleep(1000);
        Set<String> multiBulkReply = jedis.smembers("halibobo_set");
        System.out.println(multiBulkReply);
        System.out.println(jedis.ttl("halibobo_set"));
    }

    /**
     * 也不会丢失过期时间
     * @throws InterruptedException
     */
    @Test
    public void TestHash() throws InterruptedException {
        jedis.hset("halibobo_hash","string","this is a string");
        jedis.expire("halibobo_hash",60);
        Thread.sleep(1000);
        jedis.hset("halibobo_hash","long","1");
        jedis.hset("halibobo_hash","float","1.0004");
        System.out.println(jedis.hgetAll("halibobo_hash"));

        jedis.hincrBy("halibobo_hash","long",1);

        System.out.println(jedis.hgetAll("halibobo_hash"));
        System.out.println(jedis.ttl("halibobo_hash"));
        Map<String,String> map = new HashMap<>();
        map.put("hello","hello");
        map.put("hi","hi");
        jedis.hmset("halibobo_hash",map);
        System.out.println(jedis.ttl("halibobo_hash"));
    }

    /**
     *
     * @throws InterruptedException
     */
    @Test
    public void TestZSet() throws InterruptedException {
        jedis.zadd("halibobo_zset",1,"zset1");
        jedis.expire("halibobo_zset",60);
        Thread.sleep(1000);
        jedis.zadd("halibobo_zset",2.1,"zset2");
        jedis.zadd("halibobo_zset",0.5,"zset3");

        Map<String,Double> map = new HashMap<>();
        map.put("hello",2.4);
        map.put("hi",1.6);

        jedis.zadd("halibobo_zset",map);
        System.out.println(jedis.zadd("halibobo_zset",0.5, "zset4", new ZAddParams().ch().nx()));
        System.out.println(jedis.zrange("halibobo_zset",0,2));

        jedis.zincrby("halibobo_zset",2,"zset3");

        System.out.println(jedis.zrange("halibobo_zset",0,2));
        System.out.println(jedis.zrangeByScoreWithScores("halibobo_zset",0,3));
        System.out.println(jedis.ttl("halibobo_zset"));
    }


    @Test
    public void test(){
        System.out.println(isValid("()"));
    }

    public boolean isValid(String s) {

        char[] chars = s.toCharArray();
        Deque<Character> dequeue  = new ArrayDeque<>();

        for(char c : chars){
            switch(c){
                case '(':
                case '[':
                case '{': dequeue.push(c);
                    break;
                case ')': {
                    if (dequeue.isEmpty() || dequeue.pop() != '('){
                        return false;
                    }
                    break;
                }
                case '}':{
                    if (dequeue.isEmpty() || dequeue.pop() != '{'){
                        return false;
                    }
                    break;
                }
                case ']': {
                    if (dequeue.isEmpty() || dequeue.pop() != '['){
                        return false;
                    }
                    break;
                }
                default:
            }
        }
        return true;
    }
}
