package com.springboot.service.impl;

import com.springboot.service.RedissonClientService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/9/2
 */
@Service
public class RedissonClientServiceImpl implements RedissonClientService {

    @Autowired
    private RedissonClient redissonClient;


    @Override
    public void getLock(String key) {
        RLock rLock = redissonClient.getLock(key);
        Long startTime = System.currentTimeMillis();
        System.out.println("start getLock: " + startTime);
        rLock.lock();
        System.out.println("end getLock: " + (System.currentTimeMillis()-startTime));
    }

    @Override
    public void tryLock(String key, TimeUnit unit, int waitTime, int leaseTime) {
        RLock rLock = redissonClient.getLock(key);
        try {
            Long startTime = System.currentTimeMillis();
            System.out.println("start tryLock: " + startTime);
            rLock.tryLock(waitTime, leaseTime, unit);
            System.out.println("end tryLock: " + (System.currentTimeMillis()-startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unLock(String key) {
        RLock rLock = redissonClient.getLock(key);
        Long startTime = System.currentTimeMillis();
        System.out.println("start unLock: " + startTime);
        rLock.unlock();
        System.out.println("end unLock: " + (System.currentTimeMillis()-startTime));
    }
}
