package com.gongxm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
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
			DetachedCriteria criteria = DetachedCriteria.forClass(BookChapter.class);
			criteria.add(Restrictions.eq("status", MyConstants.BOOK_UNCOLLECT));
			List<BookChapter> list = (List<BookChapter>) hqlObj.findByCriteria(criteria, (currentPage - 1) * pageSize,pageSize);
			return list;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BookChapter findByChapterLink(String chapterLink) {
		String sql = "select * from book_chapters where chapter_link=?";
		BookChapter chapter = null;
		try {
			chapter = sqlObj.queryForObject(sql, new BookChapterMap(), chapterLink);
		} catch (DataAccessException e) {
		}
		return chapter;
	}
	
	class BookChapterMap  implements RowMapper<BookChapter> {
		@Override
		public BookChapter mapRow(ResultSet rs, int i) throws SQLException {
			BookChapter chapter = new BookChapter();
			chapter.setId(rs.getInt("id"));
			chapter.setChapter_link(rs.getString("chapter_link"));
			chapter.setChapter_name(rs.getString("chapter_name"));
			chapter.setPosition(rs.getInt("position"));
			chapter.setStatus(rs.getInt("status"));
			return chapter;
		}
		
	}
}
