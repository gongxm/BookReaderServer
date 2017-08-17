package com.gongxm.dao.impl;

import org.hibernate.Session;

import com.gongxm.bean.User;
import com.gongxm.dao.UserDao;
import com.gongxm.utils.HibernateUtil;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
	private static final UserDaoImpl instance = new UserDaoImpl(); 
	
	private UserDaoImpl(){}

	public static UserDao getInstance() {
		return instance;
	}

	@Override
	public User findUserByName(String username) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String sql = "from User where username=?";
			User user= (User) session.createQuery(sql).setParameter(0, username).uniqueResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}

	@Override
	public User findUser(String username, String password) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String sql = "from User where username=? and password=?";
			User user= (User) session.createQuery(sql).setParameter(0, username).setParameter(1, password).uniqueResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}

}
