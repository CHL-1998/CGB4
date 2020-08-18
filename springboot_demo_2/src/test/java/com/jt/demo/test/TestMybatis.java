package com.jt.demo.test;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.demo.mapper.UserMapper;
import com.jt.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//@RunWith(SpringRunner.class)	//注意测试文件的位置 必须在主文件加载包路径下

@SpringBootTest
public class TestMybatis {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void testFindUser() {

		List<User> userList = userMapper.findAll();
		System.out.println(userList);
	}

	//案例一：利用mybatis plus实现查询所有的数据
	@Test
	public void selectList(){

		List<User> userList = userMapper.selectList(null);
		System.out.println(userList);
	}

	//1、用户的入库操作
	//注意事项： MP操作时将对象中不为空的数据，当作执行要素
	@Test
	public void insert(){
		User user = new User();
		user.setName("特朗普");
		user.setAge(60);
		user.setSex("男");
		userMapper.insert(user);
	}

	@Test
	public void select01(){
		//定义条件构造器，动态拼接where条件之后的数据
		User user = new User();
		user.setName("特朗普");

		QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println(userList);
	}

	@Test
	public void select02(){


		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sex","女");
		queryWrapper.gt("age",200);
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println(userList);
	}

	@Test
	public  void select03(){
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("name","精");
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println(userList);
	}

	@Test
	public void select04(){

		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.between("age",18,35).eq("sex","男");
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println(userList);

	}

	@Test
	public void select05(){
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.isNotNull("name").orderByDesc("age","sex");
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println(userList);
	}

	@Test
	public void update(){
		User user = new User();
//		user.setId(53);
		user.setName("特朗普");
		user.setSex("女");
		user.setAge(60);
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		//userMapper.updateById(user);
		updateWrapper.eq("id",53);
		userMapper.update(user,updateWrapper);


	}
}
