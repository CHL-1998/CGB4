package com.jt.controller;

import com.jt.vo.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author huilong
 * @create 2020/8/7 16:09
 */
@RestController
public class PortController{
    //动态赋值操作

    @Value("${server.port}")
    private Integer port;

    @RequestMapping("/getPort")
    public JsonResult getPort(){

        return new JsonResult("端口号为："+port);
    }
}
