package com.gongxm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gongxm.bean.BookList;
import com.gongxm.dao.BookListDao;
@Repository("bookListDao")
public class BookListDaoImpl extends BaseDao<BookList> implements BookListDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BookList> findUnCollectBookListBySource(String book_source, int currentPage, int pageSize) {
		/*Session session = null;
		try {
			session = HibernateUtil.getSession();
			String sql = "from BookList where book_source=? and status=?";
			List<BookList> list = session.createQuery(sql).setParameter(0, book_source)
					.setParameter(1, MyConstants.BOOK_UNCOLLECT).setFirstResult((currentPage - 1) * pageSize)
					.setMaxResults(pageSize).list();
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
	public List<BookList> findAllUnCollectBookList(int currentPage, int pageSize) {
		/*Session session = null;
		try {
			session = HibernateUtil.getSession();
			String sql = "from BookList where status=?";
			List<BookList> list = session.createQuery(sql).setParameter(0, MyConstants.BOOK_UNCOLLECT).setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
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

	@Override
	public long getUnCollectBookListCountBySource(String book_source) {
		/*Session session = null;
		try {
			session = HibernateUtil.getSession();
			String sql = "select count(*) from BookList where book_source=? and status=?";
			long count = (long) session.createQuery(sql).setParameter(0, book_source)
					.setParameter(1, MyConstants.BOOK_UNCOLLECT).uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}*/
		return 0;
	}

	@Override
	public long getAllUnCollectBookListCount() {
		/*Session session = null;
		try {
			session = HibernateUtil.getSession();
			String sql = "select count(*) from BookList where status=?";
			long count = (long) session.createQuery(sql).setParameter(0, MyConstants.BOOK_UNCOLLECT)
					.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}*/
		return 0;
	}

	@Override
	public BookList findByBookLink(String bookUrl) {
		/*Session session = null;
		try {
			session = HibernateUtil.getSession();
			String sql = "from BookList where book_link=?";
			BookList list = (BookList) session.createQuery(sql).setParameter(0, bookUrl).uniqueResult();
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
