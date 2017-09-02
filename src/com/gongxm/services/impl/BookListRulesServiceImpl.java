package com.gongxm.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gongxm.bean.BookListRules;
import com.gongxm.dao.BookListRulesDao;
import com.gongxm.dao.Dao;
import com.gongxm.services.BookListRulesService;
@Service
public class BookListRulesServiceImpl extends BaseService<BookListRules> implements BookListRulesService {
	@Autowired
	BookListRulesDao dao;

	@Override
	public Dao<BookListRules> getDao() {
		return dao;
	}

}
