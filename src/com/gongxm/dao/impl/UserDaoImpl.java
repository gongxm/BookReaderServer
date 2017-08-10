package com.gongxm.dao.impl;

import com.gongxm.bean.User;
import com.gongxm.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
	private static final UserDaoImpl instance = new UserDaoImpl(); 
	
	private UserDaoImpl(){}

	public static UserDao getInstance() {
		return instance;
	}

	@Override
	public User findUserByName(String username) {
		return null;
	}

}
