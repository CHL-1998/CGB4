package com.jt.demo.test;

import com.jt.demo.mapper.UserMapper;
import com.jt.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author huilong
 * @create 2020/7/29 17:35
 */
@SpringBootTest
public class testFind {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelect(){
        List<User> all = userMapper.findAll();
        System.out.println(all);
    }
}
