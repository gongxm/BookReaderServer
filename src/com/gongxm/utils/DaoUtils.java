package com.gongxm.utils;

import com.gongxm.dao.UserDao;
import com.gongxm.dao.impl.UserDaoImpl;

public class DaoUtils {
	
	//获取用户Dao
	public static UserDao getUserDao(){
		return UserDaoImpl.getInstance();
	}

}
