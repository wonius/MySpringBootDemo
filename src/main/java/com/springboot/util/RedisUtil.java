package com.springboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/7/29
 */
public class RedisUtil {
    public static String LOCK_TRUE = "true";

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 一定会获取到锁，并设置过期时间
     * @param keyName
     * @param seconds
     * @return
     * @throws InterruptedException
     */
    public boolean getLock(String keyName, Long seconds) throws InterruptedException {
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        boolean lockReturn = connection.setNX(keyName.getBytes(), LOCK_TRUE.getBytes());
        while (!lockReturn) {
            Thread.sleep(10);
            lockReturn = connection.setNX(keyName.getBytes(), LOCK_TRUE.getBytes());
        }
        connection.expire(keyName.getBytes(), seconds);
        connection.close();
        return lockReturn;
    }

    /**
     * 尝试获取锁，如果获取到，并设置过期时间
     * @param keyName
     * @param seconds
     * @return
     */
    public boolean tryLock(String keyName, Long seconds) {
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        boolean lockReturn = connection.setNX(keyName.getBytes(), LOCK_TRUE.getBytes());
        connection.close();
        if (lockReturn) {
            redisTemplate.expire(keyName, seconds, TimeUnit.SECONDS);
        }

        return lockReturn;
    }

    /**
     * 释放锁
     * @param keyName
     * @return
     */
    public boolean releaseLock(String keyName) {
        return redisTemplate.delete(keyName);
    }

}
