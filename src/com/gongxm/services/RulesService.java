package com.gongxm.services;

import java.util.List;

import com.gongxm.bean.Rules;

public interface RulesService extends Service<Rules> {

	List<Rules> findAll();
}
