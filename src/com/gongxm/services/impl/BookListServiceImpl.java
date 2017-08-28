package com.gongxm.services.impl;

import java.util.List;

import com.gongxm.bean.BookList;
import com.gongxm.dao.BookListDao;
import com.gongxm.dao.Dao;
import com.gongxm.services.BookListService;
import com.gongxm.utils.DaoUtils;
/**
 * 用户服务
 * @author gongxm
 *
 */
public class BookListServiceImpl extends BaseService<BookList> implements BookListService {
	private static final BookListServiceImpl instance = new BookListServiceImpl(); 
	
	private static final BookListDao dao = DaoUtils.getBookListDao();
	private BookListServiceImpl(){}

	public static BookListService getInstance() {
		return instance;
	}

	@Override
	public Dao<BookList> getDao() {
		return dao;
	}

	@Override
	public List<BookList> findUnCollectBookListBySource(String book_source, int currentPage, int pageSize) {
		return dao.findUnCollectBookListBySource(book_source,currentPage,pageSize);
	}

	@Override
	public List<BookList> findAllUnCollectBookList(int currentPage, int pageSize) {
		return dao.findAllUnCollectBookList(currentPage,pageSize);
	}

	@Override
	public long getUnCollectBookListCountBySource(String book_source) {
		return dao.getUnCollectBookListCountBySource(book_source);
	}

	@Override
	public long getAllUnCollectBookListCount() {
		return dao.getAllUnCollectBookListCount();
	}
	
}
