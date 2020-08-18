package com.jt.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
	
	List<User> findAll();

	/*@Override
	List<User> selectList(Wrapper<User> queryWrapper);*/
}
