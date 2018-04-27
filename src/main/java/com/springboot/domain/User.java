package com.springboot.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/4/17
 */
public class User implements Serializable {
    private String user;
    private String host;
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
