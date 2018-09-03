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
    @Value("${redis.port}")
    private int port;
    @Value("${redis.host}")
    private String host;
    @Value("${redis.max.total}")
    private Integer maxTotal;
    @Value("${redis.max.idle}")
    private Integer maxIdle;
    @Value("${redis.max.waitmillis}")
    private Long maxWaitMillis;
    @Value("${redis.max.timeout}")
    private int timeout;
    @Value("${redis.password}")
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
