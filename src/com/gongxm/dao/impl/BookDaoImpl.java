package com.gongxm.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.gongxm.bean.Book;
import com.gongxm.dao.BookDao;
import com.gongxm.utils.HibernateUtil;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {
	private static final BookDaoImpl instance = new BookDaoImpl(); 
	
	private BookDaoImpl(){}

	public static BookDao getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getBookCategory() {
		Session session = null;
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
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getCategoryList(String category, int currentPage, int pageSize) {
		Session session = null;
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
		}
		return null;
	}

}
