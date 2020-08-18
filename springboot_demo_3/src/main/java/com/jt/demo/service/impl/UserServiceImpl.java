package com.jt.demo.service.impl;

import com.jt.demo.mapper.UserMapper;
import com.jt.demo.pojo.User;
import com.jt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author huilong
 * @create 2020/7/30 10:49
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findAll() {
        List<User> list = userMapper.findAll();
        return list;
    }
}
