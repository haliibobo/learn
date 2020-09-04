package com.github.haliibobo.learn.distributed;

import com.github.haliibobo.learn.distributed.lock.redis.RedisLock;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-09-04 15:25
 * @description describe what this class do
 */
public class DemoTest {


    public String lockName = "lock:halibobo";

    Jedis jedis;

    @Before
    public void setup(){
        jedis = new Jedis("halibobo.cn");
        jedis.auth("19911205_Lzb");
        System.out.println("connected");
    }

    @Test
    public void test(){
        String owner = UUID.randomUUID().toString();
        RedisLock lock = new RedisLock(lockName,owner,jedis);
        System.out.println(lock.tryLock());
        System.out.println(lock.tryLock());
    }

}
