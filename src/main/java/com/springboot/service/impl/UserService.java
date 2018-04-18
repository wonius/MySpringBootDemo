package com.springboot.service.impl;

import com.springboot.domain.User;
import com.springboot.mapper.UserMapper;
import com.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/4/17
 */
@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser() {
        return userMapper.getUser();
    }
}
