package com.jt.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author huilong
 * @create 2020/8/17 17:41
 */
@RestController
@RequestMapping("/web")
public class JSONPController {

    @RequestMapping("/testJSONP")
    public String jsonp(String callback){

        return callback + "({'id':'100','name':'tomcat'})";
    }
}
