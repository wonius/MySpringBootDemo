package com.springboot.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/6/6
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (ApplicationContextUtil.applicationContext == null) {
            ApplicationContextUtil.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return ApplicationContextUtil.getApplicationContext().getBean(name);
    }

    public static <T>T getBean(Class<T> clazz) {
        return ApplicationContextUtil.getApplicationContext().getBean(clazz);
    }

    public static <T>T getBean(String name, Class<T> clazz) {
        return ApplicationContextUtil.getApplicationContext().getBean(name, clazz);
    }
}
