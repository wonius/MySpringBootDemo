package com.springboot.common;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.redisson.api.RedissonClient;


/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/9/2
 */
@Configuration
public class RedissonClientConfig {

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
    public RedissonClient getRedisson() {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://"+host+":"+port).setPassword(password);
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
