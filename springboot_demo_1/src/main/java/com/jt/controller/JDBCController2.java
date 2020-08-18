package com.jt.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author huilong
 * @create 2020/7/29 9:12
 */
@RestController
@ConfigurationProperties(prefix = "jdbc")  //定义属性的前缀
//批量设置属性也可以指定配置文件
@PropertySource(value="classpath:/properties/jdbc.properties",encoding = "utf-8")
public class JDBCController2 {
   //利用配置文件为属性赋值
    //属性与类中的属性名称必须一致，自动的赋值
    private String username;
    private String password;

    //为属性赋值时一定会调用对象的set方法


    //映射地址不能重复
    @RequestMapping("/getMsgValue1")
    public String getMsgValue(){

        return username + "|" +password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
