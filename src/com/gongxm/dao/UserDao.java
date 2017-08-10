package com.gongxm.dao;

import com.gongxm.bean.User;

public interface UserDao extends Dao<User> {

	User findUserByName(String username);

}
