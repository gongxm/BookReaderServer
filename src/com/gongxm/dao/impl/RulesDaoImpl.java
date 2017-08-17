package com.gongxm.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.gongxm.bean.Rules;
import com.gongxm.dao.RulesDao;
import com.gongxm.utils.HibernateUtil;

public class RulesDaoImpl extends BaseDao<Rules> implements RulesDao {

	private static final RulesDaoImpl instance = new RulesDaoImpl();

	private RulesDaoImpl() {
	}

	public static RulesDao getInstance() {
		return instance;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Rules> findAll() {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String sql = "from Rules";
			List<Rules> list= session.createQuery(sql).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}

}
