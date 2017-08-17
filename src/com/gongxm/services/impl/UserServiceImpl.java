package com.gongxm.services.impl;

import com.gongxm.bean.User;
import com.gongxm.dao.UserDao;
import com.gongxm.services.UserService;
import com.gongxm.utils.DaoUtils;
/**
 * �û���ط���
 * @author gongxm
 *
 */
public class UserServiceImpl implements UserService {
	//����
	private static final UserServiceImpl instance = new UserServiceImpl(); 
	
	//��ȡ�û�DAO
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

}
