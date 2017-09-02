package com.gongxm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gongxm.bean.User;
import com.gongxm.dao.UserDao;

@Repository("userDao")
public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public User findUserByName(String username) {
		String sql = "select * from user where username=?";
		List<User> list = sqlObj.queryForList(sql, User.class, username);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public User findUser(String username, String password) {
		String sql = "select * from user where username=? and password=?";
		List<User> list = sqlObj.queryForList(sql, User.class, username,password);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public User findUserByThirdSession(String thirdSession) {
		String sql = "select * from user where thirdSession=?";
		List<User> list = sqlObj.queryForList(sql, User.class,thirdSession);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public User findUserByOpenId(String openid) {
		String sql = "select * from user where openid=?";
		List<User> list = sqlObj.queryForList(sql, User.class,openid);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}
