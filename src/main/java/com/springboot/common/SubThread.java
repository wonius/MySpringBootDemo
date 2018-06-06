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
    String pattern = "*";
//    String pattern0 = "keyspace@0:SessionID_*";
//    String pattern1 = "__key*__:*";
//    String pattern2 = "__keyevent@0__:*";
//    String[] patterns = new String[]{"SessionID_*"};


    public SubThread(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {
        Jedis jedis = jedisPool.getResource();
        jedis.psubscribe(new RedisListener(), pattern);
    }
}
