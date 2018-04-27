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
    private Long id;
    private String name;
    private String host;
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
