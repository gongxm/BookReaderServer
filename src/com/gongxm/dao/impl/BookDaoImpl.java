package com.gongxm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.gongxm.bean.Book;
import com.gongxm.dao.BookDao;

@Repository("bookDao")
public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public List<String> getBookCategory() {
		try {
			String sql = "select distinct category from books";
			List<String> list = sqlObj.queryForList(sql, String.class);
			return list;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getCategoryList(String category, int currentPage, int pageSize) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Book.class);
			criteria.add(Restrictions.eq("category", category));
			List<Book> list = (List<Book>) hqlObj.findByCriteria(criteria, (currentPage - 1) * pageSize,pageSize);
			return list;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
