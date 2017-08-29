package com.gongxm.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.gongxm.bean.BookChapter;
import com.gongxm.dao.BookChapterDao;
import com.gongxm.utils.HibernateUtil;
import com.gongxm.utils.MyConstants;

public class BookChapterDaoImpl extends BaseDao<BookChapter> implements BookChapterDao {
	private static final BookChapterDaoImpl instance = new BookChapterDaoImpl();

	private BookChapterDaoImpl() {
	}

	public static BookChapterDao getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookChapter> findByBookId(int bookid) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String sql = "from BookChapter where book_id=?";
			List<BookChapter> list= session.createQuery(sql).setParameter(0, bookid).list();
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

	@Override
	public long getUnCollectChapterCount() {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String sql = "select count(*) from BookChapter where status=?";
			long count = (long) session.createQuery(sql)
					.setParameter(0, MyConstants.BOOK_UNCOLLECT).uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookChapter> findUnCollectChapter(int currentPage, int pageSize) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String sql = "from BookChapter where status=?";
			List<BookChapter> list= session.createQuery(sql).setParameter(0, MyConstants.BOOK_UNCOLLECT).setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
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
