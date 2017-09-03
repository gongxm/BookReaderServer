package com.gongxm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.gongxm.bean.BookListRules;
import com.gongxm.dao.BookListRulesDao;

@Repository
public class BookListRulesDaoImpl extends BaseDao<BookListRules> implements BookListRulesDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BookListRules> findAll() {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BookListRules.class);
			List<BookListRules> list = (List<BookListRules>) hqlObj.findByCriteria(criteria);
			return list;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
