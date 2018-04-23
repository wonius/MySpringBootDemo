package com.springboot.jmockit;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/4/20
 */
public class SimpleInterfaceImpl implements SimpleInterface {
    @Override
    public String getCityName() {
        return "default city";
    }

    @Override
    public String getAreaName() {
        return "default area";
    }
}
