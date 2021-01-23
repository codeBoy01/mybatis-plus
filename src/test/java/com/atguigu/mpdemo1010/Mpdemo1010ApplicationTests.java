package com.atguigu.mpdemo1010;

import com.atguigu.mpdemo1010.entity.User;
import com.atguigu.mpdemo1010.mapper.UserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		User user=new User();
		user.setId(2L);
		user.setAge(20);
		int row=userMapper.updateById(user);
        System.out.println(2222);
	}

	@Test
	public void autoInsertUser(){
		User user=new User();
		user.setAge(80);
		user.setName("Lucky22");
		user.setEmail("Lucky22@qq.com");
		int insert=userMapper.insert(user);
		System.out.println("insert："+insert);
	}
	@Test
	public void testOptimisticLocker(){
		User user=userMapper.selectById(1L);
		user.setName("yye");
		user.setEmail("yye@qq.com");
		userMapper.updateById(user);
	}
	@Test
	public void test2OptimisticLocker(){
		User user=userMapper.selectById(1L);
		user.setName("yye");
		user.setEmail("yye@qq.com");
		user.setVersion(user.getVersion()-1);
		userMapper.updateById(user);
	}
	@Test
	void selectId(){
		User user=userMapper.selectById(1L);
		System.out.println(user);
	}
	@Test
	void selectListById(){
		List<User> users=userMapper.selectBatchIds(Arrays.asList(1,2,3));
		users.forEach(System.out::println);
	}
	@Test
	void selectByMap(){
		HashMap<String,Object>map=new HashMap<>();
		map.put("name","Helen");
		map.put("age",18);
		List<User> users=userMapper.selectByMap(map);
		users.forEach(System.out::println);
	}
	@Test
	void PageTest(){
		Page<User>page=new Page<>(1,5);
		userMapper.selectPage(page,null);
		page.getRecords().forEach(System.out::println);
		System.out.println(page.getCurrent());
		System.out.println(page.getPages());
		System.out.println(page.getSize());
		System.out.println(page.getTotal());
		System.out.println(page.hasNext());
		System.out.println(page.hasPrevious());
	}

	@Test
	 void testSelectMapsPage() {
		Page<User> page = new Page<>(1, 5);
		IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);
		//注意：此行必须使用 mapIPage 获取记录列表，否则会有数据类型转换错误
		mapIPage.getRecords().forEach(System.out::println);
		System.out.println(page.getCurrent());
		System.out.println(page.getPages());
		System.out.println(page.getSize());
		System.out.println(page.getTotal());
		System.out.println(page.hasNext());
		System.out.println(page.hasPrevious());
	}

	@Test
	void testDeleteById(){
		int delete=userMapper.deleteById(1L);
		System.out.println("delete:"+delete);
	}

	@Test
	void testDeleteListById(){
		int delete=userMapper.deleteBatchIds(Arrays.asList(2L,3L));
		System.out.println("delete:"+delete);
	}

	@Test
	void testDeleteByMap(){
		HashMap<String,Object> map=new HashMap<>();
		map.put("age",24);
		int delete=userMapper.deleteByMap(map);
		System.out.println("delete:"+delete);
	}
	//测试逻辑删除
	@Test
	void testLogicDelete(){
		int result=userMapper.deleteById(4L);
		System.out.println("result:"+result);
	}
	@Test
	public void testPerformance() {
		User user = new User();
		user.setName("我是Helen");
		user.setEmail("helen@sina.com");
		user.setAge(18);
		userMapper.insert(user);
	}

}
