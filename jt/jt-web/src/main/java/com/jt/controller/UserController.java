package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author huilong
 * @create 2020/8/17 15:56
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 要求用一个方法实现页面的通用跳转
     *
     */
    @RequestMapping("/{moduleName}")
    public String doLogin(@PathVariable String moduleName){

        return moduleName;
    }
}
