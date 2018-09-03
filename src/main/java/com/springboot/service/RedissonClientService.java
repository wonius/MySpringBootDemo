package com.springboot.service;

import java.util.concurrent.TimeUnit;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/9/2
 */
public interface RedissonClientService {

    public void getLock(String key);
    public void tryLock(String kye, TimeUnit unit, int waitTime, int leaseTime);
    public void unLock(String key);
}
