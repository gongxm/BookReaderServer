package com.gongxm.test;

import org.junit.Test;

import com.gongxm.bean.Rules;
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
	
	@Test
	public void test2(){
		Rules rules = new Rules();
		rules.setUrl("http://www.baidu.com");
		rules.setStartStr("abc");
		rules.setEndStr("cmd");
		rules.setStartIndex(2);
		rules.setEndIndex(20);
		rules.setRepeat(false);
		rules.setCurrent(true);
		ServiceUtils.getRulesService().add(rules);
	}
	
	
	@Test
	public void test3(){
		
	}
	
}
