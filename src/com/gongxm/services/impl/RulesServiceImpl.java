package com.gongxm.services.impl;

import java.util.List;

import com.gongxm.bean.Rules;
import com.gongxm.dao.Dao;
import com.gongxm.dao.RulesDao;
import com.gongxm.services.RulesService;
import com.gongxm.utils.DaoUtils;

public class RulesServiceImpl extends BaseService<Rules> implements RulesService {
	private static final RulesServiceImpl instance = new RulesServiceImpl();
	
	private RulesDao rulesDao = DaoUtils.getRulesDao();

	private RulesServiceImpl() {
	}
	
	public Dao<Rules> getDao(){
		return rulesDao;
	}

	public static RulesServiceImpl getInstance() {
		return instance;
	}

	@Override
	public List<Rules> findAll() {
		return rulesDao.findAll();
	}
}
