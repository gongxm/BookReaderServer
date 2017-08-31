package com.gongxm.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gongxm.bean.Rules;
import com.gongxm.dao.Dao;
import com.gongxm.dao.RulesDao;
import com.gongxm.services.RulesService;
@Service("rulesService")
public class RulesServiceImpl extends BaseService<Rules> implements RulesService {
	private static final RulesServiceImpl instance = new RulesServiceImpl();
	@Autowired
	@Qualifier("rulesDao")
	private RulesDao rulesDao;

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
