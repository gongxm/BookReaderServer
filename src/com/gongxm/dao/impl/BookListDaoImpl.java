package com.gongxm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gongxm.bean.BookList;
import com.gongxm.dao.BookListDao;
import com.gongxm.utils.MyConstants;
@Repository("bookListDao")
public class BookListDaoImpl extends BaseDao<BookList> implements BookListDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BookList> findUnCollectBookListBySource(String book_source, int currentPage, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(BookList.class);
		criteria.add(Restrictions.eq("book_source", book_source));
		criteria.add(Restrictions.eq("status", MyConstants.BOOK_UNCOLLECT));
		List<BookList> list = (List<BookList>) hqlObj.findByCriteria(criteria, (currentPage - 1) * pageSize,pageSize);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookList> findAllUnCollectBookList(int currentPage, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(BookList.class);
		criteria.add(Restrictions.eq("status", MyConstants.BOOK_UNCOLLECT));
		List<BookList> list = (List<BookList>) hqlObj.findByCriteria(criteria, (currentPage - 1) * pageSize,pageSize);
		return list;
	}

	@Override
	public long getUnCollectBookListCountBySource(String book_source) {
		String sql = "select count(*) from book_list where book_source=? and status=?";
		Long count =sqlObj.queryForObject(sql, new Object[] {book_source,MyConstants.BOOK_UNCOLLECT}, Long.class);
		return count;
	}

	@Override
	public long getAllUnCollectBookListCount() {
		String sql = "select count(*) from book_list where status=?";
		Long count =sqlObj.queryForObject(sql, new Object[] {MyConstants.BOOK_UNCOLLECT}, Long.class);
		return count;
	}

	@Override
	public BookList findByBookLink(String bookUrl) {
		String hql = "from BookList where book_link=?";
		BookList list = (BookList) hqlObj.find(hql, bookUrl);
		return list;
	}
}
