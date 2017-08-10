package com.gongxm.utils;

import com.gongxm.services.UserService;
import com.gongxm.services.impl.UserServiceImpl;

public class ServiceUtils {
	//获取用户服务
	public static UserService getUserService() {
		return UserServiceImpl.getInstance();
	}
}
