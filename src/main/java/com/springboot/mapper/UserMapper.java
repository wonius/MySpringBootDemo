package com.springboot.mapper;

import com.springboot.domain.User;
import org.apache.ibatis.annotations.Select;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/4/17
 */
//@Mapper
public interface UserMapper {
    @Select("select * from user limit 1")
    User getUser();
}
