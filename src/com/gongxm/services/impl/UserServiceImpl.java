package com.gongxm.services.impl;

import com.gongxm.bean.User;
import com.gongxm.dao.Dao;
import com.gongxm.dao.UserDao;
import com.gongxm.services.UserService;
import com.gongxm.utils.DaoUtils;
/**
 * 用户服务
 * @author gongxm
 *
 */
public class UserServiceImpl extends BaseService<User> implements UserService {
	private static final UserServiceImpl instance = new UserServiceImpl(); 
	
	private static final UserDao udao = DaoUtils.getUserDao();
	
	private UserServiceImpl(){}

	public static UserService getInstance() {
		return instance;
	}

	@Override
	public User findUserByName(String username) {
		return udao.findUserByName(username);
	}

	@Override
	public void addUser(User user) {
		udao.add(user);
	}

	@Override
	public User findUser(String username, String password) {
		return udao.findUser(username, password);
	}

	@Override
	public Dao<User> getDao() {
		return udao;
	}

	@Override
	public User findUserByThirdSession(String thirdSession) {
		return udao.findUserByThirdSession(thirdSession);
	}

	@Override
	public User findUserByOpenId(String openid) {
		return udao.findUserByOpenId(openid);
	}

}
