package com.gongxm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gongxm.bean.Book;
import com.gongxm.dao.BookDao;

@Repository("bookDao")
public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getBookCategory() {
		/*Session session = null;
		try {
			session = HibernateUtil.getSession();
			String sql = "select distinct category from books";
			NativeQuery<String> query = session.createNativeQuery(sql);
			List<String> list =query.list();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getCategoryList(String category, int currentPage, int pageSize) {
		/*Session session = null;
		try {
			session = HibernateUtil.getSession();
			String sql = "from Book where category=?";
			List<Book> list = session.createQuery(sql).setParameter(0, category).setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
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
