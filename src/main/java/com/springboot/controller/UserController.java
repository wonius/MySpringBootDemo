package com.springboot.controller;

import com.springboot.domain.User;
import com.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/4/17
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User getUser() {
        return userService.getUser();
    }

    @RequestMapping(value = "/multiFileTest", method = RequestMethod.POST)
    public void multiFileTest(User user, HttpServletRequest request) {
        System.out.println("success");
    }
}
