package com.springboot.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/6/4
 */
@Configuration
@EnableCaching
public class JedisConfig extends CachingConfigurerSupport{
    @Value("${jedis.port}")
    private int port;
    @Value("${jedis.host}")
    private String host;
    @Value("${jedis.max.total}")
    private Integer maxTotal;
    @Value("${jedis.max.idle}")
    private Integer maxIdle;
    @Value("${jedis.max.waitmillis}")
    private Long maxWaitMillis;
    @Value("${jedis.max.timeout}")
    private int timeout;
    @Value("${jedis.password}")
    private String password;


    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        return jedisPool;
    }
}
