package com.gongxm.utils;

import com.gongxm.services.BookService;
import com.gongxm.services.RulesService;
import com.gongxm.services.UserService;
import com.gongxm.services.impl.BookServiceImpl;
import com.gongxm.services.impl.RulesServiceImpl;
import com.gongxm.services.impl.UserServiceImpl;

public class ServiceUtils {
	//获取用户服务
	public static UserService getUserService() {
		return UserServiceImpl.getInstance();
	}

	// 获取规则服务
	public static RulesService getRulesService() {
		return RulesServiceImpl.getInstance();
	}
	// 获取书籍服务
	public static BookService getBookService() {
		return BookServiceImpl.getInstance();
	}
}
