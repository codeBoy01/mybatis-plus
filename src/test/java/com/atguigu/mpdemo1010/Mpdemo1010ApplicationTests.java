package com.atguigu.mpdemo1010;

import com.atguigu.mpdemo1010.entity.User;
import com.atguigu.mpdemo1010.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Mpdemo1010ApplicationTests {
	@Autowired
	private UserMapper userMapper;
	//查询User表
	@Test
	void contextLoads() {
		List<User> userList=userMapper.selectList(null);
		System.out.println(userList);
	}

	@Test
	public void addUser(){
		User user=new User();
		user.setAge(18);
		user.setName("Lucky");
		user.setEmail("Lucky@qq.com");
		int insert=userMapper.insert(user);
		System.out.println("insert："+insert);
	}

	@Test
	public void updateUser(){

	}

}