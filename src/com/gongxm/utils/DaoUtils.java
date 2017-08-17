package com.gongxm.utils;

import com.gongxm.dao.RulesDao;
import com.gongxm.dao.UserDao;
import com.gongxm.dao.impl.RulesDaoImpl;
import com.gongxm.dao.impl.UserDaoImpl;

public class DaoUtils {
	
	//获取用户Dao
	public static UserDao getUserDao(){
		return UserDaoImpl.getInstance();
	}

	//获取规则Dao
	public static RulesDao getRulesDao(){
		return RulesDaoImpl.getInstance();
	}
}
