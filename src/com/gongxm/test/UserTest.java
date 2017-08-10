package com.gongxm.test;

import org.junit.Test;

import com.gongxm.bean.User;
import com.gongxm.services.UserService;
import com.gongxm.utils.ServiceUtils;

public class UserTest {
	@Test
	public void test1(){
		User user = new User();
		user.setUsername("’≈ÃÏÃÏ");
		user.setPassword("123");
		user.setPermissions("user");
		
		UserService us = ServiceUtils.getUserService();
		us.addUser(user);
		
	}
}
