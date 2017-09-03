package com.gongxm.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.gongxm.bean.BookChapter;
import com.gongxm.dao.BookChapterDao;
import com.gongxm.utils.MyConstants;

@Repository("bookChapterDao")
public class BookChapterDaoImpl extends BaseDao<BookChapter> implements BookChapterDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BookChapter> findByBookId(int bookid) {
		try {
			String sql = "from BookChapter where book_id=?";
			List<BookChapter> list = (List<BookChapter>) hqlObj.find(sql, bookid);
			return list;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getUnCollectChapterCount() {
		try {
			String sql = "select count(*) from book_chapters where status=?";
			Long count =sqlObj.queryForObject(sql, new Object[] {MyConstants.BOOK_UNCOLLECT}, Long.class);
			return count;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookChapter> findUnCollectChapter(int currentPage, int pageSize) {
		try {
			String sql = "from BookChapter where status=?";
			return (List<BookChapter>) hqlObj.find(sql, MyConstants.BOOK_UNCOLLECT);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
