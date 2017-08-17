package com.gongxm.dao;

import java.util.List;

import com.gongxm.bean.Rules;

public interface RulesDao extends Dao<Rules> {

	List<Rules> findAll();

}
