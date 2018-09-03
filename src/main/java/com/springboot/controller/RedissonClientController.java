package com.springboot.controller;

import com.springboot.service.RedissonClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/9/2
 */
@RestController
public class RedissonClientController {

    @Autowired
    private RedissonClientService redissonClientService;

    @RequestMapping(value = "/getLock", method = RequestMethod.POST)
    public void getLock(@RequestBody String key) {
        redissonClientService.getLock(key);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redissonClientService.unLock(key);
    }

    @RequestMapping(value = "/unLock", method = RequestMethod.POST)
    public void unLock(@RequestBody String key) {
        redissonClientService.tryLock(key, TimeUnit.SECONDS, 3, 5);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redissonClientService.unLock(key);
        redissonClientService.tryLock(key, TimeUnit.SECONDS, 3, 5);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redissonClientService.unLock(key);
    }

    @RequestMapping(value = "/tryLock", method = RequestMethod.POST)
    public void tryLock(@RequestBody String key) {
        redissonClientService.tryLock(key, TimeUnit.SECONDS, 3, 5);
        try {
            Thread.sleep(100);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        redissonClientService.unLock(key);
    }
}
