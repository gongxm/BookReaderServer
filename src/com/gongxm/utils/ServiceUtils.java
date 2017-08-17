package com.gongxm.utils;

import com.gongxm.services.RulesService;
import com.gongxm.services.UserService;
import com.gongxm.services.impl.RulesServiceImpl;
import com.gongxm.services.impl.UserServiceImpl;

public class ServiceUtils {
	// ��ȡ�û�����
	public static UserService getUserService() {
		return UserServiceImpl.getInstance();
	}

	// ��ȡ�������
	public static RulesService getRulesService() {
		return RulesServiceImpl.getInstance();
	}
}
