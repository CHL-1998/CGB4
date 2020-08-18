package com.jt.demo.controller;

import com.jt.demo.pojo.User;
import com.jt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author huilong
 * @create 2020/7/30 10:00
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/findAll")
    public String JumpToJsp(Model model){
        List<User> userList = userService.findAll();
        model.addAttribute("userList",userList);
        return "userList";
    }

    @RequestMapping("/userAjax")
    public String userAjax(){
        return "userAjax";
    }

    @RequestMapping("/findAjax")
    @ResponseBody
    public List<User> findAjax(){
        return userService.findAll();
    }
}
