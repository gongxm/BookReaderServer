package com.gongxm.utils;

import com.gongxm.dao.BookDao;
import com.gongxm.dao.RulesDao;
import com.gongxm.dao.UserDao;
import com.gongxm.dao.impl.BookDaoImpl;
import com.gongxm.dao.impl.RulesDaoImpl;
import com.gongxm.dao.impl.UserDaoImpl;

public class DaoUtils {
	
	//用户Dao
	public static UserDao getUserDao(){
		return UserDaoImpl.getInstance();
	}

	//规则Dao
	public static RulesDao getRulesDao(){
		return RulesDaoImpl.getInstance();
	}
	
	//书籍Dao
	public static BookDao getBookDao(){
		return BookDaoImpl.getInstance();
	}
}
