package com.gongxm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gongxm.bean.BookChapter;
import com.gongxm.dao.BookChapterDao;
import com.gongxm.utils.MyConstants;

@Repository("bookChapterDao")
public class BookChapterDaoImpl extends BaseDao<BookChapter> implements BookChapterDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BookChapter> findByBookId(int bookid) {
		/*
		 * Session session = null; try { session = HibernateUtil.getSession(); String
		 * sql = "from BookChapter where book_id=?"; List<BookChapter> list=
		 * session.createQuery(sql).setParameter(0, bookid).list(); return list; } catch
		 * (Exception e) { e.printStackTrace(); } finally { if (session != null) {
		 * session.close(); } }
		 */

//		String sql = "from BookChapter where book_id=?";
//		List<BookChapter> list = (List<BookChapter>) ht.find(sql, bookid);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getUnCollectChapterCount() {
		/*
		 * Session session = null; try { session = HibernateUtil.getSession(); String
		 * sql = "select count(*) from BookChapter where status=?"; long count = (long)
		 * session.createQuery(sql) .setParameter(0,
		 * MyConstants.BOOK_UNCOLLECT).uniqueResult(); return count; } catch (Exception
		 * e) { e.printStackTrace(); } finally { if (session != null) { session.close();
		 * } }
		 */

		String hql = "select count(*) from BookChapter where status=?";
		// long count = (long) ht.iterate(sql, MyConstants.BOOK_UNCOLLECT).next();

		long count = 0;
		List<Long> list = (List<Long>) ht.find(hql, MyConstants.BOOK_UNCOLLECT);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookChapter> findUnCollectChapter(int currentPage, int pageSize) {
		/*
		 * Session session = null; try { session = HibernateUtil.getSession(); String
		 * sql = "from BookChapter where status=?"; List<BookChapter> list=
		 * session.createQuery(sql).setParameter(0,
		 * MyConstants.BOOK_UNCOLLECT).setFirstResult((currentPage - 1) *
		 * pageSize).setMaxResults(pageSize).list(); return list; } catch (Exception e)
		 * { e.printStackTrace(); } finally { if (session != null) { session.close(); }
		 * }
		 */
		String sql = "from BookChapter where status=?";
		return null;//(List<BookChapter>) ht.find(sql, MyConstants.BOOK_UNCOLLECT);
	}
}
