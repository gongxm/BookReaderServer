package com.gongxm.dao.impl;

import org.springframework.stereotype.Repository;

import com.gongxm.bean.User;
import com.gongxm.dao.UserDao;
@Repository("userDao")
public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public User findUserByName(String username) {
		String sql = "select * from user where username=?";
		User user = sqlObj.queryForObject(sql, new Object[] {username}, User.class);
		return user;
	}

	@Override
	public User findUser(String username, String password) {
		String sql = "select * from user where username=? and password=?";
		User user = sqlObj.queryForObject(sql, new Object[] {username,password}, User.class);
		return user;
	}

	@Override
	public User findUserByThirdSession(String thirdSession) {
		String sql = "select * from user where thirdSession=?";
		User user = sqlObj.queryForObject(sql, new Object[] {thirdSession}, User.class);
		return user;
	}

	@Override
	public User findUserByOpenId(String openid) {
		String sql = "select * from user where openid=?";
		User user = sqlObj.queryForObject(sql, new Object[] {openid}, User.class);
		return user;
	}

}
