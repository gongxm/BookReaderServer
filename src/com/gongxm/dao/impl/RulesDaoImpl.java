package com.gongxm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.gongxm.bean.Rules;
import com.gongxm.dao.RulesDao;
@Repository("rulesDao")
public class RulesDaoImpl extends BaseDao<Rules> implements RulesDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Rules> findAll() {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Rules.class);
			List<Rules> list = (List<Rules>) hqlObj.findByCriteria(criteria);
			return list;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
