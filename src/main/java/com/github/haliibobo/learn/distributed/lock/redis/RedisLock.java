package com.github.haliibobo.learn.distributed.lock.redis;

import com.github.haliibobo.learn.distributed.lock.Lock;
import com.github.haliibobo.learn.distributed.lock.exception.LockLeaseLostException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-09-03 11:36
 * @description describe what this class do
 */
public class RedisLock implements Lock {

    private String owner; //锁持有者
    private String name;
    private final static long INTERVAL = 1000L; //默认获取锁的间隔，单位纳秒
    private final static int DEFAULT_TTL = 60; //默认锁的TTL，单位默认秒
    private final static TimeUnit DEFAULT_TIMEUNIT = TimeUnit.SECONDS;//默认的获取锁的时间单位
    private static Long RELEASE_SUCCESS = 1L;
    private int ttl = DEFAULT_TTL; //锁持有时间
    private TimeUnit unit = DEFAULT_TIMEUNIT; //锁持有时间的单位，不指定为默认
    private TimeUnit spinTimeUnit = TimeUnit.NANOSECONDS;//默认的获取锁的时间单位
    private long spinInterval = INTERVAL; //获取锁的重试间隔，不指定为默认，单位纳秒
    private Jedis client;
    private static String UNLOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    private int holdCount = 0;

    public RedisLock(String owner, String name, int ttl, TimeUnit unit,
        Jedis client) {
        this.owner = owner;
        this.name = name;
        this.ttl = ttl;
        this.unit = unit;
        this.client = client;
    }

    public RedisLock(String owner, String name, Jedis client) {
        this.owner = owner;
        this.name = name;
        this.client = client;
    }

    @Override
    public void lock() throws InterruptedException {
        if (!tryLock() ||!doLockNanos() ){
            selfInterrupt();
        }
    }
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        return tryLock() || doLockNanos(unit.toNanos(time));
    }

    @Override
    public boolean tryLock() {
         if ("1".equals(client.set(name,owner,new SetParams().nx().ex((int)unit.toSeconds(ttl))))){
             return true;
         }
         return owner.equals(getExclusiveOwner());
    }

    @Override
    public void unlock() throws LockLeaseLostException {
        if (this.isLock()) {
            String sha = client.scriptLoad(UNLOCK_SCRIPT);
            Object result = client
                .evalsha(sha, Collections.singletonList(name), Collections.singletonList(owner));
            if (!RELEASE_SUCCESS.equals(result)) {
                throw new LockLeaseLostException();
            }
        }
    }

    @Override
    public void forceUnLock() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean KeepLockLease() throws LockLeaseLostException {
        if (this.isLock()){
            int nextc = 1 + holdCount;
            if (nextc < 0) {
                // overflow
                throw new Error("Maximum keep count exceeded");
            }
            boolean ok = "1".equals(client.set(name,owner,new SetParams().xx().ex((int)unit.toSeconds(ttl))));
            if (ok){
                holdCount = nextc;
            }else{
                throw new LockLeaseLostException();
            }
            return true;
        }
        return false;
    }

    private String getExclusiveOwner(){
        return client.get(name);
    }


    public boolean isLock() {
        return owner.equals(getExclusiveOwner());
    }

    private boolean doLockNanos(long nanosTimeout)
        throws InterruptedException {
        if (nanosTimeout <= 0L){
            return false;
        }

        final long deadline = System.nanoTime() + nanosTimeout;
        for (;;) {
            if (tryLock()) {
                return true;
            }
            nanosTimeout = deadline - System.nanoTime();
            if (nanosTimeout <= 0L){
                return false;
            }
            if (nanosTimeout > spinTimeUnit.toNanos(spinInterval)){
                LockSupport.parkNanos(this, nanosTimeout);
            }
            if (Thread.interrupted()){
                throw new InterruptedException();
            }
        }
    }
    private boolean doLockNanos() throws InterruptedException {
        for (;;) {
            if (tryLock()) {
                return true;
            }
            LockSupport.parkNanos(this, spinTimeUnit.toNanos(spinInterval));
            if (Thread.interrupted()){
                throw new InterruptedException();
            }
        }
    }

    private static void selfInterrupt() {
        Thread.currentThread().interrupt();
    }
}
