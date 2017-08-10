package com.gongxm.services;

import com.gongxm.bean.User;

public interface UserService {

	User findUserByName(String username);

	void addUser(User user);

}
