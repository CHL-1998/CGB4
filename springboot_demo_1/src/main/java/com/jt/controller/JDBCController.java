package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author huilong
 * @create 2020/7/29 9:12
 */
@RestController
//properties配置文件与spring容器建立关系,指定pro文件之后，进行加载,默认的加载策略
//采用的iso-8859-1的编码格式，如果其中包含中文，则应该采用utf-8的格式编码
@PropertySource(value="classpath:/properties/jdbc.properties",encoding = "utf-8")
public class JDBCController {
    //@Value的作用就是，从spring容器中找到具体的key，为具体的属性赋值
    @Value("${jdbc.username}") //spel表达式，spring提供的语法规则
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @RequestMapping("/getMsg")
    public String getMsg(){
        this.username="root";
        this.password= "root";
        return username + "" +password;


    }
    @RequestMapping("/getMsgValue")
    public String getMsgValue(){

        /*User user = new User();
        user.setId(10).setName("zhongguo");*/
        return username + "|" +password;
    }
}
