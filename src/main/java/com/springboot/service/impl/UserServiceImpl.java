package com.springboot.service.impl;

import com.springboot.domain.User;
import com.springboot.mapper.UserMapper;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/4/17
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    public static String hello;

    @Value("${hello.value}")
    private String pHello;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser() {
        return userMapper.getUser();
    }

    @Value("${hello.value}")
    public void setHello(String hello) {
        hello = hello;
        System.out.println("XXXXXXXXXXXXXXXXXXXXX"+hello);
    }

    @PostConstruct
    public void init() {
        hello = pHello;
        System.out.print("XXXXXXXXXXXXXXXXXXXXX"+hello);
    }
    
}
