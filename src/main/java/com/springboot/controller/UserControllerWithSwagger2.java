package com.springboot.controller;

import com.springboot.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/4/27
 */
@RestController
@RequestMapping(value = "/users")
@Api(tags = "用户管理类")
public class UserControllerWithSwagger2 {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @ApiOperation(value = "获取用户列表")
    @RequestMapping(value = "/getUsers")
    public List<User> getUsers() {
        return new ArrayList<User>(users.values());
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户实体User", required = true, dataType = "User")
    @RequestMapping(value = "/postUser", method = RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value = "获取用户信息", notes = "根据id获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")
    @RequestMapping(value = "/getUser")
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据id更新对应user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/putUser", method = RequestMethod.POST)
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setHost(user.getHost());
        u.setName(user.getName());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "根据id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}
