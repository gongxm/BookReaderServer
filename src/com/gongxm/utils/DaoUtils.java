package com.gongxm.utils;

import com.gongxm.dao.RulesDao;
import com.gongxm.dao.UserDao;
import com.gongxm.dao.impl.RulesDaoImpl;
import com.gongxm.dao.impl.UserDaoImpl;

public class DaoUtils {
	
	//��ȡ�û�Dao
	public static UserDao getUserDao(){
		return UserDaoImpl.getInstance();
	}

	//��ȡ����Dao
	public static RulesDao getRulesDao(){
		return RulesDaoImpl.getInstance();
	}
}
