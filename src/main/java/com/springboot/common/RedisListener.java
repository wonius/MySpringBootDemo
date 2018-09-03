package com.springboot.common;

import com.springboot.util.ApplicationContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import javax.annotation.PostConstruct;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/6/4
 */
@Component
public class RedisListener extends JedisPubSub{

    private static final String KEYS_LOCK = "KEYS_LOCK:";

    @Autowired
    private JedisPool jedisPool;
    Jedis jedis = null;

    @Override
    public void onPMessage(String pattern, String channel, String message) {

        System.out.println("onPMessage, channel： " + channel.trim() + ", message: "+message);
        if (StringUtils.startsWith(message, KEYS_LOCK)) {
            return;
        }

        jedisPool = ApplicationContextUtil.getBean(JedisPool.class);
        jedis = jedisPool.getResource();

        Long lock = jedis.setnx(KEYS_LOCK + message, "true");
        jedis.expire(KEYS_LOCK + message, 5);
        if (lock == 0) {
            jedis.close();
            return ;
        }

        jedis.del(KEYS_LOCK);
        jedis.close();
    }

    @PostConstruct
    void init() {
//        jedis = jedisPool.getResource();
//        jedis.setnx("SessionID_123", "hello");
//        jedis.expire("SessionID_123", 10);

        SubThread subThread = new SubThread(jedisPool);
        Thread thread = new Thread(subThread);
        thread.start();
    }
}
