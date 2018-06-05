package com.springboot.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/6/4
 */
public class SubThread implements Runnable {

    private JedisPool jedisPool;

    public SubThread(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {
        Jedis jedis = jedisPool.getResource();
        jedis.psubscribe(new RedisListener(), "SessionID_123");
    }
}
