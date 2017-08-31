package com.gongxm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gongxm.bean.Rules;
import com.gongxm.dao.RulesDao;
@Repository("rulesDao")
public class RulesDaoImpl extends BaseDao<Rules> implements RulesDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Rules> findAll() {
		/*Session session = null;
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
		}*/
		return null;
	}

}
